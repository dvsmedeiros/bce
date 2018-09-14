package com.dvsmedeiros.bce.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApplicationEntity extends AbstractApplicationEntity {
	public Logger getLogger(Class<?> clazz) {
		return LoggerFactory.getLogger(clazz);
	}
}
