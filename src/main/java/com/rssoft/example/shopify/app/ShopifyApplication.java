package com.rssoft.example.shopify.app;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.rssoft.example.shopify.app.model.entities.Product;
import com.rssoft.example.shopify.app.model.entities.User;
import com.rssoft.example.shopify.app.services.ProductServiceImpl;
import com.rssoft.example.shopify.app.services.UserServiceImpl;
import com.rssoft.example.shopify.app.upload.StorageService;

@SpringBootApplication
public class ShopifyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopifyApplication.class, args);
	}
	
	/**
	 * CommandLineRunner proporciona una ejecución paralela a la ejecución
	 * principal de la aplicación.
	 * Inicialización de datos.
	 * @param userDao
	 * @param productDao
	 * @return
	 */
	@Bean
	public CommandLineRunner initData(UserServiceImpl userService, ProductServiceImpl productService) {
		return args -> {
			User user = new User("Perico", "Palomeque Huertas", null, "perico@gmail.com", "1234");
			user = userService.registry(user);

			User user1 = new User("Jhon", "Doe", null, "jhon@gmail.com", "1234");
			user1 = userService.registry(user1);

			List<Product> products = Arrays.asList(
				new Product("Bicicleta de montaña", 100.0f, null, user),
				new Product("Golf GTI Serie 2", 2500.0f, "https://www.minicar.es/large/Volkswagen-Golf-GTi-G60-Serie-II-%281990%29-Norev-1%3A18-i22889.jpg", user),
				new Product("Raqueta de tenis", 10.5f, "https://imgredirect.milanuncios.com/fg/2311/04/tenis/Raqueta-tenis-de-segunda-mano-en-Madrid-231104755_1.jpg?VersionId=T9dPhTf.3ZWiAFjnB7CvGKsvbdfPLHht", user),
				new Product("Xbox One X", 425.0f, "https://images.vibbo.com/635x476/860/86038583196.jpg", user1),
				new Product("Trípode flexible", 10.0f, "https://images.vibbo.com/635x476/860/86074256163.jpg", user1),
				new Product("Iphone 7 128 GB", 350.0f, "https://store.storeimages.cdn-apple.com/4667/as-images.apple.com/is/image/AppleInc/aos/published/images/i/ph/iphone7/rosegold/iphone7-rosegold-select-2016?wid=470&hei=556&fmt=jpeg&qlt=95&op_usm=0.5,0.5&.v=1472430205982", user1)
			);
			
			products.forEach(productService::save);
		};
	}
	
	/**
	 * Inicializa el almacen de datos.
	 * @param storageService
	 * @return
	 */
	@Bean
    CommandLineRunner init(StorageService storageService) {
        return (args) -> {
            storageService.deleteAll();
            storageService.init();
        };
    }

}
