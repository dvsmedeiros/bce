package com.dvsmedeiros.bce.core.controller.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.core.controller.INavigationCase;
import com.dvsmedeiros.bce.core.controller.INavigator;
import com.dvsmedeiros.bce.core.controller.business.IStrategy;
import com.dvsmedeiros.bce.domain.IEntity;

@Component
@SuppressWarnings({ "unchecked", "rawtypes" })
public class Navigator<E extends IEntity> implements INavigator {

	@Autowired
	private NavigatorContext context;

	@Autowired
	private Map<String, Navigation<E>> listNavigations = new HashMap<String, Navigation<E>>();

	@Override
	public void run(IEntity aEntity, INavigationCase aCase) {

		aCase.setContext(context);
		navigate(aEntity, aCase);
	}

	private void navigate(IEntity aEntity, INavigationCase aCase) {

		if (aEntity != null) {

			Navigation<E> navigation = listNavigations.get(aCase.getName());

			if (navigation != null && !aCase.isSuspendExecution()) {

				List<IStrategy<? super E>> activities = navigation.getActivities();

				for (IStrategy strategy : activities) {

					strategy.process(aEntity, aCase);
					if (aCase.isSuspendExecution())
						break;
				}

			} else if (!aCase.getName().equals(BusinessCase.DEFAULT_CONTEXT_NAME)) {

				aCase.suspendExecution();
				aCase.getResult().setMessage(aCase.getName() + " - Não foi encontrada!");
			}
		}
	}
}
