package com.dvsmedeiros.app.core.business.impl;

import org.springframework.stereotype.Component;

import com.dvsmedeiros.app.domain.Home;
import com.dvsmedeiros.bce.core.controller.INavigationCase;
import com.dvsmedeiros.bce.core.controller.business.IStrategy;

@Component
public class HomeStrategy implements IStrategy<Home>{

	@Override
	public void process(Home aEntity, INavigationCase<Home> aCase) {
		aCase.getContext().setAttribute("message", "Context Navigation Attribute");
	}

}
