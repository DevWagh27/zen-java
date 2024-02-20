package com.zenith.accountInfo.models;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class EhfInfo implements Serializable {
	/** Serial version UID */
	private static final long serialVersionUID = -8647643527147121819L;
	private List<Item> item;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EhfInfo [item=");
		builder.append(item);
		builder.append("]");
		return builder.toString();
	}

}