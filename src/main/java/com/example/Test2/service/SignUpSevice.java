package com.example.Test2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Test2.mapper.SignUpMapper;
import com.example.Test2.vo.User;

@Service
public class SignUpSevice {
	@Autowired
	SignUpMapper signUpMapper;
	
	public int insertSignUp(User user) {
		int result = 0;
		try {
			result = signUpMapper.insertSignUp(user);
		} catch (Exception e) {
		}
		return result;
	}

}
