package com.zenith.accountInfo.processors;

import static com.zenith.accountInfo.commons.CustomExchangeProperties.CALLER_IP;
import static com.zenith.accountInfo.commons.CustomExchangeProperties.CONVERSATION_ID_HEADER;
import static com.zenith.accountInfo.commons.CustomExchangeProperties.EMPTY;
import static com.zenith.accountInfo.commons.CustomExchangeProperties.ORIGINAL_REQUEST;

import java.util.Objects;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.zenith.accountInfo.models.RequestWrapper;


@Component
public class HeadersSetterProcessor implements Processor {

	private static final Logger logger = LoggerFactory.getLogger(HeadersSetterProcessor.class);

	@Override
	public void process(Exchange exchange) throws Exception {

		// Get Caller IP
		HttpServletRequest request = exchange.getIn().getBody(HttpServletRequest.class);
		RequestWrapper requestWrapper = exchange.getIn().getBody(RequestWrapper.class);

		exchange.setProperty(ORIGINAL_REQUEST, requestWrapper);

		String ipRemote = Objects.isNull(request) ? EMPTY : request.getRemoteAddr();

		String conversationID = UUID.randomUUID().toString();
		
		exchange.getIn().setHeader(CONVERSATION_ID_HEADER, conversationID);
		exchange.getIn().setHeader(CALLER_IP, ipRemote);
		logger.info("CALLER_IP_MESSAGE", ipRemote);

	}
}