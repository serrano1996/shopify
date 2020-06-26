/**
 * 
 */
package com.rssoft.example.shopify.app.model.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author rafas
 * 
 * Clase modelo.
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="products")
public class Product {
	
	@Id 
	@GeneratedValue
	private Long id;
	
	@NotBlank
	private String name;
	
	private float price;
	
	private String image;
	
	@ManyToOne
	private User owner;
	
	@ManyToOne
	private Purchase purchase;

	public Product(String name, float price, String image, User owner) {
		super();
		this.name = name;
		this.price = price;
		this.image = image;
		this.owner = owner;
	}

}
