package com.zenith.accountInfo.commons;


public enum HTTPCommonHeadersEnum {
	ACCEPT("Accept"),
	CONTENT_TYPE("Content-Type"),
	AUTHORIZATION("Authorization"),
	WWW_AUTHENTICATE("WWW-Authenticate");

	private String name;

	private HTTPCommonHeadersEnum(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}