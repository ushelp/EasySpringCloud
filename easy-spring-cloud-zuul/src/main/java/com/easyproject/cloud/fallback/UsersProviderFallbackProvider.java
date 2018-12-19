package com.easyproject.cloud.fallback;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import com.easyproject.cloud.filter.ErrorFilter;
import com.netflix.hystrix.exception.HystrixTimeoutException;

@Component
public class UsersProviderFallbackProvider implements FallbackProvider {

	Logger logger = LoggerFactory.getLogger(UsersProviderFallbackProvider.class);
	
	@Override
	public String getRoute() {
//		return "*"; // 代表默认所有路由（为所有路由提供默认 fallback）
		return "users-provider"; // 基于 serviceId 进行熔断
	}

	
	private ClientHttpResponse response(final HttpStatus status) {
		return new ClientHttpResponse() {
			@Override
			public HttpStatus getStatusCode() throws IOException {
				return status;
			}

			@Override
			public int getRawStatusCode() throws IOException {
				return status.value();
			}

			@Override
			public String getStatusText() throws IOException {
				return status.getReasonPhrase();
			}

			@Override
			public void close() {
			}

			@Override
			public InputStream getBody() throws IOException {
				return new ByteArrayInputStream("失败备援！".getBytes());
			}

			@Override
			public HttpHeaders getHeaders() {
				HttpHeaders headers = new HttpHeaders();
				headers.set("Content-Type", "text/html;charset=UTF-8");
//				headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
				return headers;
			}
		};
	}

	@Override
	public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
		if (cause instanceof HystrixTimeoutException) {
            return response(HttpStatus.GATEWAY_TIMEOUT);
        } else {
            return response(HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}


	
}
