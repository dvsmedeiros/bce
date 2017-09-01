package com.dvsmedeiros.bce.core.controller.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.core.controller.business.IStrategy;
import com.dvsmedeiros.bce.domain.ApplicationEntity;
import com.dvsmedeiros.bce.domain.IEntity;

@Component
public class Navigation<E extends IEntity> extends ApplicationEntity {

	private List<IStrategy<? super E>> activities;

	public Navigation() {
		this.activities = new ArrayList<>();
	}

	public void addActivity(IStrategy<? super E> activity) {
		this.activities.add(activity);
	}

	public List<IStrategy<? super E>> getActivities() {
		return activities;
	}

}
