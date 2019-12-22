package com.example.demo.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/web")
public class WebController {
	private final  Logger logger = LoggerFactory.getLogger(WebController.class);
	@RequestMapping("/html")
	public String getController() {
		
		return "success";
	}
	
	
	
}
