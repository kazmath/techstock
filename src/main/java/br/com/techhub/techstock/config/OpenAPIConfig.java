package br.com.techhub.techstock.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
@SecurityScheme(
		name = "bearerAuth",
		type = SecuritySchemeType.HTTP,
		bearerFormat = "JWT",
		scheme = "bearer"
)
public class OpenAPIConfig {

	@Bean
	public OpenAPI registrationOpenAPI() {
		return new OpenAPI().info(
			new Info().title("TechStock")
				.description("Especificação da API do TechStock")
				.version("1.0")
		);
	}

	//    @Bean

}
