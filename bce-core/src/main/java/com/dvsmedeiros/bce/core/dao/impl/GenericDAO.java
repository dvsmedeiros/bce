package com.dvsmedeiros.bce.core.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.core.dao.IDAO;
import com.dvsmedeiros.bce.core.repository.GenericRepository;
import com.dvsmedeiros.bce.domain.ApplicationEntity;
import com.dvsmedeiros.bce.domain.DomainEntity;
import com.dvsmedeiros.bce.domain.DomainSpecificEntity;

@Component
public class GenericDAO<T extends DomainEntity> extends ApplicationEntity implements IDAO<T> {

	@Autowired
	@Qualifier("genericRepository")
	private GenericRepository<T> repository;

	@PersistenceContext
	private EntityManager em;

	@Override
	public void save(T aEntity) {
		repository.save(aEntity);
	}

	@Override
	public void update(T aEntity) {
		em.merge(aEntity);
	}

	@Override
	public void delete(T aEntity) {
		repository.delete(aEntity);
	}

	@Override
	public List<T> findAll(Class<? extends T> clazz) {

		StringBuilder jpql = new StringBuilder();
		jpql.append("SELECT e FROM ");
		jpql.append(clazz.getName());
		jpql.append(" e ");
				
		return em.createQuery(jpql.toString()).getResultList();
	}

	@Override
	public T find(Long id, Class<? extends T> clazz) {

		return em.find(clazz, id);
	}

	@Override
	public DomainSpecificEntity find(Class<? extends DomainSpecificEntity> clazz, String code) {

		StringBuilder jpql = new StringBuilder();
		jpql.append("SELECT e FROM ");
		jpql.append(clazz.getName());
		jpql.append(" e ");
		jpql.append(" WHERE e.code = :code ");

		Query query = em.createQuery(jpql.toString());
		query.setParameter("code", code);

		List<?> resultList = query.getResultList();

		if (resultList != null && !resultList.isEmpty()) {
			return (DomainSpecificEntity) resultList.get(0);
		}

		getLogger(this.getClass()).info("Class: " + clazz.getName() + " code: " + code + " não foi encontrado!");
		return null;

	}

	@Override
	public List<? extends DomainSpecificEntity> findAll(Class<? extends DomainSpecificEntity> clazz, boolean active) {

		StringBuilder jpql = new StringBuilder();
		jpql.append("SELECT e FROM ");
		jpql.append(clazz.getName());
		jpql.append(" e ");
		jpql.append(" WHERE e.active = :active ");

		Query query = em.createQuery(jpql.toString());
		query.setParameter("active", active);

		return query.getResultList();
	}

	@Override
	public void inactivate(Class<? extends DomainSpecificEntity> clazz, String code) {

		StringBuilder jpql = new StringBuilder();
		jpql.append("UPDATE ");
		jpql.append(clazz.getName());
		jpql.append(" e ");
		jpql.append(" SET e.active = false ");
		jpql.append(" WHERE e.code = :code ");

		Query query = em.createQuery(jpql.toString());
		query.setParameter("code", code);

		query.executeUpdate();

	}

	@Override
	public void activate(Class<? extends DomainSpecificEntity> clazz, String code) {

		StringBuilder jpql = new StringBuilder();
		jpql.append("UPDATE ");
		jpql.append(clazz.getName());
		jpql.append(" e ");
		jpql.append(" SET e.active = true ");
		jpql.append(" WHERE e.code = :code ");

		Query query = em.createQuery(jpql.toString());
		query.setParameter("code", code);

		query.executeUpdate();

	}

}
