package com.easyproject.main;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@SpringBootApplication(scanBasePackages = { "com.easyproject.cloud" })
@EnableDiscoveryClient
public class Application {
	public static void main(String[] args) throws IOException {
		SpringApplication.run(Application.class, args);
//		new SpringApplicationBuilder(Application.class).web(WebApplicationType.SERVLET).run(args);
	}
}
