package com.example.demo.base;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.WebRequest;

@Controller
public class AppErrorController implements ErrorController {
	private final  Logger logger = LoggerFactory.getLogger(AppErrorController.class);
	private static final String ERROR_PATH = "/error";

	private ErrorAttributes errorAttributes;

	/**
	 * set方法注入errorAttributes
	 **/
	public AppErrorController(ErrorAttributes errorAttributes) {
		this.errorAttributes = errorAttributes;
	}

	@Override
	public String getErrorPath() {

		System.out.println("=======执行ErrorController的getErrorPath()方法==============");

		return ERROR_PATH;
	}

	@RequestMapping(value = ERROR_PATH, produces = "text/html")
	public String errorPageHandler(HttpServletRequest request, HttpServletResponse response) {
		int status = response.getStatus();
		switch (status) {
		case 403:
			return "403";// 到403页面
		case 404:
			return "404";
		case 500:
			return "500";
		}
		// 没有符合case的，默认返回index页面
		return "index";
	}

	/***
	 * 用于处理错误信息 用于处理还未到达页面就抛出异常的情况，和页面不同
	 **/

	@RequestMapping(value = ERROR_PATH)
	@ResponseBody
	public ApiResponse errorApiHandler(HttpServletRequest request) {
		RequestAttributes requestAttributes = new ServletRequestAttributes(request);
		Map<String, Object> attr = this.errorAttributes.getErrorAttributes((WebRequest) requestAttributes, false);
		Integer status = getStatus(request);
		return ApiResponse.ofMessage(status, String.valueOf(attr.getOrDefault("message====", "error=======")));

	}

	private Integer getStatus(HttpServletRequest request) {
		Integer status = (Integer) request.getAttribute("javax.servlet.error.status_code");
		if (status != null) {
			return status;
		}
		return 500;
	}

}
