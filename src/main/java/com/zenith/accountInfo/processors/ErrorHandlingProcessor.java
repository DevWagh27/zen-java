package com.zenith.accountInfo.processors;

import static com.zenith.accountInfo.commons.CustomExchangeProperties.ORIGINAL_REQUEST;
import static com.zenith.accountInfo.commons.ErrorCodesEnum.OSP1000;
import static com.zenith.accountInfo.commons.ErrorCodesEnum.OSP1004;
import static com.zenith.accountInfo.commons.ErrorCodesEnum.OSP1014;
import static com.zenith.accountInfo.commons.ErrorCodesEnum.OSP1999;

import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.ExchangeTimedOutException;
import org.apache.camel.Processor;
import org.apache.camel.ValidationException;
import org.apache.camel.component.jsonvalidator.JsonValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import com.zenith.accountInfo.exceptions.DataValidationException;
import com.zenith.accountInfo.exceptions.SystemAuthenticationException;
import com.zenith.accountInfo.models.EhfInfo;
import com.zenith.accountInfo.models.Item;
import com.zenith.accountInfo.models.RequestWrapper;
import com.zenith.accountInfo.models.ResponseHeader;
import com.zenith.accountInfo.models.ResponsePayload;
import com.zenith.accountInfo.models.ResponseWrapper;


@Component
public class ErrorHandlingProcessor implements Processor {

	private static final Logger logger = LoggerFactory.getLogger(ErrorHandlingProcessor.class);

	@Override
	public void process(Exchange exchange) throws Exception {

		Exception exception = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class);

		logger.info(exception.getClass().toString());
		
		RequestWrapper originalRequest = exchange.getProperty(ORIGINAL_REQUEST, RequestWrapper.class);
		logger.info("--------------------- "+originalRequest.toString());

		String routeCode = "";
		String routeName = "";
		String channelCode = "";
		String channelName = "";
		String messageID = "";
		String conversationID = "";

		ResponseWrapper responseWrapper = new ResponseWrapper();

		if (null != originalRequest) {
			ResponsePayload responsePayload = new ResponsePayload();

			responsePayload.setPrimaryData(originalRequest.getRequestPayload().getPrimaryData());
			responseWrapper.setResponsePayload(responsePayload);

			routeCode = originalRequest.getHeader().getRouteCode();
			routeName = originalRequest.getHeader().getRouteName();
			channelCode = originalRequest.getHeader().getChannelCode();
			channelName = originalRequest.getHeader().getChannelName();
			messageID = originalRequest.getHeader().getMessageID();
		}

		Item entry = null;
		List<Item> listofItems = new ArrayList<>();

		EhfInfo ehfInfo = new EhfInfo();

		if (exception instanceof DataValidationException || exception instanceof UnrecognizedPropertyException
				|| exception instanceof ValidationException || exception instanceof JsonValidationException) {

			entry = new Item();
			entry.setEhfDesc(OSP1014.getMessage());
			entry.setEhfRef(OSP1014.getCode());
			listofItems.add(entry);
		} else if (exception instanceof ExchangeTimedOutException || exception instanceof SocketTimeoutException) {
			entry = new Item();
			entry.setEhfDesc(OSP1004.getMessage());
			entry.setEhfRef(OSP1004.getCode());
			listofItems.add(entry);
		} else if (exception instanceof SystemAuthenticationException) {
			entry = new Item();
			entry.setEhfDesc(OSP1000.getMessage());
			entry.setEhfRef(OSP1000.getCode());
			listofItems.add(entry);
		} else {
			entry = new Item();
			entry.setEhfDesc(OSP1999.getMessage());
			entry.setEhfRef(OSP1999.getCode());
			listofItems.add(entry);
		}

		ehfInfo.setItem(listofItems);
		ResponseHeader header = new ResponseHeader();
		header.setEhfInfo(ehfInfo);

		header.setMessageID(messageID);
		header.setConversationID(conversationID);
		header.setTargetSystemID("NotAvailable");
		header.setChannelCode(channelCode);
		header.setChannelName(channelName);
		header.setRouteCode(routeCode);
		header.setRouteName(routeName);

		responseWrapper.setHeader(header);
		exchange.getIn().setBody(responseWrapper, ResponseWrapper.class);
		exchange.getIn().setHeader(Exchange.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

		// Logs all required information about the error
		logger.error("Adapter::Error::MessageID [{}]::Exception [{}]", messageID, exception.getMessage(),
				exception.getCause() != null ? exception.getCause() : exception);
	}
}
