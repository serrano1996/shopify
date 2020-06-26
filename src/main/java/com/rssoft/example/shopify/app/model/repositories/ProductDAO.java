package com.rssoft.example.shopify.app.model.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rssoft.example.shopify.app.model.entities.Product;
import com.rssoft.example.shopify.app.model.entities.Purchase;
import com.rssoft.example.shopify.app.model.entities.User;

/**
 * @author rafas
 * 
 * Interfaz repositorio del producto.
 *
 */
public interface ProductDAO extends JpaRepository<Product, Long> {
	
	List<Product> findByOwner(User owner);
	
	List<Product> findByPurchase(Purchase purchase);
	
	List<Product> findByPurchaseIsNull();
	
	List<Product> findByNameContainsIgnoreCaseAndPurchaseIsNull(String name);
	
	List<Product> findByNameContainsIgnoreCaseAndOwner(String name, User owner);

}
