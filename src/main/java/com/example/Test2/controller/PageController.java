package com.example.Test2.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PageController {
	@GetMapping("/LOGIN/page")
	public String Page() {
		return "Page";
	}
	
	@GetMapping(value={"/NotLogin/","/NotLogin/getLogin"})
	public String getLogin() {
		return "Login";
	}
	
	@GetMapping("/NotLogin/getSignUp")
	public String getSignUp(Model model,
			@RequestParam(required=false, defaultValue="1") int result) {
		System.out.println(result + "매개값");
		model.addAttribute("result", result);
		return "SignUp";
	}
	
	@GetMapping("/LOGIN/logOut")
	public String LogOut(HttpSession session) {
		System.out.println("로그아웃");
		System.out.println(session.getAttribute("loginId"));
		session.invalidate();
		return "redirect:/NotLogin/getLogin";
	}
}
