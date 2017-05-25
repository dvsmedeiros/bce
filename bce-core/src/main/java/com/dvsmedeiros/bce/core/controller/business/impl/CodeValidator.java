package com.dvsmedeiros.bce.core.controller.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.dvsmedeiros.bce.core.controller.INavigationCase;
import com.dvsmedeiros.bce.core.controller.business.IStrategy;
import com.dvsmedeiros.bce.core.dao.DomainEntityRepository;
import com.dvsmedeiros.bce.domain.DomainSpecificEntity;
import com.dvsmedeiros.bce.domain.Test;

@Component
public class CodeValidator implements IStrategy<DomainSpecificEntity> {

	@Autowired
	private DomainEntityRepository<Test> repository;

	@Override
	public void process(DomainSpecificEntity aEntity, INavigationCase<DomainSpecificEntity> aCase) {
		if (aEntity != null && aEntity.getCode() != null && !StringUtils.isEmpty(aEntity.getCode())) {
			
			if(aEntity instanceof DomainSpecificEntity){
				Test find = repository.findByCode(aEntity.getCode());
				if (find != null) {
					aCase.suspendExecution();
					aCase.getResult().setMessage("Código duplicado!");
					return;
				}
				return;
			}
			aCase.suspendExecution();
			aCase.getResult().setMessage("Entidade não é DomainSpecificEntity");
			return;
		}

		aCase.suspendExecution();
		aCase.getResult().setMessage("Código inexistente ou inválido!");

	}

}
