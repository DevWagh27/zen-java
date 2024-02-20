package com.zenith.accountInfo.models;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class AdditionalData implements Serializable {
	/** Serial version */
	private static final long serialVersionUID = -6445866824539143515L;

	private String bankId;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AdditionalData [bankId=");
		builder.append(bankId);
		builder.append("]");
		return builder.toString();
	}

}