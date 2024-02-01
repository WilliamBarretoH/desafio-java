package br.com.desafio.restaurante.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration

public class OpenApiConfig {

    @Bean
    public OpenAPI myOpenAPI() {

        Contact contact = new Contact();
        contact.setEmail("willbarretosan@gmail.com");
        contact.setName("William Barreto");
        contact.setUrl("https://www.linkedin.com/william-barreto-h");

        License mitLicense = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");

        Info info = new Info()
                .title("Desafio Java - Api Restaurante")
                .version("1.0")
                .contact(contact)
                .license(mitLicense);

        return new OpenAPI().info(info);
    }

}
