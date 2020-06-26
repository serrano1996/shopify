/**
 * 
 */
package com.rssoft.example.shopify.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.rssoft.example.shopify.app.model.entities.Product;
import com.rssoft.example.shopify.app.model.entities.User;
import com.rssoft.example.shopify.app.services.ProductServiceImpl;
import com.rssoft.example.shopify.app.services.UserServiceImpl;
import com.rssoft.example.shopify.app.upload.StorageService;

/**
 * @author rafas
 *
 */
@Controller
@RequestMapping("/app")
public class ProductController {
	
	@Autowired
	private ProductServiceImpl productService;
	
	@Autowired
	private UserServiceImpl userService;
	
	@Autowired
	private StorageService storageService;

	private User logged;
	
	/**
	 * Proporciona los producto pertenecientes al usuario que
	 * se encuentra logueado.
	 * @return
	 */
	@ModelAttribute("my_products")
	public List<Product> myProducts() {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		logged = userService.findByEmail(email);
		return productService.findProductByOwner(logged);
	}
	
	@GetMapping("my_products")
	public String viewProducts(Model model, @RequestParam(name="q", required=false) String query) {
		if(query != null) {
			model.addAttribute("my_products", productService.searchByOwner(query, logged));
		}
		return "app/product/list";
	}
	
	@GetMapping("product/add")
	public String createProductForm(Model model) {
		model.addAttribute("product", new Product());
		return "app/product/form";
	}
	
	@PostMapping("/product/save")
	public String saveProduct(@ModelAttribute Product product,
			@RequestParam("file") MultipartFile file) {
		if (!file.isEmpty()) {
			String imagen = storageService.store(file);
			product.setImage(MvcUriComponentsBuilder
					.fromMethodName(FilesController.class, "serveFile", imagen).build().toUriString());
		}
		product.setOwner(logged);
		productService.save(product);
		return "redirect:/app/my_products";
	}
	
	
	@GetMapping("my_products/{id}/delete")
	public String viewProducts(@PathVariable Long id) {
		Product product = productService.findById(id);
		if(product.getPurchase() == null) {
			productService.delete(product);
		}
		return "redirect:/app/my_products";
	}

}
