package com.dvsmedeiros.bce.core.dao;

import java.util.Optional;
import java.util.stream.Stream;

import com.dvsmedeiros.bce.domain.DomainEntity;
import com.dvsmedeiros.bce.domain.DomainSpecificEntity;
import com.dvsmedeiros.bce.domain.IEntity;

public interface IDAO<T extends DomainEntity> extends IEntity {

	T save(T aEntity);

	T update(T aEntity);

	T delete(T aEntity);

	Optional<Stream<T>> findAll(Class<? extends T> clazz);

	Optional<T> find(Long id, Class<? extends T> clazz);

	/* Methods for DomainSpecificEntity */

	Optional<Stream<? extends DomainSpecificEntity>> findAll(Class<? extends DomainSpecificEntity> clazz, boolean active);

	Optional<DomainSpecificEntity> find(Class<? extends DomainSpecificEntity> clazz, String code);

	Optional<DomainSpecificEntity> inactivate(Class<? extends DomainSpecificEntity> clazz, String code);

	Optional<DomainSpecificEntity> activate(Class<? extends DomainSpecificEntity> clazz, String code);

}
