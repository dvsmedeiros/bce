package com.dvsmedeiros.bce.domain;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrePersist;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Component
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class DomainEntity extends AbstractDomainEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	private Calendar insertionDate;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Calendar getInsertionDate() {
		return insertionDate;
	}

	public void setInsertionDate(Calendar insertionDate) {
		this.insertionDate = insertionDate;
	}

	@PrePersist
	public void prePersist() {
		this.insertionDate = Calendar.getInstance();
	}

}
