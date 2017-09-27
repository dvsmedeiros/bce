package com.dvsmedeiros.bce.core.dao;

import java.util.List;

import com.dvsmedeiros.bce.domain.DomainEntity;
import com.dvsmedeiros.bce.domain.DomainSpecificEntity;
import com.dvsmedeiros.bce.domain.IEntity;

public interface IDAO<T extends DomainEntity> extends IEntity {

	void save(T aEntity);

	void update(T aEntity);
	
	void delete(T aEntity);

	List<T> findAll(Class<? extends T> clazz);

	T find(Long id, Class<? extends T> clazz);
	
	/* Methods for DomainSpecificEntity */
	
	List<? extends DomainSpecificEntity> findAll(Class<? extends DomainSpecificEntity> clazz, boolean active);

	DomainSpecificEntity find(Class<? extends DomainSpecificEntity> clazz, String code);

	void inactivate(Class<? extends DomainSpecificEntity> clazz, String code);
	
	void activate(Class<? extends DomainSpecificEntity> clazz, String code);
	
}
