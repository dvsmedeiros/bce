package com.dvsmedeiros.bce.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Component
@Entity
@Table(name = "CONFIGURATIONS")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Configuration extends DomainSpecificEntity {

	private String value;

	@JsonIgnore
	public int getIntValue() {
		return Integer.parseInt(value);
	}
	
	@JsonIgnore
	public double getDoubleValue() {
		return Double.parseDouble(value);
	}
	
	@JsonIgnore
	public long getLongValue() {
		return Long.parseLong(value);
	}
	
	@JsonIgnore
	public String getStringValue() {
		return value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}
