package com.dvsmedeiros.bce.core.controller;

import com.dvsmedeiros.bce.domain.IEntity;

@SuppressWarnings("rawtypes")
public interface INavigator<E extends IEntity> extends IEntity {

	public void run(IEntity aEntity, INavigationCase aCase);

}
