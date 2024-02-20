package com.zenith.accountInfo.models;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class RequestHeader implements Serializable {
	/** Serial version */
	private static final long serialVersionUID = 7656371320496443722L;

	// All this information comes from channel as part of the full payload
	protected String messageID;
	protected String serviceCode;
	protected String serviceName;
	protected String channelIdentifier;
	protected String channelCode;
	protected String channelName;
	protected String routeCode;
	protected String routeName;
	protected String timeStamp;
	protected String serviceMode;
	protected String subscribeEvents;
	protected String callBackURL;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RequestHeader [messageID=");
		builder.append(messageID);
		builder.append(", serviceCode=");
		builder.append(serviceCode);
		builder.append(", serviceName=");
		builder.append(serviceName);
		builder.append(", channelIdentifier=");
		builder.append(channelIdentifier);
		builder.append(", channelCode=");
		builder.append(channelCode);
		builder.append(", channelName=");
		builder.append(channelName);
		builder.append(", routeCode=");
		builder.append(routeCode);
		builder.append(", routeName=");
		builder.append(routeName);
		builder.append(", timeStamp=");
		builder.append(timeStamp);
		builder.append(", serviceMode=");
		builder.append(serviceMode);
		builder.append(", subscribeEvents=");
		builder.append(subscribeEvents);
		builder.append(", callBackURL=");
		builder.append(callBackURL);
		builder.append("]");
		return builder.toString();
	}

}