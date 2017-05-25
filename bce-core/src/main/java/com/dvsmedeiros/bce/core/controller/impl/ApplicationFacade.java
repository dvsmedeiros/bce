package com.dvsmedeiros.bce.core.controller.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.dvsmedeiros.bce.core.controller.IFacade;
import com.dvsmedeiros.bce.core.controller.INavigationCase;
import com.dvsmedeiros.bce.core.repository.GenericRepository;
import com.dvsmedeiros.bce.core.repository.GenericSpecificRepository;
import com.dvsmedeiros.bce.domain.DomainEntity;
import com.dvsmedeiros.bce.domain.DomainSpecificEntity;
import com.dvsmedeiros.bce.domain.Filter;
import com.dvsmedeiros.bce.domain.Result;

@Component
@Transactional
@SuppressWarnings({ "unchecked", "rawtypes" })
public class ApplicationFacade<T extends DomainEntity> implements IFacade<T> {

	@Autowired
	private Navigator<T> navigator;

	@Autowired
	@Qualifier("genericRepository")
	private GenericRepository<T> repository;

	@Autowired
	@Qualifier("genericSpecificRepository")
	private GenericSpecificRepository<? extends DomainSpecificEntity> specificRepository;

	@Override
	public Result save(T aEntity, INavigationCase<T> aCase) {

		navigator.run(aEntity, aCase);
		if (!aCase.getResult().hasError() && !aCase.isSuspendExecution()) {
			repository.save(aEntity);
			aCase.getResult().addEntity(aEntity);
		}
		return aCase.getResult();

	}

	@Override
	public Result update(T aEntity, INavigationCase<T> aCase) {

		navigator.run(aEntity, aCase);
		if (!aCase.getResult().hasError() && !aCase.isSuspendExecution()) {
			repository.save(aEntity);
			aCase.getResult().addEntity(aEntity);
		}
		return aCase.getResult();
	}

	@Override
	public Result delete(T aEntity, INavigationCase<T> aCase) {

		if (aCase.getName().equals(BusinessCase.DEFAULT_CONTEXT_NAME)) {
			repository.delete(aEntity);
			aCase.getResult().addEntity(aEntity);
		}
		return aCase.getResult();
	}

	@Override
	public Result findAll(Class<? extends DomainEntity> clazz, INavigationCase<T> aCase) {

		if (aCase.getName().equals(BusinessCase.DEFAULT_CONTEXT_NAME)) {
			Iterable<T> source = repository.findAll();
			List list = new ArrayList<>();
			source.forEach(list::add);
			aCase.getResult().addEntities(list);
		}
		return aCase.getResult();
	}

	@Override
	public Result find(Long id, Class<? extends DomainEntity> clazz, INavigationCase<T> aCase) {

		if (aCase.getName().equals(BusinessCase.DEFAULT_CONTEXT_NAME)) {
			T aEntity = repository.findOne(id);
			aCase.getResult().addEntity(aEntity);
		}
		return aCase.getResult();
	}

	@Override
	public Result find(Filter<T> aFilter, INavigationCase<T> aCase) {

		navigator.run(aFilter, aCase);
		return aCase.getResult();

	}

	@Override
	public Result find(String code, Class<? extends DomainSpecificEntity> clazz, INavigationCase<T> aCase) {

		if (aCase.getName().equals(BusinessCase.DEFAULT_CONTEXT_NAME)) {
			T aEntity = (T) specificRepository.findByCode(code);
			aCase.getResult().addEntity(aEntity);
		}
		return aCase.getResult();
	}

	@Override
	public Result delete(String code, Class<? extends DomainSpecificEntity> clazz, INavigationCase<T> aCase) {
		if (aCase.getName().equals(BusinessCase.DEFAULT_CONTEXT_NAME)) {
			specificRepository.deleteByCode(code);
		}
		return aCase.getResult();
	}

	@Override
	public Result findAll(boolean active, Class<? extends DomainSpecificEntity> clazz, INavigationCase<T> aCase) {
		if (aCase.getName().equals(BusinessCase.DEFAULT_CONTEXT_NAME)) {
			List entityList = specificRepository.findByActive(active);
			aCase.getResult().addEntities(entityList);
		}
		return aCase.getResult();
	}

}
