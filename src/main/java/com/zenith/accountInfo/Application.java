package com.zenith.accountInfo;

//import org.apache.camel.component.servlet.CamelHttpTransportServlet;
import org.apache.camel.component.servlet.CamelHttpTransportServlet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class Application {

    @Value("${camel.servlet.mapping.context-path}")
    private String camelContextPath;

    @Value("${camel.servlet.mapping.servlet-name}")
    private String camelServletName;

    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    ServletRegistrationBean<CamelHttpTransportServlet> servletRegistrationBean() {
        ServletRegistrationBean<CamelHttpTransportServlet> servlet = new ServletRegistrationBean<CamelHttpTransportServlet>(
                new CamelHttpTransportServlet(), camelContextPath);
        servlet.setName(camelServletName);
        return servlet;
    }

}
