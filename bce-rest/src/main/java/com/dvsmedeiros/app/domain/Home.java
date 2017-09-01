package com.dvsmedeiros.app.domain;

import javax.persistence.Entity;

import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.domain.DomainSpecificEntity;

@Component
@Entity
public class Home extends DomainSpecificEntity{

	private String message;
	
	public Home() {
	}
	
	public Home(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
