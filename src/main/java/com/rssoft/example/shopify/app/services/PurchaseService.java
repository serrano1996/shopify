/**
 * 
 */
package com.rssoft.example.shopify.app.services;

import java.util.List;

import com.rssoft.example.shopify.app.model.entities.Product;
import com.rssoft.example.shopify.app.model.entities.Purchase;
import com.rssoft.example.shopify.app.model.entities.User;

/**
 * @author rafas
 * 
 * Interfaz para el servicio de compra.
 *
 */
public interface PurchaseService {
	
	public Purchase save(Purchase purchase, User user);
	
	public Purchase save(Purchase purchase);
	
	public Product addProductInPurchase(Product product, Purchase purchase);
	
	public Purchase findById(Long id);
	
	public List<Purchase> findAll();
	
	public List<Purchase> findByOwner(User owner);

}
