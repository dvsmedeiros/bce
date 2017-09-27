package com.dvsmedeiros.bce.core.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.core.controller.INavigationCase;
import com.dvsmedeiros.bce.core.controller.ITask;
import com.dvsmedeiros.bce.core.controller.business.IStrategy;
import com.dvsmedeiros.bce.domain.ApplicationEntity;
import com.dvsmedeiros.bce.domain.Configuration;

@Component
public class RestartScheduler extends ApplicationEntity implements IStrategy<Configuration>{

	@Autowired
	private ThreadPoolTaskScheduler scheduler;
	@Autowired
	private List<ITask> schedules;
	
	@Override
	public void process(Configuration aEntity, INavigationCase<Configuration> aCase) {
		
		if(restart()) {
			
			scheduler.shutdown();
			scheduler = new ThreadPoolTaskScheduler();
			scheduler.initialize();
			
			for (ITask schedule : schedules) {
				scheduler.scheduleAtFixedRate(schedule, schedule.getFixedRate());
			}
		}
	}
	
	private boolean restart() {
		for (ITask task : schedules) {
			if(task.isRestart()) return true;
		}
		return false;
	}
}
