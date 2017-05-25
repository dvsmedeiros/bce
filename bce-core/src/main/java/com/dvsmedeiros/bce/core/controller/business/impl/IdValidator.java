package com.dvsmedeiros.bce.core.controller.business.impl;

import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.core.controller.INavigationCase;
import com.dvsmedeiros.bce.core.controller.business.IStrategy;
import com.dvsmedeiros.bce.domain.DomainEntity;

@Component
public class IdValidator implements IStrategy<DomainEntity> {

	@Override
	public void process(DomainEntity aEntity, INavigationCase<DomainEntity> aCase) {

		if (aEntity != null && aEntity.getId() > 0) {
			return;
		}

		aCase.suspendExecution();
		aCase.getResult().setMessage("ID: " + aEntity.getId() + " inexistente ou inválido");
	}

}
