package com.easyproject.main;

import java.io.IOException;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication(scanBasePackages= {"com.easyproject.cloud"})
@EnableZuulProxy
public class Application {
	public static void main(String[] args) throws IOException {
		new SpringApplicationBuilder(Application.class).web(WebApplicationType.SERVLET).run(args);
	}

	
}
