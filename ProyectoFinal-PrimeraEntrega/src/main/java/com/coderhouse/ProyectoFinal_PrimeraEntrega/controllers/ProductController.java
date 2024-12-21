package com.coderhouse.ProyectoFinal_PrimeraEntrega.controllers;

import com.coderhouse.ProyectoFinal_PrimeraEntrega.models.Product;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.repositories.ClientRepository;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductRepository mProductRepository;

}
