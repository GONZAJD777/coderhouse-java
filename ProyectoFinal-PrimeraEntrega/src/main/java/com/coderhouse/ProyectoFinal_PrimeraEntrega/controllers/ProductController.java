package com.coderhouse.ProyectoFinal_PrimeraEntrega.controllers;

import com.coderhouse.ProyectoFinal_PrimeraEntrega.models.Cart;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.models.Client;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.models.Product;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.repositories.ClientRepository;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductRepository mProductRepository;

    public ProductController(ProductRepository pProductRepository) {
        this.mProductRepository = pProductRepository;
    }


    @GetMapping
    public List<Product> getAllProducts() {
        return mProductRepository.findAll();
    }

    @GetMapping("/{pProductId}")
    public ResponseEntity<Product> getClientById(@PathVariable Long pProductId){
        if(mProductRepository.existsById(pProductId)) {
            Product mProduct = mProductRepository.findById(pProductId).get();
            return ResponseEntity.ok(mProduct);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Product createClient(@RequestBody Product pProduct) {
        System.out.println("Client ProductId:" + pProduct.getmProductId());
        System.out.println("Client ProductName:" + pProduct.getmProductName());
        System.out.println("Client ProductDescription:" + pProduct.getmProductDescription());
        System.out.println("Client ProductCode:" + pProduct.getmProductCode());
        System.out.println("Client ProductStock:" + pProduct.getmProductStock());
        System.out.println("Client ProductPrice:" + pProduct.getmProductPrice());
        System.out.println("Client ProductTaxPercent:" + pProduct.getmProductTaxPercent());

        pProduct.setmProductCreationDate(LocalDateTime.now());
        return mProductRepository.save(pProduct);
    }


}
