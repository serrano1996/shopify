/**
 * 
 */
package com.rssoft.example.shopify.app.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rssoft.example.shopify.app.model.entities.Product;
import com.rssoft.example.shopify.app.services.ProductServiceImpl;

/**
 * @author rafas
 *
 */
@Controller
@RequestMapping("/public")
public class PublicController {

	@Autowired
	private ProductServiceImpl productService;
	
	@Autowired
	private HttpSession session;
	
	@ModelAttribute("products")
	public List<Product> productNotSold() {
		return productService.findProductNotSold();
	}
	
	/**
	 * Proporciona la suma total de los precios
	 * de los productos almacenados en el carrito
	 * de compra.
	 * @return
	 */
	@ModelAttribute("total_cart")
	public Double totalCart() {
		List<Product> cartContent = productInCart();
		// Si el carrito no está vacio.
		if(cartContent != null) {
			return cartContent.stream()
					.mapToDouble(p -> p.getPrice()) // Obtenemos el precio de cada producto convertido a Double.
					.sum();	// Sumamos todos los precios de la colección.
		}
		return 0.0;
	}
	
	/**
	 * Proporciona un listado de los productos
	 * almacenados en el carrito de compra.
	 * @return
	 */
	@ModelAttribute("cart")
	public List<Product> productInCart() {
		List<Long> cartContent = (List<Long>) session.getAttribute("cart");
		return (cartContent == null) ? null : productService.findProductsById(cartContent);
	}
	
	@GetMapping({"", "/", "index"})
	public String index(Model model, @RequestParam(name="q", required=false) String query) {
		if(query != null) {
			model.addAttribute("products", productService.search(query));
		}
		return "index";
	}
	
	@GetMapping("/product/{id}")
	public String viewProduct(Model model, @PathVariable Long id) {
		Product result = productService.findById(id);
		if(result != null) {
			model.addAttribute("product", result);
		}
		return "product";
	}
	
}
