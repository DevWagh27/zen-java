package com.zenith.accountInfo.models;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Keys implements Serializable {

	private static final long serialVersionUID = 1724083858859234133L;

	private List<StringBuilder> keys;

	public Keys(List<StringBuilder> keys) {
		super();
		this.keys = keys;
	}

	public Keys() {
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Keys [keys=");
		builder.append(keys);
		builder.append("]");
		return builder.toString();
	}

}
