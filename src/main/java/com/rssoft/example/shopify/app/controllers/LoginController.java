/**
 * 
 */
package com.rssoft.example.shopify.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.rssoft.example.shopify.app.model.entities.User;
import com.rssoft.example.shopify.app.services.UserServiceImpl;
import com.rssoft.example.shopify.app.upload.StorageService;

/**
 * @author rafas
 *
 */
@Controller
public class LoginController {
	
	@Autowired
	private UserServiceImpl userService;
	
	@Autowired
	private StorageService storageService;
	
	@GetMapping("/")
	public String welcome() {
		return "redirect:/public/";
	}
	
	@GetMapping("/auth/login")
	public String login(Model model) {
		model.addAttribute("user", new User());
		return "login";
	}
	
	@PostMapping("/auth/register")
	public String register(@ModelAttribute User user,
			@RequestParam("file") MultipartFile file) {
		if (!file.isEmpty()) {
			String imagen = storageService.store(file);
			user.setAvatar(MvcUriComponentsBuilder
					.fromMethodName(FilesController.class, "serveFile", imagen).build().toUriString());
		}
		userService.registry(user);
		return "redirect:/auth/login";
	}

}
