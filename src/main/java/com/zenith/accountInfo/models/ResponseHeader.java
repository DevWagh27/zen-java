package com.zenith.accountInfo.models;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class ResponseHeader implements Serializable {

	/** Serial version UID */
	private static final long serialVersionUID = -7857624541088572239L;

	private String messageID;
	private String conversationID;
	private String targetSystemID;
	private String channelCode;
	private String channelName;
	private String channelIdentifier;
	private String routeCode;
	private String routeName;
	private String serviceCode;
	private EhfInfo ehfInfo;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ResponseHeader [messageID=");
		builder.append(messageID);
		builder.append(", conversationID=");
		builder.append(conversationID);
		builder.append(", targetSystemID=");
		builder.append(targetSystemID);
		builder.append(", channelCode=");
		builder.append(channelCode);
		builder.append(", channelName=");
		builder.append(channelName);
		builder.append(", channelIdentifier=");
		builder.append(channelIdentifier);
		builder.append(", routeCode=");
		builder.append(routeCode);
		builder.append(", routeName=");
		builder.append(routeName);
		builder.append(", serviceCode=");
		builder.append(serviceCode);
		builder.append(", ehfInfo=");
		builder.append(ehfInfo);
		builder.append("]");
		return builder.toString();
	}

}