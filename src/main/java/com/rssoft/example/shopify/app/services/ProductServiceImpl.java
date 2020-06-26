/**
 * 
 */
package com.rssoft.example.shopify.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rssoft.example.shopify.app.model.entities.Product;
import com.rssoft.example.shopify.app.model.entities.Purchase;
import com.rssoft.example.shopify.app.model.entities.User;
import com.rssoft.example.shopify.app.model.repositories.ProductDAO;

/**
 * @author rafas
 * 
 * Implementación del servicio del producto.
 *
 */
@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductDAO productDao;

	@Override
	public Product save(Product product) {
		return productDao.save(product);
	}

	@Override
	public void deleteById(Long id) {
		productDao.deleteById(id);	
	}

	@Override
	public void delete(Product product) {
		productDao.delete(product);
	}

	@Override
	public Product findById(Long id) {
		return productDao.findById(id).orElse(null);
	}

	@Override
	public List<Product> findAll() {
		return productDao.findAll();
	}

	@Override
	public List<Product> findProductByOwner(User owner) {
		return productDao.findByOwner(owner);
	}

	@Override
	public List<Product> findProductByPurchase(Purchase purchase) {
		return productDao.findByPurchase(purchase);
	}

	@Override
	public List<Product> findProductNotSold() {
		return productDao.findByPurchaseIsNull();
	}

	@Override
	public List<Product> search(String query) {
		return productDao.findByNameContainsIgnoreCaseAndPurchaseIsNull(query);
	}

	@Override
	public List<Product> searchByOwner(String query, User owner) {
		return productDao.findByNameContainsIgnoreCaseAndOwner(query, owner);
	}

	@Override
	public List<Product> findProductsById(List<Long> ids) {
		return productDao.findAllById(ids);
	}

}
