package com.coderhouse.ProyectoFinal_PrimeraEntrega.controllers;

import com.coderhouse.ProyectoFinal_PrimeraEntrega.models.Product;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.services.ProductService;
import jakarta.ws.rs.InternalServerErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService mProductService;

    public ProductController(ProductService mProductService) {
        this.mProductService = mProductService;
    }


    @GetMapping
    public List<Product> getAllProducts() {
        return mProductService.listAll();
    }

    @GetMapping("/{pProductId}")
    public ResponseEntity<Product> getProductById(@PathVariable Long pProductId){
        try {
            return ResponseEntity.ok(mProductService.getProduct(pProductId));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product pProduct) {
        System.out.println("Client ProductId:" + pProduct.getmProductId());
        System.out.println("Client ProductName:" + pProduct.getmProductName());
        System.out.println("Client ProductDescription:" + pProduct.getmProductDescription());
        System.out.println("Client ProductCode:" + pProduct.getmProductCode());
        System.out.println("Client ProductStock:" + pProduct.getmProductStock());
        System.out.println("Client ProductPrice:" + pProduct.getmProductPrice());
        System.out.println("Client ProductTaxPercent:" + pProduct.getmProductTaxPercent());

        try {
            return ResponseEntity.ok(mProductService.createProduct(pProduct));
        } catch (HttpClientErrorException.BadRequest e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (InternalServerErrorException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


}
