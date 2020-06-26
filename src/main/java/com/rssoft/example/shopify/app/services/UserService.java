/**
 * 
 */
package com.rssoft.example.shopify.app.services;

import com.rssoft.example.shopify.app.model.entities.User;

/**
 * @author rafas
 * 
 * Interfaz para el servicio del usuario.
 *
 */
public interface UserService {
	
	public User registry(User user);
	
	public User findById(Long id);
	
	public User findByEmail(String email);

}
