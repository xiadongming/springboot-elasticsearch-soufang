package com.example.demo.web.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.web.WebController;

@Controller
public class AdminController {
	private final  Logger logger = LoggerFactory.getLogger(AdminController.class);
	@RequestMapping("/admin/center")
	public String adminCenterPage() {
		return "admin/center";
	}

	@RequestMapping("/admin/welcome")
	public String welcomePage() {
		return "admin/welcome";
	}
	
	@RequestMapping("/admin/login")
	public String login() {
		return "admin/login";
	}

	@RequestMapping("/admin/add")
	public String houseAdd() {
		return "admin/house-add";
	}

	
	
	
}
