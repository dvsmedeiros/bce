package com.dvsmedeiros.bce.core.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.core.dao.IConfigurationDAO;
import com.dvsmedeiros.bce.domain.Configuration;

@Component
public class ConfigurationDAO extends GenericDAO<Configuration> implements IConfigurationDAO {
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Configuration findByCode(String code) {
		
		StringBuilder jpql = new StringBuilder();
		jpql.append("SELECT c FROM ");
		jpql.append(Configuration.class.getName());
		jpql.append(" c ");
		jpql.append(" WHERE c.code = :code ");
		
		TypedQuery<Configuration> query = em.createQuery(jpql.toString(), Configuration.class);	
		query.setParameter("code", code);
		
		return query.getSingleResult();
		
	}

}
