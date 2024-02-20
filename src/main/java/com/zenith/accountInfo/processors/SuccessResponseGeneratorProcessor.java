package com.zenith.accountInfo.processors;

import static com.zenith.accountInfo.commons.CustomExchangeProperties.CONVERSATION_ID_HEADER;
import static com.zenith.accountInfo.commons.CustomExchangeProperties.ORIGINAL_REQUEST;
import static com.zenith.accountInfo.commons.ErrorCodesEnum.OSP1000;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;

import com.zenith.accountInfo.models.AccountInfo;
import com.zenith.accountInfo.models.Address;
import com.zenith.accountInfo.models.CustomerDetails;
import com.zenith.accountInfo.models.EhfInfo;
import com.zenith.accountInfo.models.Item;
import com.zenith.accountInfo.models.RequestWrapper;
import com.zenith.accountInfo.models.ResponseHeader;
import com.zenith.accountInfo.models.ResponsePayload;
import com.zenith.accountInfo.models.ResponseWrapper;

import javax.annotation.PostConstruct;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;


@Component
public class SuccessResponseGeneratorProcessor implements Processor {

	private static final Logger logger = LoggerFactory.getLogger(SuccessResponseGeneratorProcessor.class);

	private DocumentBuilder documentBuilder;

	@PostConstruct
	public void createNewDocumentBuilder() throws ParserConfigurationException {
		this.documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
	}

	@Override
	public void process(Exchange exchange) throws Exception {

		String body = exchange.getIn().getBody(String.class);

		// Gets the request body
		Document document = this.documentBuilder.parse(new ByteArrayInputStream(body.getBytes()));

		// Gets the request from the property
		RequestWrapper originalRequestWrapper = exchange.getProperty(ORIGINAL_REQUEST, RequestWrapper.class);

		ResponseWrapper response = new ResponseWrapper();

		ResponsePayload responsePayload = new ResponsePayload();

		responsePayload.setPrimaryData(originalRequestWrapper.getRequestPayload().getPrimaryData());

		AccountInfo additionalData = new AccountInfo();

		CustomerDetails customerDetails = new CustomerDetails();
		Address address = new Address();

		address.setAddressID(getTagValue(document, "AddressId"));
		address.setAddressLine1(getTagValue(document, "Address1"));
		address.setAddressLine2(getTagValue(document, "Address2"));
		address.setCityCode(getTagValue(document, "CityCode"));
		address.setCityCodeDesc(getTagValue(document, "CityCodeDesc"));
		address.setCountryCode(getTagValue(document, "CountryCode"));
		address.setPinCode(getTagValue(document, "PinCode"));
		address.setStateCode(getTagValue(document, "StateCode"));

		customerDetails.setIdentifier(getTagValue(document, "CustomerID"));
		customerDetails.setFullName(getTagValue(document, "CustomerName"));
		customerDetails.setFirstName(getTagValue(document, "CustomerFirstName"));
		customerDetails.setLastName(getTagValue(document, "CustomerLastName"));
		customerDetails.setShortName(getTagValue(document, "CustomerShortName"));
		customerDetails.setTitleCode(getTagValue(document, "CustomerTitleCode"));
		customerDetails.setNationalID(getTagValue(document, "NationalID"));
		customerDetails.setEmailID(getTagValue(document, "EmailID"));
		customerDetails.setPhoneNumber(getTagValue(document, "PhoneNo"));
		customerDetails.setDateOfBirth(getTagValue(document, "DateOfBirth"));
		customerDetails.setMaritalStatus(getTagValue(document, "MaritalStatus"));
		customerDetails.setGender(getTagValue(document, "Sex"));
		customerDetails.setMinorFlag(getTagValue(document, "CustomerMinorFlag"));
		customerDetails.setNreFlag(getTagValue(document, "RetCorpFlg"));
		customerDetails.setCommunity(getTagValue(document, "Community"));
		customerDetails.setUserSubClassification(getTagValue(document, "UserSubClassification"));
		customerDetails.setPassportNo(getTagValue(document, "PassportNo"));

		additionalData.setAccountID(getTagValue(document, "AccountID"));
		additionalData.setAccountName(getTagValue(document, "AccountName"));
		additionalData.setAddress(address);
		additionalData.setBranchCode(getTagValue(document, "BranchCode"));
		additionalData.setCbsAccountRef(getTagValue(document, "Acid"));
		additionalData.setClearBalance(getTagValue(document, "ClearBalance"));
		additionalData.setClosureFlag(getTagValue(document, "AccountClosureFlag"));
		additionalData.setCompRegNo(getTagValue(document, "CompRegNo"));
		additionalData.setConstCode(getTagValue(document, "ConstCode"));
		additionalData.setCorpRetFlag(getTagValue(document, "RetCorpFlg"));
		additionalData.setCreationFlag(getTagValue(document, "AccountCreationFlag"));
		additionalData.setCurrency(getTagValue(document, "Currency"));
		additionalData.setCustomerDetails(customerDetails);
		additionalData.setDrawingPower(getTagValue(document, "DrawingPower"));
		additionalData.setEffectiveBalance(getTagValue(document, "EffectiveBalance"));
		additionalData.setFreezeCode(getTagValue(document, "FreezeCode"));
		additionalData.setIncoprationDate(getTagValue(document, "DateOfIncorp"));
		additionalData.setLienAmount(getTagValue(document, "LienAmount"));
		additionalData.setModeOfOperation(getTagValue(document, "ModeOfOperation"));
		additionalData.setOpenDate(getTagValue(document, "AcctOpenDate"));
		additionalData.setOwnershipCode(getTagValue(document, "AccountOwnership"));
		additionalData.setSanctionLimit(getTagValue(document, "SanctionLimit"));
		additionalData.setSchemeCode(getTagValue(document, "SchemeCode"));
		additionalData.setSchemeType(getTagValue(document, "SchemeType"));
		additionalData.setSecRegNo(getTagValue(document, "SecRegNo"));
		additionalData.setStatusCode(getTagValue(document, "AccountStatus"));
		additionalData.setUnclearBalance(getTagValue(document, "UnclearBalance"));

		responsePayload.setAccountInfo(additionalData);

		Item entry = new Item();
		List<Item> listofItems = new ArrayList<>();
		EhfInfo ehfInfo = new EhfInfo();

		entry.setEhfRef(OSP1000.getCode());
		entry.setEhfDesc(OSP1000.getMessage());

		listofItems.add(entry);

		ehfInfo.setItem(listofItems);
		ResponseHeader header = new ResponseHeader();
		header.setEhfInfo(ehfInfo);
		header.setMessageID(originalRequestWrapper.getHeader().getMessageID());
		header.setConversationID(exchange.getIn().getHeader(CONVERSATION_ID_HEADER, String.class));
		header.setTargetSystemID("NotAvailable");
		header.setChannelCode(originalRequestWrapper.getHeader().getChannelCode());
		header.setChannelName(originalRequestWrapper.getHeader().getChannelName());
		header.setRouteCode(originalRequestWrapper.getHeader().getRouteCode());
		header.setRouteName(originalRequestWrapper.getHeader().getRouteName());
		header.setChannelIdentifier(originalRequestWrapper.getHeader().getChannelIdentifier());
		header.setServiceCode(originalRequestWrapper.getHeader().getServiceCode());

		response.setHeader(header);
		response.setResponsePayload(responsePayload);

		// Sets the payload to the exchange body
		exchange.getIn().setBody(response, ResponseWrapper.class);
	}

	public String getTagValue(Document doc, String tagName) {
		return doc.getElementsByTagName(tagName).item(0) != null
				? doc.getElementsByTagName(tagName).item(0).getTextContent()
				: "";
	}
}
