package com.dvsmedeiros.bce.domain;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import org.springframework.stereotype.Component;

@Component
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class DomainSpecificEntity extends DomainEntity {

	private String code;
	private String description;
	private Boolean active;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public void prePersist() {
		super.prePersist();

		if (this.active == null) {
			active = Boolean.TRUE;
		}
	}

}
