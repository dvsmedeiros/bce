package com.dvsmedeiros.bce.core.controller.impl;

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
	
	@Autowired
	private Map<String, Navigation<?>> listNavigations;
	private static Map<String, Navigation<?>> staticListNavigations;
	
	protected BusinessCase aCase;
	
	public BusinessCaseBuilder() {
		BusinessCaseBuilder.staticListNavigations = listNavigations;		
		this.aCase = new BusinessCase<E>();
	}

	//public BusinessCaseBuilder<E> withName(String aBusinessCaseName) {
	//	aCase.setName(aBusinessCaseName);
	//	return this;
	//}

	//public BusinessCaseBuilder<E> forEntity(Class<? extends E> entity) {
	//	try {
	//		aCase.setEntity(entity.newInstance());
	//	} catch (InstantiationException | IllegalAccessException e) {
	//		e.printStackTrace();
	//		}
	//	return this;
	//}

	//public BusinessCase<E> build() {
	//
	//	if (aCase.getName() == null || aCase.getName().isEmpty()) {
	//		aCase.setName(BusinessCase.DEFAULT_CONTEXT_NAME);
	//	}
	//	return aCase;
	//}
	
	public static BusinessCase<?> defaultContext() {
		return new BusinessCase<>(BusinessCase.DEFAULT_CONTEXT_NAME);
	}
	
	public static BusinessCase<?> withName(String name) {
		return new BusinessCase<>(name.trim().toUpperCase());
	}
	
	
	public static BusinessCase<?> save(String name) {
		name = existingNavigation(SAVE.concat(name.trim().toUpperCase())).orElse(BusinessCase.DEFAULT_CONTEXT_NAME).trim().toUpperCase();
		return new BusinessCase<>(name);
	}
	
	public static BusinessCase<?> update(String name) {
		name = existingNavigation(UPTDATE.concat(name.trim().toUpperCase())).orElse(BusinessCase.DEFAULT_CONTEXT_NAME).trim().toUpperCase();
		return new BusinessCase<>(name);
	}
	
	public static BusinessCase<?> delete(String name) {
		name = existingNavigation(DELETE.concat(name.trim().toUpperCase())).orElse(BusinessCase.DEFAULT_CONTEXT_NAME).trim().toUpperCase();
		return new BusinessCase<>(name);
	}

	public static BusinessCase<?> filter(String name) {
		name = existingNavigation(FILTER.concat(name.trim().toUpperCase())).orElse(BusinessCase.DEFAULT_CONTEXT_NAME).trim().toUpperCase();
		return new BusinessCase<>(name);
	}
	
	public static BusinessCase<?> activate(String name) {
		name = existingNavigation(ACTIVATE.concat(name.trim().toUpperCase())).orElse(BusinessCase.DEFAULT_CONTEXT_NAME).trim().toUpperCase();
		return new BusinessCase<>(name);
	}
	
	public static BusinessCase<?> inactivate(String name) {
		name = existingNavigation(INACTIVATE.concat(name.trim().toUpperCase())).orElse(BusinessCase.DEFAULT_CONTEXT_NAME).trim().toUpperCase();
		return new BusinessCase<>(name);
	}
	
	private static Optional<String> existingNavigation(String name) {		
		return BusinessCaseBuilder.staticListNavigations.containsKey(name) ? Optional.of(name) : Optional.empty();
	}
		
}
