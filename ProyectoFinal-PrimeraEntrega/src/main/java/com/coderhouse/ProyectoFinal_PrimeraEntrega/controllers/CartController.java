package com.coderhouse.ProyectoFinal_PrimeraEntrega.controllers;

import com.coderhouse.ProyectoFinal_PrimeraEntrega.models.Cart;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.models.Client;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/carts")
public class CartController {

    @Autowired
    private CartRepository mCartRepository;

    public CartController(CartRepository pCartRepository) {
        this.mCartRepository = pCartRepository;
    }

    @GetMapping
    public List<Cart> getAllCarts() {
        return mCartRepository.findAll();
    }

    @GetMapping("/{pCartId}")
    public ResponseEntity<Cart> getCartById(@PathVariable Long pCartId){
        if(mCartRepository.existsById(pCartId)) {
            Cart mCart = mCartRepository.findById(pCartId).get();
            return ResponseEntity.ok(mCart);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

}
