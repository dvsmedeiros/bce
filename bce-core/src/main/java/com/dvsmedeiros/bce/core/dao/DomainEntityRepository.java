package com.dvsmedeiros.bce.core.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.dvsmedeiros.bce.domain.DomainSpecificEntity;

@Component
public interface DomainEntityRepository<T extends DomainSpecificEntity> extends CrudRepository<T, Long> {

	T findByCode(String code);
	
	List<T> findByActive(Boolean active);

	@Modifying
	@Transactional
	@Query("delete from DomainSpecificEntity d where d.code = ?1")
	void deleteByCode(String code);
}
