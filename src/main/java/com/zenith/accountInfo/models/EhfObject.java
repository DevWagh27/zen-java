package com.zenith.accountInfo.models;

import java.io.Serializable;

import lombok.Data;


@Data
public class EhfObject implements Serializable {

	private static final long serialVersionUID = -8551895066257437538L;
	private String key;
	private EhfRecord ehfRecord;

}
