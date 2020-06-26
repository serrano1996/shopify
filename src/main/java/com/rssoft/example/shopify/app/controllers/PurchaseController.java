package com.rssoft.example.shopify.app.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rssoft.example.shopify.app.model.entities.Product;
import com.rssoft.example.shopify.app.model.entities.Purchase;
import com.rssoft.example.shopify.app.model.entities.User;
import com.rssoft.example.shopify.app.services.ProductServiceImpl;
import com.rssoft.example.shopify.app.services.PurchaseServiceImpl;
import com.rssoft.example.shopify.app.services.UserServiceImpl;

/**
 * @author rafas
 *
 */
@Controller
@RequestMapping("app")
public class PurchaseController {
	
	@Autowired
	private PurchaseServiceImpl purchaseService;
	
	@Autowired
	private ProductServiceImpl productService;
	
	@Autowired
	private UserServiceImpl userService;
	
	@Autowired
	private HttpSession session;

	private User logged;
	
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
	 * Proporciona las compras realizadas por el usuario que
	 * se encuentra logueado.
	 * @return
	 */
	@ModelAttribute("my_purchases")
	public List<Purchase> myPurchases() {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		logged = userService.findByEmail(email);
		return purchaseService.findByOwner(logged);
	}
	
	@GetMapping("/cart")
	public String viewCart(Model model) {
		return "app/purchase/cart";
	}
	
	@GetMapping("/cart/add/{id}")
	public String addCart(Model model, @PathVariable Long id) {
		List<Long> cartContent = (List<Long>) session.getAttribute("cart");
		if(cartContent == null) {
			cartContent = new ArrayList<>();
		}
		if(!cartContent.contains(id)) {
			cartContent.add(id);
		}
		session.setAttribute("cart", cartContent);
		return "redirect:/";
	}
	
	@GetMapping("/cart/delete/{id}")
	public String deleteItemCart(Model model, @PathVariable Long id) {
		List<Long> cartContent = (List<Long>) session.getAttribute("cart");
		if(cartContent == null) {
			return "redirect:/";
		}
		cartContent.remove(id);
		if(cartContent.isEmpty()) {
			session.removeAttribute("cart");
		} else {
			session.setAttribute("cart", cartContent);
		}
		return "redirect:/app/cart";
	}
	
	@GetMapping("/cart/buy")
	public String buyItemsCart() {
		List<Long> cartContent = (List<Long>) session.getAttribute("cart");
		if(cartContent == null) {
			return "redirect:/";
		}
		List<Product> products = productInCart();
		Purchase purchase = purchaseService.save(new Purchase(), logged);
		products.forEach(p -> purchaseService.addProductInPurchase(p, purchase));
		session.removeAttribute("cart");
		return "redirect:/app/buy/bill/" + purchase.getId();
	}
	
	@GetMapping("/buy/bill/{id}")
	public String bill(Model model, @PathVariable Long id) {
		Purchase purchase = purchaseService.findById(id);
		List<Product> products = productService.findProductByPurchase(purchase);
		model.addAttribute("products", products);
		model.addAttribute("purchase", purchase);
		model.addAttribute("total_buy", products.stream().mapToDouble(p -> p.getPrice()).sum());
		return "app/purchase/bill";
	}
	
	@GetMapping("/my-purchases")
	public String viewMyPurchases(Model model) {
		return "app/purchase/list";
	}
	
}
