package com.dvsmedeiros.bce.core;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan("com.dvsmedeiros")
@ComponentScan("com.dvsmedeiros")
@EnableJpaRepositories("com.dvsmedeiros")
public class BceCoreApplication {
}
