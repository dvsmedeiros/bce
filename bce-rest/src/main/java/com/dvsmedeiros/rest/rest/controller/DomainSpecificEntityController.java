package com.dvsmedeiros.rest.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dvsmedeiros.bce.domain.DomainSpecificEntity;
import com.dvsmedeiros.bce.domain.Result;
import com.dvsmedeiros.rest.domain.ResponseMessage;

@SuppressWarnings("rawtypes")
public abstract class DomainSpecificEntityController<T extends DomainSpecificEntity> extends DomainEntityController<T> {

	public DomainSpecificEntityController(Class<? extends T> clazz) {
		super(clazz);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	public @ResponseBody ResponseEntity inactivateEntityById(@PathVariable Long id) {

		try {

			T entity = appFacade.find(id, clazz).getEntity();
			Result result = appFacade.inactivate(entity);

			if (result.hasError()) {
				return new ResponseEntity<>(new ResponseMessage(Boolean.TRUE, result.getMessage()),
						HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return new ResponseEntity<>(HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(
					new ResponseMessage(Boolean.TRUE, "Erro ao remover ".concat(clazz.getSimpleName().toLowerCase())),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
