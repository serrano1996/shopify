package com.rssoft.example.shopify.app.model.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rssoft.example.shopify.app.model.entities.Purchase;
import com.rssoft.example.shopify.app.model.entities.User;

/**
 * @author rafas
 * 
 * Interfaz repositorio de la compra.
 *
 */
public interface PurchaseDAO extends JpaRepository<Purchase, Long> {
	
	List<Purchase> findByOwner(User owner);

}
