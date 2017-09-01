package com.dvsmedeiros.bce.core.controller.impl;

import com.dvsmedeiros.bce.core.controller.business.IStrategy;
import com.dvsmedeiros.bce.domain.ApplicationEntity;
import com.dvsmedeiros.bce.domain.IEntity;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class NavigationBuilder<E extends IEntity> extends ApplicationEntity {

	private Navigation navigation;

	public NavigationBuilder() {
		this.navigation = new Navigation<>();
	}

	public NavigationBuilder<E> next(IStrategy<? super E> activity) {
		navigation.addActivity(activity);
		return this;
	}

	public Navigation<E> build() {
		return navigation;
	}
}
