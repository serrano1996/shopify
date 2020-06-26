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
 * Interfaz para el servicio del producto.
 *
 */
public interface ProductService {
	
	public Product save(Product product);
	
	public void deleteById(Long id);
	
	public void delete(Product product);
	
	public Product findById(Long id);
	
	public List<Product> findAll();
	
	public List<Product> findProductByOwner(User owner);
	
	public List<Product> findProductByPurchase(Purchase purchase);
	
	public List<Product> findProductNotSold();
	
	public List<Product> search(String query);
	
	public List<Product> searchByOwner(String query, User owner);

	public List<Product> findProductsById(List<Long> ids); 
	
}
