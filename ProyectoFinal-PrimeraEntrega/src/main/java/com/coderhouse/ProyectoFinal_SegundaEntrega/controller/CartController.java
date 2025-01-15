package com.coderhouse.ProyectoFinal_SegundaEntrega.controller;

import com.coderhouse.ProyectoFinal_SegundaEntrega.dto.cart.CartDTO;
import com.coderhouse.ProyectoFinal_SegundaEntrega.mapper.CartMapper;
import com.coderhouse.ProyectoFinal_SegundaEntrega.service.CartService;
import jakarta.ws.rs.InternalServerErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/carts")
public class CartController {


    @Autowired
    private CartService mCartService;

    public CartController( CartService pCartService) {

        this.mCartService = pCartService;
    }

    @GetMapping
    public ResponseEntity<List<CartDTO>> getAllCarts() {
        try {
            return ResponseEntity.ok(CartMapper.toDTO(mCartService.listAll()));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/{pCartId}")
    public ResponseEntity<CartDTO> getCartById(@PathVariable Long pCartId){
        try {
            return ResponseEntity.ok(CartMapper.toDTO(mCartService.getCart(pCartId)));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping("/{pCartId}")
    public ResponseEntity<CartDTO> addProductToCart(@PathVariable Long pCartId, @RequestBody Map<String, Object> pRequestBody) {
       Long pProductId = ((Number) pRequestBody.get("mCartDetailProduct")).longValue();
       int pItemQuantity = (int) pRequestBody.get("mCartDetailItemQuantity");

       try {
           return  ResponseEntity.ok(CartMapper.toDTO(mCartService.updateCart(pCartId,pProductId,pItemQuantity)));

       } catch (HttpClientErrorException.BadRequest e){
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
       } catch (InternalServerErrorException e) {
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
       }
    }
}
