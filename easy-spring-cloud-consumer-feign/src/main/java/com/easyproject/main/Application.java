package com.easyproject.main;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = { "com.easyproject.cloud" })
@EnableFeignClients(basePackages = { "com.easyproject.cloud.feign" })
@EnableHystrix
@EnableHystrixDashboard
public class Application {
	
	public static void main(String[] args) throws IOException {
		SpringApplication.run(Application.class, args);
//		new SpringApplicationBuilder(Application.class).web(WebApplicationType.SERVLET).run(args);
	}

}
