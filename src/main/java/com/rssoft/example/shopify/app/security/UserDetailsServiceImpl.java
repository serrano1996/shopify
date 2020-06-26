/**
 * 
 */
package com.rssoft.example.shopify.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;

import com.rssoft.example.shopify.app.model.repositories.UserDAO;

/**
 * @author rafas
 * 
 * Recupera un usuario del sistema para autorizarlo 
 * en la aplicación.
 *
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserDAO userDao;

	/**
	 * Recupera un usuario del repositorio de 
	 * datos y lo autoriza.
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		com.rssoft.example.shopify.app.model.entities.User user = userDao.findFirtsByEmail(username);
		
		UserBuilder builder = null;
		
		if(user != null) {
			builder = User.withUsername(username);
			builder.disabled(false);
			builder.password(user.getPassword());
			builder.authorities(new SimpleGrantedAuthority("ROLE_USER"));
		} else {
			throw new UsernameNotFoundException("User not found");
		}
		
		return builder.build();
	}

}
