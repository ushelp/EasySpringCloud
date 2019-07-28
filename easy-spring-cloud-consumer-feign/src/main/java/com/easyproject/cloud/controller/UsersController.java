package com.easyproject.cloud.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.easyproject.cloud.entity.Users;
import com.easyproject.cloud.feign.UsersClient;

@RestController
public class UsersController {

	Logger logger = LoggerFactory.getLogger(UsersController.class);

	@Resource
	UsersClient usersClient;

	@RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
	public Map<String, Object> get(@PathVariable int id) {

		logger.info("[Consumer] UsersController.get");
		logger.info("user id：" + id);

		Map<String, Object> result = new HashMap<String, Object>();

		Users users = usersClient.get(id);

		Map<String, Object> data = new HashMap<String, Object>();
		data.put("users", users);

		result.put("code", 0);
		result.put("msg", "ok");
		result.put("data", data);

		return result;
	}

	@RequestMapping(value = "/users/upload")
	public Map<String, Object> upload(int id, MultipartFile file) {
		logger.info("[Consumer] UsersController.upload");
		Map<String, Object> result = new HashMap<String, Object>();
		String res=usersClient.upload(id, file);

		result.put("code", 0);
		result.put("msg", "ok");
		result.put("data", res);
		return result;
	}

	@RequestMapping("/users/download")
	public void consumerDownload(HttpServletResponse response) throws IOException {

		logger.info("[Consumer] UsersController.download");
		Map<String, Object> result = new HashMap<String, Object>();

		ResponseEntity<byte[]> responseEntity = usersClient.download();
		byte[] res = responseEntity.getBody();
		String fileName = "Cat.jpg";
		fileName = URLEncoder.encode(fileName, "utf-8");

		response.setContentType("application/octet-stream;charset=utf-8");
		response.setHeader("Content-Disposition", "attachment;filename*=utf-8'zh_cn'" + fileName + "");
		FileCopyUtils.copy(res, response.getOutputStream());
	}

}
