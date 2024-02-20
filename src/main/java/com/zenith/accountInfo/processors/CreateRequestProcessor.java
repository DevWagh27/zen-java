package com.zenith.accountInfo.processors;

import static com.zenith.accountInfo.commons.CustomExchangeProperties.HEADER_ACCOUNTID;
import static com.zenith.accountInfo.commons.CustomExchangeProperties.*;
import static com.zenith.accountInfo.commons.CustomExchangeProperties.HEADER_CHANNELID;
import static com.zenith.accountInfo.commons.CustomExchangeProperties.HEADER_UUID;
import static com.zenith.accountInfo.commons.CustomExchangeProperties.ORIGINAL_REQUEST;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.zenith.accountInfo.models.RequestWrapper;


@Component
public class CreateRequestProcessor implements Processor {

	private static final Logger logger = LoggerFactory.getLogger(CreateRequestProcessor.class);
	
	public static final String SAFARI_TIMESTAMP_FORMAT = "yyyyMMddHH:mm:ss:gg:ggg";

	private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(SAFARI_TIMESTAMP_FORMAT);
	

	@Override
	public void process(Exchange exchange) throws Exception {

		// Gets the request body
		RequestWrapper payload = exchange.getProperty(ORIGINAL_REQUEST, RequestWrapper.class);


		logger.info(payload.toString());
		// Set body and headers
		exchange.getIn().removeHeaders("Camel*");
		exchange.getIn().setHeader(Exchange.HTTP_METHOD, "POST");

		exchange.getIn().setHeader(HEADER_UUID, payload.getHeader().getMessageID());
		exchange.getIn().setHeader(HEADER_BANCKID, payload.getRequestPayload().getAdditionalData().getBankId());
		exchange.getIn().setHeader(HEADER_ACCOUNTID, payload.getRequestPayload().getPrimaryData().getBusinessKey());
		exchange.getIn().setHeader(HEADER_CHANNELID, payload.getHeader().getChannelIdentifier());
		exchange.getIn().setHeader(HEADER_DATE_TIME, dateTimeFormatter.format(LocalDateTime.now()));
	}
}