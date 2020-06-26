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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

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
@Table(name="users")
public class User {
	
	@Id 
	@GeneratedValue
	private Long id;
	
	@NotBlank
	private String name;
	
	@NotBlank
	private String lastname;
	
	private String avatar;
	
	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_at")
	private Date createdDate;
	
	@Email
	private String email;
	
	@NotBlank
	private String password;

	public User(String name, String lastname, String avatar, String email, String password) {
		super();
		this.name = name;
		this.lastname = lastname;
		this.avatar = avatar;
		this.email = email;
		this.password = password;
	}
	
}
