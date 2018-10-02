package com.dvsmedeiros.bce.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "code"))
public abstract class DomainSpecificEntity extends DomainEntity {
	
	@Column(nullable = false)
	private String code;
	private String description;
	private Boolean active;

	public DomainSpecificEntity() {
	}

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
