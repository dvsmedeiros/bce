package com.dvsmedeiros.bce.core.controller.impl;

import com.dvsmedeiros.bce.domain.IEntity;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class BusinessCaseBuilder<E extends IEntity> {

	protected BusinessCase aCase;
	
	public BusinessCaseBuilder() {
		this.aCase = new BusinessCase<E>();
	}

	public BusinessCaseBuilder<E> withName(String aBusinessCaseName) {
		aCase.setName(aBusinessCaseName);
		return this;
	}

	public BusinessCaseBuilder<E> forEntity(Class<? extends E> entity) {
		try {
			aCase.setEntity(entity.newInstance());
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return this;
	}

	public BusinessCase<E> build() {

		if (aCase.getName() == null || aCase.getName().isEmpty()) {
			aCase.setName(BusinessCase.DEFAULT_CONTEXT_NAME);
		}

		return aCase;
	}

}
