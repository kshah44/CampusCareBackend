package com.springboot.angular.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
 
@RestController
public class WebRestController {
 
	@RequestMapping("/hi")
	public String hi() {
		return "Hi Everyone!!!";
	}
}
