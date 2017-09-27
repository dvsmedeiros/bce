package com.dvsmedeiros.bce.core.controller.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import com.dvsmedeiros.bce.core.controller.ITask;
import com.dvsmedeiros.bce.core.controller.business.impl.DeleteByCodeStrategy;
import com.dvsmedeiros.bce.core.controller.impl.Navigation;
import com.dvsmedeiros.bce.core.controller.impl.NavigationBuilder;
import com.dvsmedeiros.bce.core.controller.impl.RestartScheduler;
import com.dvsmedeiros.bce.domain.DomainSpecificEntity;

@Configuration
public class BceNavigation {

	@Autowired
	private DeleteByCodeStrategy deleteByCode;
	@Autowired
	private RestartScheduler restartScheduler;
	@Autowired
	private ThreadPoolTaskScheduler scheduler;
	
	/*
	@Autowired
	public BceNavigation(List<ISchedule> schedules) {
	
		for (ISchedule schedule : schedules) {
			scheduler.scheduleAtFixedRate(schedule, schedule.getFixedRate());
		}
	}
	*/

	@Bean(name = "DELETE_BY_CODE")
	public Navigation<DomainSpecificEntity> deleteByCode() {

		return new NavigationBuilder<DomainSpecificEntity>()
				.next(deleteByCode)
				.build();

	}
	
	@Bean(name = "RESTART_SCHEDULER")
	public Navigation<com.dvsmedeiros.bce.domain.Configuration> updateConfiguration() {

		return new NavigationBuilder<com.dvsmedeiros.bce.domain.Configuration>()
				.next(restartScheduler)
				.build();

	}
}
