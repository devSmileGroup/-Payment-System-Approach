package com.smilestore.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * Class that represents data of product and describes the basic attributes 
 * and methods that represent the characteristic and behavior of this object.
 * 
 * @see Category
 * @see Specification
 * 
 * @author Stas Omelchenko
 */

@Entity
@Table(name = "product")
public class Product {
	
	@Id
	@GeneratedValue
	@Column(name = "id", nullable = false)
	private Long id;
	
	@Column(name = "name", length = 50, nullable = false)
	private String name;
	
	@Column(name = "price", nullable = false)
	private Double price;
	
	@Column(name = "tax", nullable = false)
	private Double tax;
	
	@Column(name = "description", length = 300, nullable = false)
	private String description;
	
	@ManyToMany(cascade = {CascadeType.ALL})
	@JoinTable(
		name = "product_category",
		joinColumns = {@JoinColumn(name = "product_id") },
		inverseJoinColumns = { @JoinColumn(name = "category_id") }
	)
	private Set<Category> categories;
	
	@ManyToMany(cascade = {CascadeType.ALL})
	@JoinTable(
		name = "product_specification",
		joinColumns = { @JoinColumn(name = "product_id") },
		inverseJoinColumns = { @JoinColumn(name = "specification_id") }
	)
	private Set<Specification> specifications;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getTax() {
		return tax;
	}

	public void setTax(Double tax) {
		this.tax = tax;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Category> getCategories() {
		return categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

	public Set<Specification> getSpecifications() {
		return specifications;
	}

	public void setSpecifications(Set<Specification> specifications) {
		this.specifications = specifications;
	}
	
}
