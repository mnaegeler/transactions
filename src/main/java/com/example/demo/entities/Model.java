package com.example.demo.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class Model {

	@Column(updatable = false)
	private Date createdAt;

	private Date updatedAt;

	private Date deletedAt;

	@PrePersist
	private void beforePersist() {
		createdAt = new Date();
		updatedAt = createdAt;
	}

	@PreUpdate
	private void beforeUpdate() {
		updatedAt = new Date();
	}
}
