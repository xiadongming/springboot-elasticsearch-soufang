package com.example.demo.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.base.ApiResponse;
import com.example.demo.service.impl.UserServiceimpl;

@Controller
public class HomeWebController {
	private final  Logger logger = LoggerFactory.getLogger(HomeWebController.class);
	@RequestMapping("/home")
	public String thyWeb() {
		return "index";
	}

	@RequestMapping("/response")
	@ResponseBody
	public ApiResponse WebApiResponse() {

		return ApiResponse.ofMessage(200, "返回成功");
	}

	@RequestMapping("/404")
	public String notFoundPage() {
		return "404";
	}

	@RequestMapping("/403")
	public String accessErrorPage() {
		return "403";
	}

	@RequestMapping("/500")
	public String internalError() {
		return "500";
	}

	@RequestMapping("/logout/page")
	public String logout() {
		return "logout";
	}
	@RequestMapping("/login")
	public String login() {
		return "login";
	}

}
