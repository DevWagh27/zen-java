package com.zenith.accountInfo.models;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CustomerDetails implements Serializable {
	
	private static final long serialVersionUID = 8504107491887869734L;
	
	private String identifier;
	private String fullName;
    private String firstName;
    private String lastName;
    private String shortName;
    private String titleCode;
    private String nationalID;
    private String emailID;
    private String phoneNumber;
    private String dateOfBirth;
    private String maritalStatus;
    private String gender;
    private String minorFlag;
    private String nreFlag;
    private String community;
    private String userSubClassification;
    private String passportNo;
    
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CustomerDetails [identifier=");
		builder.append(identifier);
		builder.append(", fullName=");
		builder.append(fullName);
		builder.append(", firstName=");
		builder.append(firstName);
		builder.append(", lastName=");
		builder.append(lastName);
		builder.append(", shortName=");
		builder.append(shortName);
		builder.append(", titleCode=");
		builder.append(titleCode);
		builder.append(", nationalID=");
		builder.append(nationalID);
		builder.append(", emailID=");
		builder.append(emailID);
		builder.append(", phoneNumber=");
		builder.append(phoneNumber);
		builder.append(", dateOfBirth=");
		builder.append(dateOfBirth);
		builder.append(", maritalStatus=");
		builder.append(maritalStatus);
		builder.append(", gender=");
		builder.append(gender);
		builder.append(", minorFlag=");
		builder.append(minorFlag);
		builder.append(", nreFlag=");
		builder.append(nreFlag);
		builder.append(", community=");
		builder.append(community);
		builder.append(", userSubClassification=");
		builder.append(userSubClassification);
		builder.append(", passportNo=");
		builder.append(passportNo);
		builder.append("]");
		return builder.toString();
	}

}