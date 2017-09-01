package com.dvsmedeiros.app.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dvsmedeiros.app.domain.Home;
import com.dvsmedeiros.bce.core.controller.impl.ApplicationFacade;
import com.dvsmedeiros.bce.core.controller.impl.BusinessCase;
import com.dvsmedeiros.bce.core.controller.impl.BusinessCaseBuilder;
import com.dvsmedeiros.bce.core.controller.impl.Navigator;
import com.dvsmedeiros.bce.domain.Result;

@Controller
public class HomeController extends BaseController {

	@Autowired
	@Qualifier("applicationFacade")
	private ApplicationFacade<Home> appFacade;

	@Autowired
	@Qualifier("navigator")
	private Navigator<Home> navigator;

	@RequestMapping(value = "/home", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Home> getHome(@RequestBody Home home) {
		try {
			BusinessCase<Home> aCase = new BusinessCaseBuilder<Home>().build();
			appFacade.save(home, new BusinessCaseBuilder<Home>().build());
			if (!aCase.isSuspendExecution() && !aCase.getResult().hasError()) {
				return new ResponseEntity<Home>(home, HttpStatus.CREATED);
			}
			return new ResponseEntity<Home>(home, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			return new ResponseEntity<Home>(home, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/home", method = RequestMethod.PUT)
	public @ResponseBody ResponseEntity<Home> putHome(@RequestBody Home home) {

		try {
			BusinessCase<Home> aCase = new BusinessCaseBuilder<Home>().build();
			appFacade.update(home, new BusinessCaseBuilder<Home>().build());
			if (!aCase.isSuspendExecution() && !aCase.getResult().hasError()) {
				return new ResponseEntity<Home>(home, HttpStatus.OK);
			}
			return new ResponseEntity<Home>(home, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			return new ResponseEntity<Home>(home, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/home/{homeId}", method = RequestMethod.DELETE)
	public @ResponseBody ResponseEntity<Home> deleteHome(@PathVariable String homeCode) {

		try {
			BusinessCase<Home> aCase = new BusinessCaseBuilder<Home>().build();
			appFacade.delete(homeCode, Home.class, aCase);
			if (!aCase.isSuspendExecution() && !aCase.getResult().hasError()) {
				return new ResponseEntity<>(HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<Home>> getHome() {

		try {
			BusinessCase<Home> aCase = new BusinessCaseBuilder<Home>().build();
			Result result = appFacade.findAll(Home.class, aCase);
			List<Home> entities = result.getEntities();
			if (!aCase.isSuspendExecution() && !aCase.getResult().hasError() && !entities.isEmpty()) {
				return new ResponseEntity<List<Home>>(entities, HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@RequestMapping(value = "home/hello", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<String> sayHello() {

		BusinessCase<Home> aCase = new BusinessCaseBuilder<Home>().withName("NAME_NAVIGATION").build();
		navigator.run(new Home(), aCase);
		String attribute = aCase.getContext().getAttribute("message");
		return new ResponseEntity<>(attribute, HttpStatus.OK);
	}
}
