package com.zenith.accountInfo.models;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class Address implements Serializable {
	
	private static final long serialVersionUID = 8504107491887869734L;
	
	private String addressLine1;
	private String addressLine2;
	private String cityCode;
	private String stateCode;
	private String countryCode;
	private String cityCodeDesc;
	private String pinCode;
	private String addressID;
    
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Address [addressLine1=");
		builder.append(addressLine1);
		builder.append(", addressLine2=");
		builder.append(addressLine2);
		builder.append(", cityCode=");
		builder.append(cityCode);
		builder.append(", stateCode=");
		builder.append(stateCode);
		builder.append(", countryCode=");
		builder.append(countryCode);
		builder.append(", cityCodeDesc=");
		builder.append(cityCodeDesc);
		builder.append(", pinCode=");
		builder.append(pinCode);
		builder.append(", addressID=");
		builder.append(addressID);
		builder.append("]");
		return builder.toString();
	}

}