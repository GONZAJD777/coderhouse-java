package com.coderhouse.ProyectoFinal_PrimeraEntrega.controllers;

import com.coderhouse.ProyectoFinal_PrimeraEntrega.models.Cart;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.models.CartDetail;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.models.Client;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.models.Product;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.repositories.CartRepository;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.repositories.ProductRepository;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.services.CartService;
import jakarta.ws.rs.InternalServerErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/carts")
public class CartController {


    @Autowired
    private CartService mCartService;

    public CartController( CartService mCartService) {
        this.mCartService = mCartService;
    }

    @GetMapping
    public List<Cart> getAllCarts() {
        return mCartService.listAll();
    }

    @GetMapping("/{pCartId}")
    public ResponseEntity<Cart> getCartById(@PathVariable Long pCartId){
        try {
            return ResponseEntity.ok(mCartService.getCart(pCartId));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping("/{pCartId}")
    public ResponseEntity<Cart> addProductToCart(@PathVariable Long pCartId, @RequestBody Map<String, Object> pRequestBody) {
       Long pProductId = ((Number) pRequestBody.get("mCartDetailProduct")).longValue();
       int pItemQuantity = (int) pRequestBody.get("mCartDetailItemQuantity");

       try {
           return  ResponseEntity.ok( mCartService.updateCart(pCartId,pProductId,pItemQuantity));

       } catch (HttpClientErrorException.BadRequest e){
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
       } catch (InternalServerErrorException e) {
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
       }
    }
}
