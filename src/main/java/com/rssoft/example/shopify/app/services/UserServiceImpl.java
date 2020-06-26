/**
 * 
 */
package com.rssoft.example.shopify.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.rssoft.example.shopify.app.model.entities.User;
import com.rssoft.example.shopify.app.model.repositories.UserDAO;

/**
 * @author rafas
 * 
 * Implementación del servicio del usuario.
 *
 */
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDAO userDao;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public User registry(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userDao.save(user);
	}

	@Override
	public User findById(Long id) {
		return userDao.findById(id).orElse(null);
	}

	@Override
	public User findByEmail(String email) {
		return userDao.findFirtsByEmail(email);
	}
	
}
