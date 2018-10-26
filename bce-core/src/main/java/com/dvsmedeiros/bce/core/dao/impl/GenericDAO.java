package com.dvsmedeiros.bce.core.dao.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

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

@SuppressWarnings("unchecked")
@Component
public class GenericDAO<T extends DomainEntity> extends ApplicationEntity implements IDAO<T> {

	@Autowired
	@Qualifier("genericRepository")
	protected GenericRepository<T> repository;

	@PersistenceContext
	protected EntityManager em;

	@Override
	public T save(T aEntity) {
		T saved = repository.save(aEntity);
		return saved;
	}

	@Override
	public T update(T aEntity) {
		T merged = em.merge(aEntity);
		return merged;
	}

	@Override
	public T delete(T aEntity) {
		repository.delete(aEntity);
		return aEntity;
	}

	@Override
	public Optional<Stream<T>> findAll(Class<? extends T> clazz) {

		StringBuilder jpql = new StringBuilder();
		jpql.append("SELECT e FROM ");
		jpql.append(clazz.getName());
		jpql.append(" e ");

		return Optional.of(em.createQuery(jpql.toString()).getResultList().stream());
	}

	@Override
	public Optional<T> find(Long id, Class<? extends T> clazz) {
		return Optional.ofNullable(em.find(clazz, id));
	}

	@Override
	public Optional<DomainSpecificEntity> find(Class<? extends DomainSpecificEntity> clazz, String code) {

		StringBuilder jpql = new StringBuilder();
		jpql.append("SELECT e FROM ");
		jpql.append(clazz.getName());
		jpql.append(" e ");
		jpql.append(" WHERE e.code = :code ");

		Query query = em.createQuery(jpql.toString());
		query.setParameter("code", code);

		List<?> resultList = query.getResultList();

		if (resultList != null && !resultList.isEmpty()) {
			DomainSpecificEntity specificEntity = (DomainSpecificEntity) resultList.get(0);
			return Optional.of(specificEntity);
		}
		getLogger(this.getClass()).info("Class: " + clazz.getName() + " code: " + code + " não foi encontrado!");
		return Optional.empty();
	}

	@Override
	public Optional<Stream<? extends DomainSpecificEntity>> findAll(Class<? extends DomainSpecificEntity> clazz,
			boolean active) {

		StringBuilder jpql = new StringBuilder();
		jpql.append("SELECT e FROM ");
		jpql.append(clazz.getName());
		jpql.append(" e ");
		jpql.append(" WHERE e.active = :active ");

		Query query = em.createQuery(jpql.toString());
		query.setParameter("active", active);

		return Optional.of(query.getResultList().stream());
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
	/*
	private DomainSpecificEntity createDomainSpecificEntityWithCode(Class<? extends DomainSpecificEntity> clazz, String aCode) {
		DomainSpecificEntity instance = null;
		try {			
			instance = (DomainSpecificEntity) Class.forName(clazz.getName()).newInstance();
			Method setCode = instance.getClass().getMethod("setCode", String.class);
			setCode.invoke(instance, aCode);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return instance;
	}
	*/
}
