package com.dvsmedeiros.bce.core.controller.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dvsmedeiros.bce.core.controller.business.impl.DeleteByCodeStrategy;
import com.dvsmedeiros.bce.core.controller.impl.Navigation;
import com.dvsmedeiros.bce.core.controller.impl.NavigationBuilder;
import com.dvsmedeiros.bce.domain.DomainSpecificEntity;

@Configuration
public class BceNavigation {

	@Autowired
	private DeleteByCodeStrategy deleteByCode;

	@Bean(name = "DELETE_BY_CODE")
	public Navigation<DomainSpecificEntity> deleteByCode() {

		return new NavigationBuilder<DomainSpecificEntity>()
				.next(deleteByCode)
				.build();

	}
}
