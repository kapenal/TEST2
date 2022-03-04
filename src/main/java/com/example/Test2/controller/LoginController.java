package com.example.Test2.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Test2.service.LoginService;
import com.example.Test2.vo.User;

@RestController
public class LoginController {
	@Autowired
	LoginService loginService;
	
	@PostMapping("/NotLogin/login")
	public String Login(HttpSession session, User user) {
		// System.out.println(user.toString());
		String id = loginService.login(user);
		session.setAttribute("loginId", id);
		return id;
	}	
}