package com.dvsmedeiros.bce.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableAutoConfiguration
@ComponentScan(basePackages="com.dvsmedeiros")
@EnableJpaRepositories(basePackages="com.dvsmedeiros")
@EntityScan(basePackages="com.dvsmedeiros")
@SpringBootApplication
public class BceCoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BceCoreApplication.class, args);
	}
}
