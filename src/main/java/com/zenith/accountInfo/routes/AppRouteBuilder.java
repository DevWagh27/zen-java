package com.zenith.accountInfo.routes;

import static com.zenith.accountInfo.commons.CustomExchangeProperties.CONVERSATION_ID_HEADER;
import static com.zenith.accountInfo.commons.CustomExchangeProperties.MESSAGE_ID_HEADER;
import static com.zenith.accountInfo.commons.HTTPCommonHeadersEnum.CONTENT_TYPE;
import static org.springframework.http.HttpHeaders.ACCEPT;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.TEXT_XML_VALUE;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.model.rest.RestParamType;
import org.springframework.stereotype.Component;

import com.zenith.accountInfo.configurations.CustomLogMask;
import com.zenith.accountInfo.exceptions.UnsuccessfullException;
import com.zenith.accountInfo.models.RequestWrapper;
import com.zenith.accountInfo.models.ResponseWrapper;


@Component
public class AppRouteBuilder extends RouteBuilder {

	@Override
	public void configure() throws Exception {

		onException(Exception.class)
				.handled(true)
				.removeHeaders("*", "messageID")
				.process("errorHandlingProcessor");

		rest()
				.post("/v1/cbs/accountInfo")
				.description("Adapter REST Service")
				.consumes(APPLICATION_JSON_VALUE)
				.type(RequestWrapper.class)
				.description("POST Endpoint 2 CustAcctInquiry Interface")
				.param().name("body").required(true).type(RestParamType.body).endParam()
				.param().name("Authorization").description("The Authorization:Basic User/Password ")
				.type(RestParamType.header).endParam()
				.param().name("messageID").description("The messageID ").type(RestParamType.header).endParam()
				.produces(APPLICATION_JSON_VALUE)
				.outType(ResponseWrapper.class)
				.responseMessage()
				.code(OK.value())
				.endResponseMessage()
				.to("seda:dispatchRequest");

		/* Routes Configuration */
		from("seda:dispatchRequest").routeId("com.zenith.request.dispatchRequest")
				.noStreamCaching().noMessageHistory().noTracing()
				.to("direct:begin")
				.to("direct:callServiceBack");

		from("direct:begin").routeId("accountinfo.zenith.request.begin")
				.noStreamCaching().noMessageHistory().noTracing()
				.log(LoggingLevel.INFO, "Account Info::REST Request::MessageID [${headers.messageID}]::Payload [${body}]")
				.process("headersSetterProcessor")
				.marshal().json(JsonLibrary.Gson, RequestWrapper.class)
				.to("json-validator:classpath:RequestPayloadSchema.json?contentCache=false");

		from("direct:callServiceBack").routeId("accountinfo.zenith.request.CustAcctInquiry")
				.noStreamCaching().noMessageHistory().noTracing()
				.removeHeaders("*", MESSAGE_ID_HEADER,CONVERSATION_ID_HEADER)
				.setBody(simple("${exchangeProperty.originalMessage}"))
				.process("createRequestProcessor")
				.to("freemarker:classpath:ResquestBack.ftl?contentCache=true")
				.bean(CustomLogMask.class, "camelCustomLogMask(\"Account Info Atomic::Request::URI [{{atomic.uri}}\", ${headers.messageID}, ${body})")
				.setHeader(ACCEPT, constant(TEXT_XML_VALUE))
				.setHeader(CONTENT_TYPE.getName(), constant(TEXT_XML_VALUE))
				.enrich().simple("{{atomic.uri}}").id("callServiceBack")
				.convertBodyTo(String.class)
				.log(LoggingLevel.INFO, "Account Info Atomic::Response::MessageID [${headers.messageID}]::ConversationID [${headers.conversationID}]::Payload [${body}]")
				.doTry()
					.process("successResponseGeneratorProcessor")
				.doCatch(UnsuccessfullException.class)
					.process("errorHandlingProcessor")
				.end()
				.removeHeaders("*", MESSAGE_ID_HEADER, CONVERSATION_ID_HEADER)
				.log(LoggingLevel.INFO, "Account Info::Response Atomic::MessageID [${headers.messageID}]::Payload [${body}]");

	}
}