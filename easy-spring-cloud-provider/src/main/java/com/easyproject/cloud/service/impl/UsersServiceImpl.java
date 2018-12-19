package com.easyproject.cloud.service.impl;

import org.springframework.stereotype.Service;

import com.easyproject.cloud.entity.Users;
import com.easyproject.cloud.service.UsersService;

@Service("usersService")
public class UsersServiceImpl implements UsersService {

	@Override
	public Users get(int id) {
		if(id>=10000) {
			return new Users(id, "User_"+id, "HK");
		}
		return null;
	}

	
	
}
