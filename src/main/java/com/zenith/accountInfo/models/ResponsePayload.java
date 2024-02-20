package com.zenith.accountInfo.models;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ResponsePayload implements Serializable {

	private static final long serialVersionUID = 353652223581858774L;

	public PrimaryData primaryData;
	public AccountInfo accountInfo;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ResponsePayload [primaryData=");
		builder.append(primaryData);
		builder.append(", accountInfo=");
		builder.append(accountInfo);
		builder.append("]");
		return builder.toString();
	}
}