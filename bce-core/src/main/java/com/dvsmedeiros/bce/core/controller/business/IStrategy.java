package com.dvsmedeiros.bce.core.controller.business;

import com.dvsmedeiros.bce.core.controller.INavigationCase;
import com.dvsmedeiros.bce.domain.IEntity;

public interface IStrategy<T extends IEntity> extends IEntity {

	public void process(T aEntity, INavigationCase<T> aCase);

}
