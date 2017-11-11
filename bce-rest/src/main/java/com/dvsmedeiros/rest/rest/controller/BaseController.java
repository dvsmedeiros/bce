package com.dvsmedeiros.rest.rest.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dvsmedeiros.bce.domain.ApplicationEntity;

@CrossOrigin(origins = "*")
@RequestMapping("${bce.app.context}") 
public class BaseController extends ApplicationEntity {
	
	public Authentication getLoggedAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}
		
}
