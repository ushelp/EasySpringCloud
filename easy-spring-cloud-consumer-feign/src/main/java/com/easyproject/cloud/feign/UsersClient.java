package com.easyproject.cloud.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.easyproject.cloud.configuration.MultipartSupportConfig;
import com.easyproject.cloud.entity.Users;
import com.easyproject.cloud.feign.fallback.UsersClientFallback;

@FeignClient(value = "users-provider", fallback = UsersClientFallback.class, configuration = MultipartSupportConfig.class)
public interface UsersClient {
	@RequestMapping("/users/{id}")
	public Users get(@PathVariable int id);

	@RequestMapping(value = "/users/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public String upload(@RequestParam(value = "id") int id, @RequestPart(value = "file") MultipartFile file);

	@RequestMapping(value="/users/download")
	public ResponseEntity<byte[]> download();
}
