package com.zenith.accountInfo.commons;


public enum PayloadFormatEnum {
	TEXT("text/plain"), 
	CSV("text/csv"), 
	JSON("application/json"),
	TEXT_XML("text/xml"),
	XML("application/xml");

	private String contentType;

	private PayloadFormatEnum(String contentType) {
		this.contentType = contentType;
	}

	public String getContentType() {
		return this.contentType;
	}
}


