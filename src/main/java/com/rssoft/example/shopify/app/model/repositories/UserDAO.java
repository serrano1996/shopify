/**
 * 
 */
package com.rssoft.example.shopify.app.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rssoft.example.shopify.app.model.entities.User;

/**
 * @author rafas
 * 
 * Interfaz repositorio del usuario.
 *
 */
public interface UserDAO extends JpaRepository<User, Long> {

	User findFirtsByEmail(String email);
	
}
