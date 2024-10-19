package org.serratec.ecommerce.pataMagica;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@Configuration
@OpenAPIDefinition(info = @Info(
		title = "API para compra e consulta de produtos Pet",
		version = "1.0",
		description = "Documentação da API Pata Mágica"
		))
public class OpenApiConfig {

}
