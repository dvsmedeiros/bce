package com.dvsmedeiros.bce.core.controller.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.domain.IEntity;

@SuppressWarnings({ "rawtypes" })
@Component
public class BusinessCaseBuilder<E extends IEntity> {

	public static final String SAVE = "SAVE_";
	public static final String UPTDATE = "UPDATE_";
	public static final String DELETE = "DELETE_";
	public static final String FILTER = "FILTER_";
	public static final String ACTIVATE = "ACTIVATE_";
	public static final String INACTIVATE = "INACTIVATE_";

	private Map<String, Navigation<E>> listNavigations = new HashMap<String, Navigation<E>>();

	protected BusinessCase aCase;

	public BusinessCaseBuilder() {

		this.aCase = new BusinessCase<E>();
	}

	public BusinessCase<E> defaultContext() {
		return new BusinessCase<>(BusinessCase.DEFAULT_CONTEXT_NAME);
	}

	public BusinessCase<E> withName(String name) {
		return new BusinessCase<>(name.trim().toUpperCase());
	}

	public BusinessCase<E> save(String name) {
		name = existingNavigation(SAVE.concat(name.trim().toUpperCase())).orElse(BusinessCase.DEFAULT_CONTEXT_NAME)
				.trim().toUpperCase();
		return new BusinessCase<>(name);
	}

	public BusinessCase<E> update(String name) {
		name = existingNavigation(UPTDATE.concat(name.trim().toUpperCase())).orElse(BusinessCase.DEFAULT_CONTEXT_NAME)
				.trim().toUpperCase();
		return new BusinessCase<>(name);
	}

	public BusinessCase<E> delete(String name) {
		name = existingNavigation(DELETE.concat(name.trim().toUpperCase())).orElse(BusinessCase.DEFAULT_CONTEXT_NAME)
				.trim().toUpperCase();
		return new BusinessCase<>(name);
	}

	public BusinessCase<E> filter(String name) {
		name = existingNavigation(FILTER.concat(name.trim().toUpperCase())).orElse(BusinessCase.DEFAULT_CONTEXT_NAME)
				.trim().toUpperCase();
		return new BusinessCase<>(name);
	}

	public BusinessCase<E> activate(String name) {
		name = existingNavigation(ACTIVATE.concat(name.trim().toUpperCase())).orElse(BusinessCase.DEFAULT_CONTEXT_NAME)
				.trim().toUpperCase();
		return new BusinessCase<>(name);
	}

	public BusinessCase<E> inactivate(String name) {
		name = existingNavigation(INACTIVATE.concat(name.trim().toUpperCase()))
				.orElse(BusinessCase.DEFAULT_CONTEXT_NAME).trim().toUpperCase();
		return new BusinessCase<>(name);
	}

	private Optional<String> existingNavigation(String name) {
		return this.listNavigations.containsKey(name) ? Optional.of(name) : Optional.empty();
	}

}
