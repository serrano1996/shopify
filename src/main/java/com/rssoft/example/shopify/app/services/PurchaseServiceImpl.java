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
import com.rssoft.example.shopify.app.model.repositories.PurchaseDAO;

/**
 * @author rafas
 * 
 * Implementación del servicio de la compra.
 *
 */
@Service
public class PurchaseServiceImpl implements PurchaseService {

	@Autowired
	private PurchaseDAO purchaseDao;
	
	@Autowired
	private ProductService productService;

	@Override
	public Purchase save(Purchase purchase, User user) {
		purchase.setOwner(user);
		return purchaseDao.save(purchase);
	}

	@Override
	public Purchase save(Purchase purchase) {
		return purchaseDao.save(purchase);
	}

	@Override
	public Product addProductInPurchase(Product product, Purchase purchase) {
		product.setPurchase(purchase);
		return productService.save(product);
	}

	@Override
	public Purchase findById(Long id) {
		return purchaseDao.findById(id).orElse(null);
	}

	@Override
	public List<Purchase> findAll() {
		return purchaseDao.findAll();
	}

	@Override
	public List<Purchase> findByOwner(User owner) {
		return purchaseDao.findByOwner(owner);
	}
	
}
