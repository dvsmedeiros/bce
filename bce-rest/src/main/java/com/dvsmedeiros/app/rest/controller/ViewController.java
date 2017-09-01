package com.dvsmedeiros.app.rest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {
	
	@RequestMapping("/")
	public String indexPage(){
	    return "index";
	}
	
	@RequestMapping("/home")
	public String home() {
		return "home";
	}
}
