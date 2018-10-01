package com.dvsmedeiros.bce.core.controller.impl;

import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.dvsmedeiros.bce.core.controller.IFacade;
import com.dvsmedeiros.bce.core.controller.INavigationCase;
import com.dvsmedeiros.bce.core.controller.INavigator;
import com.dvsmedeiros.bce.core.dao.impl.GenericDAO;
import com.dvsmedeiros.bce.domain.DomainEntity;
import com.dvsmedeiros.bce.domain.DomainSpecificEntity;
import com.dvsmedeiros.bce.domain.Filter;
import com.dvsmedeiros.bce.domain.IEntity;
import com.dvsmedeiros.bce.domain.Result;
import com.google.common.base.Strings;

@Component
@Transactional
public class ApplicationFacade<T extends DomainEntity> implements IFacade<T> {

	@Autowired
	@Qualifier("navigator")
	private INavigator<T> navigator;

	@Autowired
	@Qualifier("genericDAO")
	private GenericDAO<T> repository;

	@Override
	public Result save(T aEntity, INavigationCase<? extends IEntity> aCase) {

		navigator.run(aEntity, aCase);
		if (!aCase.getResult().hasError() && !aCase.isSuspendExecution()) {
			T saved = repository.save(aEntity);
			aCase.getResult().addEntity(saved);
		}
		return aCase.getResult();

	}

	@Override
	public Result update(T aEntity, INavigationCase<? extends IEntity> aCase) {

		navigator.run(aEntity, aCase);
		if (!aCase.getResult().hasError() && !aCase.isSuspendExecution()) {
			T updated = repository.update(aEntity);
			aCase.getResult().addEntity(updated);
		}
		return aCase.getResult();
	}

	@Override
	public Result delete(T aEntity, INavigationCase<? extends IEntity> aCase) {

		if (aCase.getName().equals(BusinessCase.DEFAULT_CONTEXT_NAME)) {
			T deleted = repository.delete(aEntity);
			aCase.getResult().addEntity(deleted);
		}
		return aCase.getResult();
	}

	@Override
	public Result findAll(Class<? extends T> clazz, INavigationCase<? extends IEntity> aCase) {

		if (aCase.getName().equals(BusinessCase.DEFAULT_CONTEXT_NAME)) {
			Optional<Stream<T>> findAll = repository.findAll(clazz);
			aCase.getResult().addEntity(Result.RESULTS_KEY, findAll.orElse(Stream.of()));
		}
		return aCase.getResult();
	}

	@Override
	public Result find(Long id, Class<? extends T> clazz) {

		Result result = new Result();
		repository.find(id, clazz).ifPresent(entity -> result.addEntity(entity));
		return result;
	}

	@Override
	public Result find(Filter<T> aFilter, INavigationCase<? extends IEntity> aCase) {
		navigator.run(aFilter, aCase);
		return aCase.getResult();
	}

	@Override
	public Result find(Class<? extends DomainSpecificEntity> clazz, String code,
			INavigationCase<? extends IEntity> aCase) {
		if (aCase.getName().equals(BusinessCase.DEFAULT_CONTEXT_NAME)) {
			repository.find(clazz, code).ifPresent(entity -> aCase.getResult().addEntity(entity));
		}
		return aCase.getResult();
	}

	@Override
	public Result inactivate(T entity) {

		BusinessCase<T> aCase = new BusinessCaseBuilder<T>().withName("DELETE_BY_CODE").build();

		if (entity != null && entity instanceof DomainSpecificEntity) {

			navigator.run(entity, aCase);
			if (!aCase.getResult().hasError() && !aCase.isSuspendExecution()) {
				repository.update(entity);
			}
			return aCase.getResult();
		}

		aCase.suspendExecution();
		aCase.getResult().setMessage("Entidade não é " + DomainSpecificEntity.class.getName());
		return aCase.getResult();
	}

	@Override
	public Result findAll(Class<? extends DomainSpecificEntity> clazz, boolean active, INavigationCase<? extends IEntity> aCase) {
		if (aCase.getName().equals(BusinessCase.DEFAULT_CONTEXT_NAME)) {
			repository.findAll(clazz, active).ifPresent(result -> aCase.getResult().addEntities(result));
		}
		return aCase.getResult();
	}

	@Override
	public Result inactivate(Class<? extends DomainSpecificEntity> clazz, String code) {

		BusinessCase<IEntity> aCase = new BusinessCaseBuilder<>().build();

		if (!Strings.isNullOrEmpty(code)) {
			repository.inactivate(clazz, code);
			return aCase.getResult();
		}

		aCase.isSuspendExecution();
		aCase.getResult().setMessage("Código inexistente ou inválido");

		return aCase.getResult();
	}
}
