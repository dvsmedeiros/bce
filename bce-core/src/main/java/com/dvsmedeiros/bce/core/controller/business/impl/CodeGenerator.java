package com.dvsmedeiros.bce.core.controller.business.impl;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.core.controller.INavigationCase;
import com.dvsmedeiros.bce.core.controller.business.IStrategy;
import com.dvsmedeiros.bce.domain.DomainSpecificEntity;
import com.google.common.base.Strings;

@Component
public class CodeGenerator implements IStrategy<DomainSpecificEntity> {

	private static final int CODE_LENGTH = 10;

	@Override
	public void process(DomainSpecificEntity aEntity, INavigationCase<DomainSpecificEntity> aCase) {

		if (aEntity != null && Strings.isNullOrEmpty(aEntity.getCode())) {
			String code = RandomStringUtils.randomAlphanumeric(CODE_LENGTH).toUpperCase();
			aEntity.setCode(code);
		}
	}

}
