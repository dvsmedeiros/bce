package com.dvsmedeiros.rest.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dvsmedeiros.bce.core.controller.IFacade;
import com.dvsmedeiros.bce.core.controller.INavigator;
import com.dvsmedeiros.bce.core.controller.impl.BusinessCaseBuilder;
import com.dvsmedeiros.bce.domain.Configuration;

@Controller
@RequestMapping("configuration")
@SuppressWarnings("rawtypes")
public class ConfigurationController extends DomainSpecificEntityController<Configuration> {
	
	public ConfigurationController() {
		super(Configuration.class);
	}


	@Autowired
	@Qualifier("applicationFacade")
	private IFacade<Configuration> appFacade;
	
	@Autowired
	@Qualifier("navigator")
	private INavigator navigator;
		
	@Override
	@CacheEvict(value = "cacheConfigurations", allEntries = true)
	public ResponseEntity updateEntity(@RequestBody Configuration entity) {
		
		super.updateEntity(entity);
		
		navigator.run(entity, new BusinessCaseBuilder<>().withName("RESTART_SCHEDULER").build());
		return new ResponseEntity<Configuration>(HttpStatus.OK);
		
	}
	
}
