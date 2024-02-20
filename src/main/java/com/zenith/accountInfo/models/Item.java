package com.zenith.accountInfo.models;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Item implements Serializable {

	private static final long serialVersionUID = 7273566685172729424L;

	public String ehfRef;
	public String ehfDesc;

	public Item() {
		super();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Item [ehfRef=");
		builder.append(ehfRef);
		builder.append(", ehfDesc=");
		builder.append(ehfDesc);
		builder.append("]");
		return builder.toString();
	}

	public Item(String ehfRef, String ehfDesc) {
		super();
		this.ehfRef = ehfRef;
		this.ehfDesc = ehfDesc;
	}

}