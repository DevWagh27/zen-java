package com.zenith.accountInfo;

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


@CamelSpringBootTest
@UseAdviceWith
@SpringBootTest(classes = Application.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class BeginRequestTest extends CamelRouterTestSupport {

	private static final Logger logger = LoggerFactory.getLogger(BeginRequestTest.class);

	@Test
	void transfortRequestTestSuccess() throws Exception {

		logger.info("============= transfortRequestTestSuccess====================");

		Long messageID = System.currentTimeMillis();

		RequestWrapper request = BeginRequestTest.payloadObjectMapper
				.readValue(REQUEST_EXAMPLE.replaceAll("KCB_MESSAGEID", messageID.toString()), RequestWrapper.class);

		// Sends the message with valid credentials header
		Exchange exchange = this.getProducerTemplate().send("direct:begin",
				ExchangeBuilder.anExchange(this.getContext())
						.withBody(request)
						.withHeader("messageID", messageID.toString())
						.build());

		String caseCamel = exchange.getIn().getBody(String.class);

		// Case 1: no DataValidationException
		assertNotNull(caseCamel);
		assertTrue(caseCamel.contains(messageID.toString()));
	}

	@Test
	void bathRequestTestFailure() throws Exception {

		logger.info("============= bathRequestTestFailure====================");

		Long messageID = System.currentTimeMillis();

		RequestWrapper request = BeginRequestTest.payloadObjectMapper
				.readValue(REQUEST_EXAMPLE.replaceAll("KCB_MESSAGEID", messageID.toString()), RequestWrapper.class);

		// Sends the message with valid credentials header
		Exchange exchange = this.getProducerTemplate().send("direct:begin",
				ExchangeBuilder.anExchange(this.getContext())
						.withBody(request)
						.build());

		String caseCamel = exchange.getIn().getBody(String.class);

		// Case 1: no DataValidationException
		assertNotNull(caseCamel);
		assertTrue(caseCamel.contains(messageID.toString()));
	}

}