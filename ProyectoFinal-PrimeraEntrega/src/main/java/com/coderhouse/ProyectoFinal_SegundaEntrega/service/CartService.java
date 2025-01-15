package com.coderhouse.ProyectoFinal_SegundaEntrega.service;

import com.coderhouse.ProyectoFinal_SegundaEntrega.model.Cart;
import com.coderhouse.ProyectoFinal_SegundaEntrega.model.CartDetail;
import com.coderhouse.ProyectoFinal_SegundaEntrega.model.Client;
import com.coderhouse.ProyectoFinal_SegundaEntrega.model.Product;
import com.coderhouse.ProyectoFinal_SegundaEntrega.repository.CartDetailRepository;
import com.coderhouse.ProyectoFinal_SegundaEntrega.repository.CartRepository;
import com.coderhouse.ProyectoFinal_SegundaEntrega.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class CartService {

    @Autowired
    private CartRepository mCartRepository;
    @Autowired
    private ProductRepository mProductRepository;
    @Autowired
    private CartDetailRepository mCartDetailRepository;

    public CartService(CartRepository pCartRepository, ProductRepository mProductRepository, CartDetailRepository mCartDetailRepository) {
        this.mCartRepository = pCartRepository;
        this.mProductRepository = mProductRepository;
        this.mCartDetailRepository = mCartDetailRepository;
    }


    public Cart createCart(Client pClient) {
        Cart cart = new Cart();
        cart.setmCartCreationDate(LocalDateTime.now());
        cart.setmCartClient(pClient);
        return cart;
    }

    public List<Cart> listAll() {
        return mCartRepository.findAll();
    }

    public Cart getCart(Long pCartId) {
        if(!mCartRepository.existsById(pCartId)) {
            throw new RuntimeException("Cart not found with ID: " + pCartId);
        }
        return mCartRepository.findById(pCartId).get();
    }

    @Transactional
    public Cart updateCart(Long pCartId, Long pProductId, int pItemQuantity) {

        try {
            if (!mCartRepository.existsById(pCartId)) {
                throw new RuntimeException("Cart not found with ID: " + pCartId);
            }
            if (!mProductRepository.existsById(pProductId)) {
                throw new RuntimeException("Product not found with ID: " + pProductId);
            }

            Cart mCart = mCartRepository.findById(pCartId).get();
            List<CartDetail> mCartDetailList = mCart.getmCartDetailList();
            Product mProduct = mProductRepository.findById(pProductId).get();
            boolean mExistFlag = false;
            CartDetail itemToRemove = null;

            for (CartDetail mCartDetailItem : mCartDetailList) {
                if (Objects.equals(mCartDetailItem.getmCartDetailProduct().getmProductId(), pProductId)) {
                    int mNewQuantity = mCartDetailItem.getmCartDetailItemQuantity() + pItemQuantity;
                    if (mNewQuantity <= 0) {
                        itemToRemove = mCartDetailItem;
                    } else {
                        mCartDetailItem.setmCartDetailItemQuantity(mNewQuantity);
                    }
                    mExistFlag = true;
                    break;
                }
            }

            if (itemToRemove != null) {
                mCartDetailList.remove(itemToRemove);
                itemToRemove.setmCartDetailCart(null);
                mCartDetailRepository.delete(itemToRemove); // Asegurar eliminación
            }

            if (!mExistFlag && pItemQuantity > 0) {
                CartDetail mCartDetail = new CartDetail(mProduct, pItemQuantity);
                mCartDetail.setmCartDetailCart(mCart);
                mCartDetailList.add(mCartDetail);
            } else if (!mExistFlag) {
                throw new RuntimeException("Product " + pProductId + " is not in the cart, you can't remove it.");
            }

            mCart.setmCartDetailList(mCartDetailList);

            return mCartRepository.save(mCart);

        } catch (Exception e) {
            throw new RuntimeException("Error adding product to cart: " + e.getMessage(), e);
        }
    }



}
