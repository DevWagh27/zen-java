package com.zenith.accountInfo.models;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class RequestPayload implements Serializable {
	/** Serial version */
	private static final long serialVersionUID = 7868310611900741033L;

	private PrimaryData primaryData;
	private AdditionalData additionalData;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RequestPayload [primaryData=");
		builder.append(primaryData);
		builder.append(", additionalData=");
		builder.append(additionalData);
		builder.append("]");
		return builder.toString();
	}

}
