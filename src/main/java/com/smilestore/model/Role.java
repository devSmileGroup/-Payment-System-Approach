package com.smilestore.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Class that represents data of role and describes the basic attributes 
 * and methods that represent the characteristic and behavior of this object.
 * 
 * @author Stas Omelchenko
 */

@Entity
@Table(name = "role")
public class Role {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	public Role() {

	}
	
	public Role(String roleName) {
		this.name = roleName;
	}

	public String getRoleName() {
		return name;
	}

	public void setRoleName(String roleName) {
		this.name = roleName;
	}
	
}
