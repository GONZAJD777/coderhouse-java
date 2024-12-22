package com.coderhouse.ProyectoFinal_PrimeraEntrega.controllers;

import com.coderhouse.ProyectoFinal_PrimeraEntrega.models.Cart;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.models.CartDetail;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.models.Client;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.models.Product;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.repositories.CartRepository;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/carts")
public class CartController {

    @Autowired
    private CartRepository mCartRepository;
    @Autowired
    private ProductRepository mProductRepository;

    public CartController(CartRepository pCartRepository, ProductRepository mProductRepository) {
        this.mCartRepository = pCartRepository;
        this.mProductRepository = mProductRepository;
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

    @PutMapping("/{pCartId}")
    public ResponseEntity<String> addProductToCart(@PathVariable Long pCartId, @RequestBody Map<String, Object> pRequestBody) {
       Long pProductId = ((Number) pRequestBody.get("mCartDetailProduct")).longValue();
       int pItemQuantity = (int) pRequestBody.get("mCartDetailItemQuantity");

       try {
               // Verificar si el carrito y el producto existen
               if (!mCartRepository.existsById(pCartId))
               { return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cart not found with ID: " + pCartId); }
               if (!mProductRepository.existsById(pProductId))
               { return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found with ID: " + pProductId); }

               // recupero la entidad cart en cuestion y su lista de detalle para trabajar con el.
               Cart mCart = mCartRepository.findById(pCartId).get();
               List<CartDetail> mCartDetailList = mCart.getmCartDetailList();
               // recupero la informacion del producto para ser agregado
               Product mProduct = mProductRepository.findById(pProductId).get();
               //Creo un objeto CartDetail con el producto y la cantidad de items a agregar en el
               CartDetail mCartDetail = new CartDetail(mProduct, pItemQuantity);
               //Agrego el nuevo objeto CartDetail al CartDetailList ya existente en el carrito y gardo el nuevo valor en el objeto Cart.

               mCartDetailList.add(mCartDetail);
               mCart.setmCartDetailList(mCartDetailList);
               mCartDetail.setmCartDetailCart(mCart);


               //Guardo los cambios en el carrito
               mCartRepository.save(mCart);

               return ResponseEntity.ok("Product added to cart successfully");

       } catch (Exception e){
               return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while adding product to cart: " + e.getMessage());
       }

    }



}
