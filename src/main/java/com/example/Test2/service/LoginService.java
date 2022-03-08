package com.example.Test2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Test2.mapper.LoginMapper;
import com.example.Test2.vo.User;

@Service
public class LoginService {
	@Autowired
	LoginMapper loginMapper;
	
	public String login(User user) {
		String id = "";
		try {
			id = loginMapper.login(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}
}