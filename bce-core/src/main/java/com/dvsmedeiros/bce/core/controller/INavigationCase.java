package com.dvsmedeiros.bce.core.controller;

import com.dvsmedeiros.bce.core.controller.impl.INavigatorContext;
import com.dvsmedeiros.bce.domain.IEntity;
import com.dvsmedeiros.bce.domain.Result;

public interface INavigationCase<E extends IEntity> extends IEntity {

	public static final String DEFAULT_CONTEXT_NAME = "DEFAULT_CONTEXT";

	public String getName();

	public Result getResult();

	public void suspendExecution();

	public Boolean isSuspendExecution();

	public void setContext(INavigatorContext context);

	public INavigatorContext getContext();

}
