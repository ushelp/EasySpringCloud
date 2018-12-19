package com.easyproject.cloud.controller;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.easyproject.cloud.entity.Users;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class UsersController {

	Logger logger = LoggerFactory.getLogger(UsersController.class);

	/**
	 * Service Id
	 */
	private static final String SERVICE_PREFIX = "http://users-provider/";

	@Resource
	RestTemplate restTemplate;

	@RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
	@HystrixCommand(fallbackMethod = "getFallback")
	public Map<String, Object> get(@PathVariable int id) {

		logger.info("[Consumer] UsersController.get");
		logger.info("user id：" + id);

		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> data = new HashMap<String, Object>();

		String url = SERVICE_PREFIX + "users/" + id;
		Users users = restTemplate.getForObject(url, Users.class);

		data.put("users", users);
		result.put("code", 0);
		result.put("msg", "ok");
		result.put("data", data);

		return result;
	}

	@RequestMapping(value = "/users/upload")
	@HystrixCommand(fallbackMethod = "uploadFallback")
	public Map<String, Object> upload(int id, MultipartFile file) {
		logger.info("[Consumer] UsersController.upload");
		Map<String, Object> result = new HashMap<String, Object>();

		String url = SERVICE_PREFIX + "users/upload";

		String res = null;
		try {
			// Save your temporay file..
			File tempFile = new File(file.getOriginalFilename());
			FileUtils.copyInputStreamToFile(file.getInputStream(), tempFile);  
			MultiValueMap<String, Object> params = new LinkedMultiValueMap<String, Object>();
			FileSystemResource resource = new FileSystemResource(tempFile);
			params.add("id", id);
			params.add("file", resource);
			res = restTemplate.postForObject(url, params, String.class);
			tempFile.delete();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		result.put("code", 0);
		result.put("msg", "ok");
		result.put("data", res);

		return result;
	}

	@RequestMapping("/users/download")
	@HystrixCommand(fallbackMethod = "downloadFallback")
	public void consumerDownload(HttpServletResponse response) throws IOException {

		logger.info("[Consumer] UsersController.download");

		String url = SERVICE_PREFIX + "users/download";

		byte[] res = restTemplate.getForObject(url, byte[].class);
		String fileName = "Cat.jpg";
		fileName = URLEncoder.encode(fileName, "utf-8");

		response.setContentType("application/octet-stream;charset=utf-8");
		response.setHeader("Content-Disposition", "attachment;filename*=utf-8'zh_cn'" + fileName + "");
		FileCopyUtils.copy(res, response.getOutputStream());
	}

	public Map<String, Object> getFallback(int id) {
		logger.info("UsersClient.get 失败备援！");
		// Place your code...
		Map<String, Object> result = new HashMap<String, Object>();

		result.put("code", 1);
		result.put("msg", "error");
		result.put("data", "get fail");
		return result;
	}

	public Map<String, Object> uploadFallback(int id, MultipartFile file) {
		logger.info("UsersClient.upload 失败备援！");
		// Place your code...
		Map<String, Object> result = new HashMap<String, Object>();

		result.put("code", 1);
		result.put("msg", "error");
		result.put("data", "upload fail");
		return null;
	}

	public void downloadFallback(HttpServletResponse response) {
		logger.info("UsersClient.download 失败备援！");
		// Place your code...

	}

}
