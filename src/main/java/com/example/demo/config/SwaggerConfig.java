package com.example.demo.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * CONFIGURAÇÃO DO SWAGGER (OpenAPI)
 * Esta classe ativa o botão de 'Authorize' (o cadeado) na interface do Swagger,
 * permitindo que enviemos o usuário e senha para as rotas protegidas.
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                // Define que a API exige segurança do tipo Basic Auth
                .addSecurityItem(new SecurityRequirement().addList("BasicAuth"))
                .components(new Components()
                        .addSecuritySchemes("BasicAuth", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("basic")));
    }
}