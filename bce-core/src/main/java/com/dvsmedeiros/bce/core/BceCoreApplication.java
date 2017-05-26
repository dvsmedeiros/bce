package com.dvsmedeiros.bce.core;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan("com.dvsmedeiros")
@EnableJpaRepositories("com.dvsmedeiros")
@EntityScan("com.dvsmedeiros")
@Configuration
public class BceCoreApplication {
}
