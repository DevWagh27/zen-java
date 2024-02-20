package com.zenith.accountInfo.models;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AccountInfo implements Serializable {

	private static final long serialVersionUID = -9031250057303725585L;

	private CustomerDetails customerDetails;
    private Address address;
    private String accountID;
    private String cbsAccountRef;
    private String openDate;
    private String branchCode;
    private String schemeCode;
    private String schemeType;
    private String accountName;
    private String currency;
    private String statusCode;
    private String ownershipCode;
    private String closureFlag;
    private String creationFlag;
    private String freezeCode;
    private String clearBalance;
    private String unclearBalance;
    private String effectiveBalance;
    private String sanctionLimit;
    private String lienAmount;
    private String drawingPower;
    private String modeOfOperation;
    private String corpRetFlag;
    private String incoprationDate;
    private String constCode;
    private String secRegNo;
    private String compRegNo;
    
    
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AccountInfo [customerDetails=");
		builder.append(customerDetails);
		builder.append(", address=");
		builder.append(address);
		builder.append(", accountID=");
		builder.append(accountID);
		builder.append(", cbsAccountRef=");
		builder.append(cbsAccountRef);
		builder.append(", openDate=");
		builder.append(openDate);
		builder.append(", branchCode=");
		builder.append(branchCode);
		builder.append(", schemeCode=");
		builder.append(schemeCode);
		builder.append(", schemeType=");
		builder.append(schemeType);
		builder.append(", accountName=");
		builder.append(accountName);
		builder.append(", currency=");
		builder.append(currency);
		builder.append(", statusCode=");
		builder.append(statusCode);
		builder.append(", ownershipCode=");
		builder.append(ownershipCode);
		builder.append(", closureFlag=");
		builder.append(closureFlag);
		builder.append(", creationFlag=");
		builder.append(creationFlag);
		builder.append(", freezeCode=");
		builder.append(freezeCode);
		builder.append(", clearBalance=");
		builder.append(clearBalance);
		builder.append(", unclearBalance=");
		builder.append(unclearBalance);
		builder.append(", effectiveBalance=");
		builder.append(effectiveBalance);
		builder.append(", sanctionLimit=");
		builder.append(sanctionLimit);
		builder.append(", lienAmount=");
		builder.append(lienAmount);
		builder.append(", drawingPower=");
		builder.append(drawingPower);
		builder.append(", modeOfOperation=");
		builder.append(modeOfOperation);
		builder.append(", corpRetFlag=");
		builder.append(corpRetFlag);
		builder.append(", incoprationDate=");
		builder.append(incoprationDate);
		builder.append(", constCode=");
		builder.append(constCode);
		builder.append(", secRegNo=");
		builder.append(secRegNo);
		builder.append(", compRegNo=");
		builder.append(compRegNo);
		builder.append("]");
		return builder.toString();
	}
}