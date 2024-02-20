package com.zenith.accountInfo;

import static com.zenith.accountInfo.commons.Examples.REQUEST_EXAMPLE;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.apache.camel.EndpointInject;
import org.apache.camel.component.mock.MockEndpoint;
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
public class RouteDispatchRequestTest extends CamelRouterTestSupport {

	@EndpointInject("mock:callServiceBack")
	MockEndpoint mockCallServiceBack;

	private static final Logger logger = LoggerFactory.getLogger(RouteDispatchRequestTest.class);

	/**
	 * Test to validate channel authentication against vault credentials - success
	 * 
	 * @throws Exception
	 */
	@Test
	void requestSuccessTest() throws Exception {

		logger.info("-------------------requestSuccessTest--------------");

		Long messageID = System.currentTimeMillis();

		RequestWrapper request = CamelRouterTestSupport.payloadObjectMapper
				.readValue(REQUEST_EXAMPLE.replaceAll("KCB_MESSAGEID", messageID.toString()), RequestWrapper.class);

		Map<String, Object> headerCamel = new HashMap<String, Object>();

		headerCamel.put("messageID", messageID.toString());
		headerCamel.put("Content-Type", "application/json");
		headerCamel.put("Accept", "application/json");

		mockCallServiceBack.expectedMessageCount(1);

		// sending data to route consumer
		String case1 = this.sendMessage("seda:dispatchRequest", request, headerCamel);

		mockCallServiceBack.assertIsSatisfied();
		assertNotNull(case1);
		assertTrue(case1.contains(messageID.toString()));
	}

	@Test
	void requestNotHeaderTest() throws Exception {

		logger.info("-------------------requestNotHeaderTest--------------");

		Long messageID = System.currentTimeMillis();

		RequestWrapper request = RouteDispatchRequestTest.payloadObjectMapper
				.readValue(REQUEST_EXAMPLE.replaceAll("KCB_MESSAGEID", messageID.toString()), RequestWrapper.class);

		Map<String, Object> headerCamelH = new HashMap<String, Object>();

		headerCamelH.put("messageID", messageID.toString());

		mockCallServiceBack.expectedMessageCount(1);

		String case1 = this.sendMessage("seda:dispatchRequest", request, headerCamelH);

		mockCallServiceBack.assertIsSatisfied();

		// Case 1: no DataValidationException
		assertNotNull(case1);
	}

}