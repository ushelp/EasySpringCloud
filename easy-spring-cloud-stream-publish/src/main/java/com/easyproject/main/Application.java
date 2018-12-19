package com.easyproject.main;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "com.easyproject.cloud" })
public class Application {
	
	public static void main(String[] args) throws IOException {
		SpringApplication.run(Application.class, args);
	}

}
