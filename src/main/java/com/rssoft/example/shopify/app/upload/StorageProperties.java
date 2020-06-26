/**
 * 
 */
package com.rssoft.example.shopify.app.upload;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author rafas
 *
 */
@Configuration
@ConfigurationProperties(prefix="storage")
public class StorageProperties {
	
	private String location = "upload-dir";

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
