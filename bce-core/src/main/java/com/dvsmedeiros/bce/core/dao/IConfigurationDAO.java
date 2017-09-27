package com.dvsmedeiros.bce.core.dao;

import com.dvsmedeiros.bce.domain.Configuration;

public interface IConfigurationDAO extends IDAO<Configuration>{
	
	Configuration findByCode(String code);
}
