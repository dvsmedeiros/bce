package com.dvsmedeiros.bce.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.dvsmedeiros.bce.domain.DomainSpecificEntity;


public interface GenericSpecificRepository<T extends DomainSpecificEntity>
		extends GenericRepository<DomainSpecificEntity> {

	T findByCode(String code);

	List<T> findByActive(Boolean active);

	@Modifying
    @Query("delete from DomainSpecificEntity d where d.code = ?1")
	void deleteByCode(String code);
}
