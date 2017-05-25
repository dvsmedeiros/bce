package com.dvsmedeiros.bce.core.controller.business.impl;


import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.core.controller.INavigationCase;
import com.dvsmedeiros.bce.core.controller.business.IStrategy;
import com.dvsmedeiros.bce.domain.DomainSpecificEntity;

@Component
public class ActiveValidator implements IStrategy<DomainSpecificEntity>{

	@Override
	public void process(DomainSpecificEntity aEntity, INavigationCase<DomainSpecificEntity> aCase) {
		if(aEntity != null && aEntity.getActive() != null ){
			return; 
		}
		
		aCase.suspendExecution();
		aCase.getResult().setMessage("Status inexistente ou inválido!");
		
	}

}
