package com.zenith.accountInfo.models;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PrimaryData implements Serializable {

	/** Serial version UID */
	private static final long serialVersionUID = -8478984558316046120L;

	private String businessKey;
	private String businessKeyType;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PrimaryData [businessKey=");
		builder.append(businessKey);
		builder.append(", businessKeyType=");
		builder.append(businessKeyType);
		builder.append("]");
		return builder.toString();
	}

}