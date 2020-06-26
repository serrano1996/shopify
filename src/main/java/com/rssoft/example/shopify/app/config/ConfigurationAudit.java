/**
 * 
 */
package com.rssoft.example.shopify.app.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * @author rafas
 * 
 * Configura la auditoria de JPA para el 
 * correcto funcionamiento de la generación 
 * automática de fechas.
 *
 */
@Configuration
@EnableJpaAuditing
public class ConfigurationAudit {

}
