package com.easyproject.cloud.feign.fallback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.easyproject.cloud.entity.Users;
import com.easyproject.cloud.feign.UsersClient;

@Component
public class UsersClientFallback implements UsersClient {
	
	Logger logger = LoggerFactory.getLogger(UsersClientFallback.class);

	@Override
	public Users get(int id) {
		logger.info("UsersClient.get 失败备援！");
		// Place your code...
		
		return null;
	}

	@Override
	public String upload(int id, MultipartFile file) {
		logger.info("UsersClient.upload 失败备援！");
		// Place your code...
		
		return null;
	}

	@Override
	public ResponseEntity<byte[]> download() {
		logger.info("UsersClient.download 失败备援！");
		// Place your code...
		
		return null;
	}
	
}
