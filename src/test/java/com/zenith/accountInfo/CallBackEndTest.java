package com.zenith.accountInfo;

import static com.zenith.accountInfo.commons.CustomExchangeProperties.ORIGINAL_REQUEST;
import static com.zenith.accountInfo.commons.Examples.REQUEST_EXAMPLE;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.apache.camel.Exchange;
import org.apache.camel.builder.ExchangeBuilder;
import org.apache.camel.test.spring.junit5.CamelSpringBootTest;
import org.apache.camel.test.spring.junit5.UseAdviceWith;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

import com.zenith.accountInfo.configurations.CamelRouterTestSupport;
import com.zenith.accountInfo.models.RequestWrapper;
import com.zenith.accountInfo.models.ResponseWrapper;


@CamelSpringBootTest
@UseAdviceWith
@SpringBootTest(classes = Application.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class CallBackEndTest extends CamelRouterTestSupport {

	private static final Logger logger = LoggerFactory.getLogger(CallBackEndTest.class);

	/**
	 * Test to validate channel authentication against vault credentials - success
	 * 
	 * @throws Exception
	 */
	@Test
	void callServiceSuccessTest() throws Exception {

		logger.info("callServiceSuccessTest");

		Long messageID = System.currentTimeMillis();

		String bodyrequest = REQUEST_EXAMPLE.replaceAll("KCB_MESSAGEID", messageID.toString());

		RequestWrapper requestWrapper = CallBackEndTest.payloadObjectMapper.readValue(bodyrequest,
				RequestWrapper.class);

		// Sends the message with valid credentials header
		Exchange exchange = this.getProducerTemplate().send("direct:callServiceBack",
				ExchangeBuilder.anExchange(this.getContext())
						.withBody(bodyrequest.getBytes())
						.withHeader("messageID", messageID.toString())
						.withProperty(ORIGINAL_REQUEST, requestWrapper).build());

		ResponseWrapper responseWrapper = exchange.getIn().getBody(ResponseWrapper.class);

		String statusRef = responseWrapper.getHeader().getEhfInfo().getItem().get(0).getEhfRef();

		// Case 1: no DataValidationException
		assertNotNull(exchange);
		assertTrue(statusRef.equals("OSP-1000"));
	}

}