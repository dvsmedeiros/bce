package com.dvsmedeiros.bce.core.repository;

import java.util.List;

import com.dvsmedeiros.bce.domain.DomainSpecificEntity;


public interface GenericSpecificRepository<T extends DomainSpecificEntity>
		extends GenericRepository<DomainSpecificEntity> {

	T findByCode(String code);

	List<T> findByActive(Boolean active);

	void deleteByCode(String code);
}
