package com.zenith.accountInfo.models;

import java.io.Serializable;
import java.util.List;
import lombok.Data;


@Data
public class EhfResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5581964837224953052L;
	private Boolean success;
	private String detail;
	private List<EhfObject> records;

}