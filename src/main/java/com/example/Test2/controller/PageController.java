package com.example.Test2.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
	
	@GetMapping("/LOGIN/page")
	public String Page() {
		return "Page";
	}
	
	@GetMapping(value={"/NotLogin/","/NotLogin/getLogin","/"})
	public String getLogin() {
		return "Login";
	}
	
	@GetMapping("/NotLogin/getSignUp")
	public String getSignUp() {
		System.out.println("에러");
		return "SignUp";
	}
	
	@GetMapping("/LOGIN/logOut")
	public String LogOut(HttpSession session) {
		// System.out.println("로그아웃");
		session.invalidate();
		return "redirect:/NotLogin/getLogin";
	}
}
