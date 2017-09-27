package com.dvsmedeiros.rest.rest.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dvsmedeiros.bce.core.controller.IFacade;
import com.dvsmedeiros.bce.core.controller.impl.BusinessCase;
import com.dvsmedeiros.bce.core.controller.impl.BusinessCaseBuilder;
import com.dvsmedeiros.bce.core.controller.impl.Navigation;
import com.dvsmedeiros.bce.domain.DomainEntity;
import com.dvsmedeiros.bce.domain.Filter;
import com.dvsmedeiros.bce.domain.Result;
import com.dvsmedeiros.rest.domain.ResponseMessage;

@SuppressWarnings("rawtypes")
public class DomainEntityController<T extends DomainEntity> extends BaseController {

	@Autowired
	private Map<String, Navigation<T>> listNavigations;

	@Autowired
	@Qualifier("applicationFacade")
	private IFacade<T> appFacade;

	protected Class<? extends T> clazz;

	public DomainEntityController(Class<? extends T> clazz) {
		this.clazz = clazz;
	}

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody ResponseEntity createEntity(@RequestBody T entity) {

		try {

			BusinessCase<T> bCase = new BusinessCaseBuilder<T>()
					.withName(existingNavigation("SAVE_".concat(clazz.getSimpleName().toUpperCase()))).build();

			Result result = appFacade.save(entity, bCase);

			if (result.hasError()) {
				return new ResponseEntity<>(new ResponseMessage(Boolean.TRUE, result.getMessage()),
						HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return new ResponseEntity<>(entity, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(
					new ResponseMessage(Boolean.TRUE, "Erro ao cadastrar ".concat(clazz.getSimpleName().toLowerCase())),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@RequestMapping(method = RequestMethod.PUT)
	public @ResponseBody ResponseEntity updateEntity(@RequestBody T entity) {

		try {

			BusinessCase<T> bCase = new BusinessCaseBuilder<T>()
					.withName(existingNavigation("UPDATE_".concat(clazz.getSimpleName().toUpperCase()))).build();

			Result result = appFacade.update(entity, bCase);

			if (result.hasError()) {
				return new ResponseEntity<>(new ResponseMessage(Boolean.TRUE, result.getMessage()),
						HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return new ResponseEntity<>(entity, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(
					new ResponseMessage(Boolean.TRUE, "Erro ao atualizar ".concat(clazz.getSimpleName().toLowerCase())),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity getEntityById(@PathVariable Long id) {

		try {

			Result result = appFacade.find(id, clazz);
			T t = result.getEntity();

			return new ResponseEntity<>(t, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody ResponseEntity getEntities() {

		try {
			
			BusinessCase<T> aCase = new BusinessCaseBuilder<T>().build();
			Result result = appFacade.findAll(clazz, aCase);
			List<T> ts = result.getEntities();

			return new ResponseEntity<>(ts, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public @ResponseBody ResponseEntity deleteEntityById(@PathVariable Long id) {

		try {
			
			BusinessCase<T> bCase = new BusinessCaseBuilder<T>()
					.withName(existingNavigation("DELETE_".concat(clazz.getSimpleName().toUpperCase()))).build();
			
			T entity = appFacade.find(id, clazz).getEntity();
			appFacade.delete(entity, bCase);

			return new ResponseEntity<>(HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(
					new ResponseMessage(Boolean.TRUE, "Erro ao remover ".concat(clazz.getSimpleName().toLowerCase())),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@RequestMapping(value = "filter", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity findBooksByFilter(@RequestBody Filter<T> filter) {
		
		try {
			
			BusinessCase<Filter<T>> bCase = new BusinessCaseBuilder<Filter<T>>()
					.withName("FILTER_".concat(clazz.getSimpleName().toUpperCase())).build();
			Result result = appFacade.find(filter, bCase);
			List<T> ts = result.getEntities();

			return new ResponseEntity<>(ts == null? Collections.emptyList() : ts, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	protected String existingNavigation(String name) {
		return listNavigations.containsKey(name) ? name : BusinessCase.DEFAULT_CONTEXT_NAME;
	}
}
