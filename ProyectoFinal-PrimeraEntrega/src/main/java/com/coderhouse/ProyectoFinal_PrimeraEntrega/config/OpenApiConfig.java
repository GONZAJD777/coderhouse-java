package com.coderhouse.ProyectoFinal_PrimeraEntrega.config;


import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.*;
import java.util.stream.Collectors;

@Configuration
public class OpenApiConfig {

    @Bean
    OpenAPI customOpenAPI (){
        return new OpenAPI()
                .info(new Info()
                        .title("API REST Full | Java | CoderHouse")
                        .version("3.0.0")
                        .description("La API REST proporciona endpoints para administrar la venta de productos en un comercio.<br>"
                                + "Permite realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) tanto para clientes y su carrito, productos y ventas.<br>"
                                + "Los endpoints permiten listar, agregar, mostrar, editar y eliminar clientes y productos asi como listar comprobantes ya emitidos.<br>"
                                + "La API está documentada utilizando Swagger, lo que facilita la comprensión de los endpoints y su uso. ")
                        .contact(new Contact()
                                .name("Gonzalo Javier Diaz Mizzau")
                                .email("gonzajd777@gmail.com"))
                        .license(new License()
                                .name("Licencia")
                                .url("https://github.com/GONZAJD777/coderhouse-java.git"))
                )
                .servers(List.of(new Server()
                        .url("http://localhost:8080")
                        .description("Servidor Local")));
    }

}
