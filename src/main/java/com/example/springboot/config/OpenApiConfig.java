package com.example.springboot.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Lorenzo Deflorian",
                        email = "l.deflorian@ringmaster.it",
                        url = "https://www.youtube.com/watch?v=xvFZjo5PgG0"
                ),
                description = "OpenAPI documentation for the best IceCream in the world",
                title = "OpenAPi specification - IceCreamFactory",
                version = "1.0.0",
                license = @License(
                        name = "CC0",
                        url = "https://creativecommons.org/public-domain/cc0/"
                ),
                termsOfService = "Make good Ice Creams"
        ),
        servers = {
                @Server(
                description = "Local DEV ENV",
                url = "http://localhost:8080"
        ), @Server(
                description = "PROD ENV",
                url = "https://www.youtube.com/watch?v=xvFZjo5PgG0"
        )}
)
public class OpenApiConfig {

}
