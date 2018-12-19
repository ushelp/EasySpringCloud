package com.easyproject.cloud.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.UrlResource;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.easyproject.cloud.entity.Users;
import com.easyproject.cloud.service.UsersService;

@RestController
public class UsersController {

	Logger logger = LoggerFactory.getLogger(UsersController.class);

	@Resource
	private UsersService usersService;

	@RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
	public Users get(@PathVariable int id) {
		logger.info("[Provider] UsersController.get");
		return usersService.get(id);
	}

	@RequestMapping(value = "/users/upload")
	public String upload(int id, MultipartFile file) {
		logger.info("[Provider] UsersController.upload");
		logger.info(id+""); // 标题
		logger.info(file.getName()); // 表单name
		logger.info(file.getContentType()); // MIME
		logger.info(file.getOriginalFilename()); // 图片名称
		logger.info(file.getSize()+""); // 大小
		
		String fileName = UUID.randomUUID().toString() + file.getOriginalFilename();
		// TODO Write your upload code,  store the bytes somewhere...
		
		return fileName;
	}
	
	@RequestMapping(value = "/users/download")
	public void download(HttpServletRequest request,HttpServletResponse response) throws IOException {
		logger.info("[Provider] UsersController.download");
		UrlResource urlResource=new UrlResource("classpath:staticresources/images/cat.jpg");
		String fileName="Cat.jpg";
		fileName=URLEncoder.encode(fileName,"utf-8");
		response.setContentType("application/octet-stream;charset=utf-8");  
		response.setHeader("Content-Disposition", "attachment;filename*=utf-8'zh_cn'" + fileName + "");
		FileCopyUtils.copy(urlResource.getInputStream(), response.getOutputStream());
	}

}
