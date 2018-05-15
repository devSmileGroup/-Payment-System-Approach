package com.smilestore.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Class that represents data of specification and describes the basic attributes 
 * and methods that represent the characteristic and behavior of this object.
 * 
 * @see SpecificationKey
 * 
 * @author Stas Omelchenko
 */

@Entity
@Table(name = "specification")
public class Specification {

	@Id
	@GeneratedValue
	@Column(name = "id", nullable = false)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "key_id")
	private SpecificationKey key = new SpecificationKey();

	@Column(name = "value", length = 50, nullable = false)
	private String value;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public SpecificationKey getKey() {
		return key;
	}

	public void setKey(SpecificationKey key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
