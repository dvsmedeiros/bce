package com.dvsmedeiros.app.core.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dvsmedeiros.app.core.business.impl.HomeStrategy;
import com.dvsmedeiros.app.domain.Home;
import com.dvsmedeiros.bce.core.controller.business.impl.CodeValidator;
import com.dvsmedeiros.bce.core.controller.impl.Navigation;

@Configuration
public class HomeNavigation {
	
	@Autowired
	private HomeStrategy homeStrategy;
	
	@Autowired
	private CodeValidator codeValidator;
	
	@Bean(name = "NAME_NAVIGATION")
	public Navigation<Home> navigation() {

		Navigation<Home> activities = new Navigation<>();
		activities.addActivity(codeValidator);
		activities.addActivity(homeStrategy);
		return activities;
	}
}
