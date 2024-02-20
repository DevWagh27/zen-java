package com.zenith.accountInfo.configurations;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CustomLogMask {

	private static final Logger logger = LoggerFactory.getLogger(CustomLogMask.class);

	public void camelCustomLogMask(final String step, final String messageID, final String body) {
		String messageTmp = ApplyMask(body);
		String messageOut = step + "::MessageID [" + messageID + "]::Payload [" + messageTmp + "])";
		logger.info("{}", messageOut);
	}

	public String camelCustomLogMask(final String body) {
		// logger.info("Raw Body{}",body);
		String messageOut = ApplyMask(body);
		return messageOut;
	}

	public void camelCustomLogMaskPrint(final String body) {
		logger.info("{}", ApplyMask(body));
	}

	private String ApplyMask(final String body) {
		String messageTmp = body;

		Pattern patternPasswordT24 = Pattern.compile("<password.*?>(.*?)<\\/password>");
		Pattern patternPassword = Pattern.compile("<Password.*?>(.*?)<\\/Password>");
		Pattern patternSecuriry = Pattern.compile("<SecurityCredential.*?>(.*?)<\\/SecurityCredential>");
		Pattern patternSPPassword = Pattern.compile("<tns:spPassword.*?>(.*?)<\\/tns:spPassword>");

		Matcher matchPasswordT24 = patternPasswordT24.matcher(body);
		Matcher matchPassword = patternPassword.matcher(body);
		Matcher matchSecuriry = patternSecuriry.matcher(body);
		Matcher matchSPPassword = patternSPPassword.matcher(body);

		String passwordT24 = "";
		String password = "";
		String securityCredential = "";
		String sPPassword = "";

		if (matchPasswordT24.find()) {
			for (int i = 1; i <= matchPasswordT24.groupCount(); i++) {
				passwordT24 = matchPasswordT24.group(i);
			}
		}
		if (matchPassword.find()) {
			for (int i = 1; i <= matchPassword.groupCount(); i++) {
				password = matchPassword.group(i);
			}
		}
		if (matchSecuriry.find()) {
			for (int i = 1; i <= matchSecuriry.groupCount(); i++) {
				securityCredential = matchSecuriry.group(i);
			}
		}
		if (matchSPPassword.find()) {
			for (int i = 1; i <= matchSPPassword.groupCount(); i++) {
				sPPassword = matchSPPassword.group(i);
			}
		}

		if (!"".equals(passwordT24))
			messageTmp = messageTmp.replace(passwordT24, "*********");
		if (!"".equals(password))
			messageTmp = messageTmp.replace(password, "*********");
		if (!"".equals(securityCredential))
			messageTmp = messageTmp.replace(securityCredential, "*********");
		if (!"".equals(sPPassword))
			messageTmp = messageTmp.replace(sPPassword, "*********");
		return messageTmp;
	}
}