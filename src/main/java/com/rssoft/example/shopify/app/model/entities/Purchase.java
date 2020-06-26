/**
 * 
 */
package com.rssoft.example.shopify.app.model.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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
@EntityListeners(AuditingEntityListener.class) // Para el correcto funcionamiento de @CreatedDate
@Table(name="purchases")
public class Purchase {

	@Id 
	@GeneratedValue
	private Long id;
	
	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_at")
	private Date purchaseDate;
	
	@ManyToOne
	private User owner;

	public Purchase(User owner) {
		super();
		this.owner = owner;
	}
	
}
