package com.zenith.accountInfo.configurations;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.http.HttpComponent;
import org.apache.camel.support.jsse.KeyManagersParameters;
import org.apache.camel.support.jsse.KeyStoreParameters;
import org.apache.camel.support.jsse.SSLContextParameters;
import org.apache.camel.support.jsse.TrustManagersParameters;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Http4Configuration extends RouteBuilder {


	@Value("${truststore.path}")
	private String path;

	@Value("${truststore.password}")
	private String password;

	@Value("${truststore.keystoretype}")
	private String keystoretype;

	@Value("${truststore.scheme}")
	private String scheme;

	@Override
	public void configure() throws Exception {
		KeyStoreParameters ksp = new KeyStoreParameters();
		ksp.setType(keystoretype);
		ksp.setResource(path);
		ksp.setPassword(password);

		KeyManagersParameters kmp = new KeyManagersParameters();
		kmp.setKeyStore(ksp);
		kmp.setKeyPassword(password);

		TrustManagersParameters trustManagersParameters = new TrustManagersParameters();
		trustManagersParameters.setKeyStore(ksp);

		SSLContextParameters scp = new SSLContextParameters();
		scp.setKeyManagers(kmp);
		scp.setTrustManagers(trustManagersParameters);

		HttpComponent httpComponent = getContext().getComponent(scheme, HttpComponent.class);
		httpComponent.setSslContextParameters(scp);
		//This is important to make your cert skip CN/Hostname checks
		//        httpComponent.setX509HostnameVerifier(  new AllowAllHostnameVerifier());
		httpComponent.setX509HostnameVerifier(NoopHostnameVerifier.INSTANCE);
	}
}