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
		String id = loginService.login(user);
		// 로그인 시 DB에 로그인한 ID를 가져와 session에 추가
		session.setAttribute("loginId", id);
		return id;
	}	
}