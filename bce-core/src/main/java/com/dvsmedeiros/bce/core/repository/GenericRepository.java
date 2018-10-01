package com.dvsmedeiros.bce.core.repository;

import java.util.stream.Stream;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.dvsmedeiros.bce.domain.DomainEntity;

public interface GenericRepository<T extends DomainEntity> extends CrudRepository<T, Long> {

	@Query("select t from #{#entityName} t where t.code = ?1")
	T findByCode(String code);

	@Query("select t from #{#entityName} t where t.active = ?1")
	Stream<T> findByActive(Boolean active);

	@Query("update #{#entityName} t set t.active = true where t.code = ?1")
	T activate(String code);

	@Query("update #{#entityName} t set t.active = false where t.code = ?1")
	T inactivate(String code);

}
