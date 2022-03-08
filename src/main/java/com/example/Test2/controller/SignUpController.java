package com.example.Test2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Test2.service.SignUpSevice;
import com.example.Test2.vo.User;

@RestController
public class SignUpController {
	@Autowired
	SignUpSevice signUpService;

	@PostMapping("/NotLogin/insertSignUp")
	public int insertSignUp(User user) {
		int result = signUpService.insertSignUp(user);
		return result;
	}
	
	
}
