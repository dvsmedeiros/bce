package com.dvsmedeiros.bce.core.controller.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dvsmedeiros.bce.core.controller.business.impl.ActivateByCode;
import com.dvsmedeiros.bce.core.controller.business.impl.InactivateByCode;
import com.dvsmedeiros.bce.core.controller.impl.Navigation;
import com.dvsmedeiros.bce.core.controller.impl.NavigationBuilder;
import com.dvsmedeiros.bce.domain.DomainSpecificEntity;

@Configuration
public class BceNavigation {

	@Autowired
	private InactivateByCode inactivateByCode;
	
	@Autowired
	private ActivateByCode activateByCode;

	@Bean(name = "INACTIVATE_BY_CODE")
	public Navigation<DomainSpecificEntity> deleteByCode() {
		return new NavigationBuilder<DomainSpecificEntity>()
				.next(inactivateByCode)
				.build();
	}
	
	@Bean(name = "ACTIVATE_BY_CODE")
	public Navigation<DomainSpecificEntity> updateByCode() {
		return new NavigationBuilder<DomainSpecificEntity>()
				.next(activateByCode)
				.build();
	}
}
