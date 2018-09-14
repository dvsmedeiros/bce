package com.dvsmedeiros.bce.core.controller.business.impl;

import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.core.controller.INavigationCase;
import com.dvsmedeiros.bce.core.controller.business.IStrategy;
import com.dvsmedeiros.bce.domain.DomainSpecificEntity;

@Component
public class DeleteByCodeStrategy implements IStrategy<DomainSpecificEntity> {

	@Override
	public void process(DomainSpecificEntity aEntity, INavigationCase<DomainSpecificEntity> aCase) {

		if (aEntity != null && aEntity.getActive() != null) {

			if (aEntity instanceof DomainSpecificEntity) {
				aEntity.setActive(Boolean.FALSE);
				return;
			}

			aCase.suspendExecution();
			aCase.getResult().setMessage("Entidade não é DomainSpecificEntity");
			return;
		}

		aCase.suspendExecution();
		aCase.getResult().setMessage("Entidade inexistente ou inválida!");
	}

}
