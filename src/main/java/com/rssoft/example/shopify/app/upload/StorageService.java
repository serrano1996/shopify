/**
 * 
 */
package com.rssoft.example.shopify.app.upload;

import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author rafas
 * 
 * Define la abstracción de lo que debería ser un almacén
 * secundario de información, de forma que pueda ser 
 * utilizado en un controlador.
 *
 */
public interface StorageService {
	
	public void init();

	public String store(MultipartFile file);

	public Stream<Path> loadAll();

	public Path load(String filename);

	public Resource loadAsResource(String filename);
    
	public void delete(String filename);

	public void deleteAll();

}
