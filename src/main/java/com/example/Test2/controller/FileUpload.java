package com.example.Test2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FileUpload {
	
	@GetMapping("/LOGIN/file")
	public String test() {
		System.out.println("test");
		return "";
	}
}
