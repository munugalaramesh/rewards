/**
 * 
 */
package com.company.rewards.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;



/**
 * @author rmunugal
 *
 */

@Configuration
@Profile({"swagger","local"})
@OpenAPIDefinition(info = @Info(
		        title = "Rewards Management API", 
				version = "1.0.0", 
				description = "Customer Reward Management Portal"), 
servers = {
		@Server(url = "http://localhost:8080/rewards/", description = "local") 
		})
public class SwaggerConfig {

}
