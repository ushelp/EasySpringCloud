package com.easyproject.cloud.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;

/**
 * Feign file upload
 * @author think
 *
 */
@Configuration
public class MultipartSupportConfig {
	@Bean
	public Encoder feignFormEncoder() {
		return new SpringFormEncoder();
	}
}