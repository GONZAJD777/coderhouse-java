package com.coderhouse.ProyectoFinal_PrimeraEntrega.controller;

import com.coderhouse.ProyectoFinal_PrimeraEntrega.model.Product;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.service.ProductService;
import jakarta.ws.rs.InternalServerErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService mProductService;

    public ProductController(ProductService mProductService) {
        this.mProductService = mProductService;
    }


    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(mProductService.listAll());
        } catch (InternalServerErrorException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{pProductId}")
    public ResponseEntity<Product> getProductById(@PathVariable Long pProductId){
        try {
            return ResponseEntity.ok(mProductService.getProduct(pProductId));
        }catch (HttpClientErrorException.NotFound e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }catch (HttpClientErrorException.BadRequest e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (InternalServerErrorException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
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
        }catch (HttpClientErrorException.NotFound e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }catch (HttpClientErrorException.BadRequest e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (InternalServerErrorException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/{pProductId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long pProductId, @RequestBody Map<String, Object> pRequestBody) {
        Product mProduct = new Product();

        if (pProductId != null) {
            mProduct.setmProductId(pProductId);
        }

        if (pRequestBody.get("mProductName") != null) {
            mProduct.setmProductName(pRequestBody.get("mProductName").toString());
        }

        if (pRequestBody.get("mProductDescription") != null) {
            mProduct.setmProductDescription(pRequestBody.get("mProductDescription").toString());
        }

        if (pRequestBody.get("mProductCategory") != null) {
            mProduct.setmProductCategory(pRequestBody.get("mProductCategory").toString());
        }

        if (pRequestBody.get("mProductCode") != null) {
            mProduct.setmProductCode(pRequestBody.get("mProductCode").toString());
        }

        if (pRequestBody.get("mProductStock") != null) {
            mProduct.setmProductStock((Integer) pRequestBody.get("mProductStock"));
        }

        if (pRequestBody.get("mProductPrice") != null) {
            mProduct.setmProductPrice((Float) pRequestBody.get("mProductPrice"));
        }

        if (pRequestBody.get("mProductTaxPercent") != null) {
            mProduct.setmProductTaxPercent((Float) pRequestBody.get("mProductTaxPercent"));
        }



        try {
            return ResponseEntity.ok(mProductService.updateProduct(mProduct));
        } catch (HttpClientErrorException.NotFound e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }catch (HttpClientErrorException.BadRequest e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (InternalServerErrorException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }




}
