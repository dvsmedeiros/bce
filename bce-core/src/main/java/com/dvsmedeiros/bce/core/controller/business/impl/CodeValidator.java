package com.dvsmedeiros.bce.core.controller.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.dvsmedeiros.bce.core.controller.INavigationCase;
import com.dvsmedeiros.bce.core.controller.business.IStrategy;
import com.dvsmedeiros.bce.core.dao.impl.GenericDAO;
import com.dvsmedeiros.bce.domain.DomainSpecificEntity;

@Component
public class CodeValidator implements IStrategy<DomainSpecificEntity> {

	@Autowired
	private GenericDAO<DomainSpecificEntity> dao;

	@Override
	public void process(DomainSpecificEntity aEntity, INavigationCase<DomainSpecificEntity> aCase) {
		if (aEntity != null && aEntity.getCode() != null && !StringUtils.isEmpty(aEntity.getCode())) {

			if (aEntity instanceof DomainSpecificEntity) {
				if (dao.find(aEntity.getClass(), aEntity.getCode()).isPresent()) {
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
