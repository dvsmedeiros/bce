package com.dvsmedeiros.bce.core.repository;

import org.springframework.data.repository.CrudRepository;

import com.dvsmedeiros.bce.domain.DomainEntity;

public interface GenericRepository<T extends DomainEntity> extends CrudRepository<T, Long> {

}
