package com.dvsmedeiros.bce.domain;

public class Filter<T extends DomainEntity> extends ApplicationEntity {

	private T entity;
	
	public Filter() {
	}
	
	public Filter(Class<? extends T> clazz) {
		try {
			this.entity = clazz.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e ) {
			e.printStackTrace();
		}
	}
	
	public T getEntity() {
		return entity;
	}

	public void setEntity(T entity) {
		this.entity = entity;
	}

}
