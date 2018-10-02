package com.dvsmedeiros.rest.rest.controller;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dvsmedeiros.bce.core.controller.IFacade;
import com.dvsmedeiros.bce.core.controller.INavigator;
import com.dvsmedeiros.bce.core.controller.impl.BusinessCase;
import com.dvsmedeiros.bce.core.controller.impl.BusinessCaseBuilder;
import com.dvsmedeiros.bce.domain.DomainEntity;
import com.dvsmedeiros.bce.domain.Filter;
import com.dvsmedeiros.bce.domain.Result;
import com.dvsmedeiros.rest.domain.ResponseMessage;

@SuppressWarnings("rawtypes")
public abstract class DomainEntityController<T extends DomainEntity> extends BaseController {

	@Autowired
	@Qualifier("applicationFacade")
	protected IFacade<T> appFacade;

	@Autowired
	@Qualifier("navigator")
	protected INavigator<T> navigator;

	protected Class<? extends T> clazz;

	public DomainEntityController(Class<? extends T> clazz) {
		this.clazz = clazz;
	}

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody ResponseEntity createEntity(@RequestBody T entity) {
		
		try {
			BusinessCase<?> bCase = BusinessCaseBuilder.save(clazz.getSimpleName());
			Result result = appFacade.save(entity, bCase);
			if (result.hasError()) {
				return ResponseMessage.serverError(result.getMessage());
			}			
			return ResponseEntity.status(HttpStatus.CREATED).body(entity);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.serverError("Erro ao cadastrar ".concat(clazz.getSimpleName()));
		}
		
	}

	@RequestMapping(method = RequestMethod.PUT)
	public @ResponseBody ResponseEntity updateEntity(@RequestBody T entity) {

		try {

			BusinessCase<?> bCase = BusinessCaseBuilder.update(clazz.getSimpleName());
			Result result = appFacade.update(entity, bCase);
			if (result.hasError()) {
				return ResponseMessage.serverError(result.getMessage());
			}
			return ResponseEntity.ok(entity);

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.serverError("Erro ao atualizar ".concat(clazz.getSimpleName()));			
		}

	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity getEntityById(@PathVariable Long id) {

		try {

			Result result = appFacade.find(id, clazz);
			Optional<T> t = result.getEntity();
			if (t.isPresent()) {
				return ResponseEntity.ok(t.get());
			}
			return ResponseEntity.noContent().build();

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.serverError("Erro ao consultar " + clazz.getSimpleName() + " com id: " + id);
		}

	}

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody ResponseEntity getEntities() {

		try {

			BusinessCase<?> aCase = BusinessCaseBuilder.defaultContext();
			Result result = appFacade.findAll(clazz, aCase);
			Optional<Stream<T>> ts = result.getEntities();

			if (ts.isPresent() && Stream.of(ts.get()).count() > 0) {
				return ResponseEntity.ok(ts.get());
			}
			return ResponseEntity.noContent().build();

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.serverError("Erro ao consultar " + clazz.getSimpleName());
		}

	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public @ResponseBody ResponseEntity deleteEntityById(@PathVariable Long id) {

		try {

			BusinessCase<?> bCase = BusinessCaseBuilder.delete(clazz.getSimpleName());
			Optional<T> hasEntity = appFacade.find(id, clazz).getEntity();
			hasEntity.ifPresent(entity -> {
				appFacade.delete(entity, bCase);				
			});
			return ResponseEntity.ok().build();

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.serverError("Erro ao remover ".concat(clazz.getSimpleName()));
		}

	}

	@RequestMapping(value = "filter", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity findEntityByFilter(@RequestBody Filter<T> filter, @RequestParam(name = "logged", required = false) boolean logged) {

		try {

			BusinessCase<?> bCase = BusinessCaseBuilder.filter(clazz.getSimpleName());
			Result result = appFacade.find(filter, bCase);
			List<T> ts = result.getEntities();

			return new ResponseEntity<>(ts == null ? Collections.emptyList() : ts, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.serverError("Erro ao filtar ".concat(clazz.getSimpleName()));
		}

	}

}
