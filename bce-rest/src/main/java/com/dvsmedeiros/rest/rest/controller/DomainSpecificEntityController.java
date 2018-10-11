package com.dvsmedeiros.rest.rest.controller;

import java.util.Optional;

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

	@RequestMapping(value = "inactivate/{id}", method = RequestMethod.PUT)
	public @ResponseBody ResponseEntity inactivateEntityById(@PathVariable Long id) {

		try {
			Optional<T> hasEntity = appFacade.find(id, clazz).getEntity();
			if (hasEntity.isPresent()) {
				Result result = appFacade.inactivate(hasEntity.get());
				hasEntity = result.getEntity();
				if (result.hasError() || !hasEntity.isPresent()) {
					return ResponseMessage.serverError(result.getMessage());
				}
				return ResponseEntity.ok(hasEntity.get());
			}
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.serverError("Erro ao inativar ".concat(clazz.getSimpleName()));
		}
	}

	@RequestMapping(value = "activate/{id}", method = RequestMethod.PUT)
	public @ResponseBody ResponseEntity activateEntityById(@PathVariable Long id) {

		try {
			Optional<T> hasEntity = appFacade.find(id, clazz).getEntity();
			if (hasEntity.isPresent()) {
				Result result = appFacade.activate(hasEntity.get());
				hasEntity = result.getEntity();
				if (result.hasError() || !hasEntity.isPresent()) {
					return ResponseMessage.serverError(result.getMessage());
				}
				return ResponseEntity.ok(hasEntity.get());
			}
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.serverError("Erro ativar ".concat(clazz.getSimpleName()));
		}
	}
}
