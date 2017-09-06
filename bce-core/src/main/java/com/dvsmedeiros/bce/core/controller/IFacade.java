package com.dvsmedeiros.bce.core.controller;

import com.dvsmedeiros.bce.domain.DomainEntity;
import com.dvsmedeiros.bce.domain.DomainSpecificEntity;
import com.dvsmedeiros.bce.domain.Filter;
import com.dvsmedeiros.bce.domain.IEntity;
import com.dvsmedeiros.bce.domain.Result;

public interface IFacade<T extends DomainEntity> extends IEntity {

	Result save(T aEntity, INavigationCase<T> aCase);

	Result update(T aEntity, INavigationCase<T> aCase);

	Result delete(T aEntity, INavigationCase<T> aCase);

	Result findAll(Class<? extends T> clazz, INavigationCase<T> aCase);

	Result find(Long id, INavigationCase<T> aCase);

	Result find(Filter<T> aFilter, INavigationCase<? extends IEntity> aCase);
	
	/*Methods for DomainSpecificEntity */
	
	Result findAll(Class<? extends DomainSpecificEntity> clazz, boolean active, INavigationCase<T> aCase);

	Result find(Class<? extends DomainSpecificEntity> clazz, String code, INavigationCase<T> aCase);

	Result inactivate(T entity);
	
	Result inactivate(Class<? extends DomainSpecificEntity> clazz, String code);



}
