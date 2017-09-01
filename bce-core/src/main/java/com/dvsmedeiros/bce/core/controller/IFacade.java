package com.dvsmedeiros.bce.core.controller;

import com.dvsmedeiros.bce.domain.DomainEntity;
import com.dvsmedeiros.bce.domain.DomainSpecificEntity;
import com.dvsmedeiros.bce.domain.Filter;
import com.dvsmedeiros.bce.domain.IEntity;
import com.dvsmedeiros.bce.domain.Result;

public interface IFacade<T extends DomainEntity> extends IEntity {

	public Result save(T aEntity, INavigationCase<T> aCase);

	public Result update(T aEntity, INavigationCase<T> aCase);

	public Result delete(T aEntity, INavigationCase<T> aCase);

	public Result findAll(Class<? extends DomainEntity> clazz, INavigationCase<T> aCase);

	public Result find(Long id, Class<? extends DomainEntity> clazz, INavigationCase<T> aCase);

	public Result find(Filter<T> aFilter, INavigationCase<? extends IEntity> aCase);

	public Result find(String code, Class<? extends DomainSpecificEntity> clazz, INavigationCase<T> aCase);

	public Result delete(String code, Class<? extends DomainSpecificEntity> clazz, INavigationCase<T> aCase);

	public Result findAll(boolean active, Class<? extends DomainSpecificEntity> clazz, INavigationCase<T> aCase);

}
