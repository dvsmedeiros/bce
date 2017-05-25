package com.dvsmedeiros.bce.core.controller.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dvsmedeiros.bce.domain.ApplicationEntity;
import com.dvsmedeiros.bce.domain.DomainEntity;

@Repository
@SuppressWarnings("rawtypes")
public class FactoryDAO extends ApplicationEntity {

	@Autowired
	private Map<String, CrudRepository> daos;

	public CrudRepository create(Class<?> clazz) {

		CrudRepository dao = daos.get(clazz.getName());
		if (dao == null) {
			return daos.get(DomainEntity.class.getName());
		}
		return dao;
	}
}
