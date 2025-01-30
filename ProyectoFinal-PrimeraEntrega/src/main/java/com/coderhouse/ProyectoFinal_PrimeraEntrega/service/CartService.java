package com.coderhouse.ProyectoFinal_PrimeraEntrega.service;

import com.coderhouse.ProyectoFinal_PrimeraEntrega.dto.cart.CartDTO;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.dto.cart.CartReducedDTO;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.dto.client.ClientDTO;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.exception.CustomException;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.mapper.CartMapper;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.model.*;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.repository.CartDetailRepository;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.repository.CartRepository;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.hibernate.validator.internal.util.stereotypes.Lazy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class CartService {


    private CartRepository mCartRepository;
    @Autowired
    private CartDetailRepository mCartDetailRepository;
    @Autowired
    private ProductService mProductService;


    public CartService(CartRepository pCartRepository, CartDetailRepository mCartDetailRepository, ProductService mProductService) {
        this.mCartRepository = pCartRepository;
        this.mCartDetailRepository = mCartDetailRepository;
        this.mProductService = mProductService;
    }

    public Cart createCart(Client pClient) throws CustomException {
        try {
            Cart cart = new Cart();
            cart.setmCartCreationDate(LocalDateTime.now());
            cart.setmCartClient(pClient);

            return cart;
        } catch (DataAccessException dbe) {
            throw new CustomException(ErrorType.DATABASE_ISSUES);
        } catch (RuntimeException rte) {
            throw new CustomException(ErrorType.SYSTEM_ERROR);
        } catch (Exception e) {
            throw new CustomException(ErrorType.SYSTEM_ERROR);
        }

    }

    public List<Cart> listAll() throws CustomException {
        try {
            return mCartRepository.findAllActiveCarts();
        }
        catch (DataAccessException dbe) {
            throw new CustomException(ErrorType.DATABASE_ISSUES);
        } catch (RuntimeException rte) {
            throw new CustomException(ErrorType.SYSTEM_ERROR);
        } catch (Exception e) {
            throw new CustomException(ErrorType.SYSTEM_ERROR);
        }
    }

    public Cart getCart(Long pCartId) throws CustomException {
        try {
            if (!mCartRepository.existsActiveCartById(pCartId)) {
                throw new CustomException(ErrorType.CART_NOT_FOUND,ErrorType.CART_NOT_FOUND.getFormattedMessage(pCartId));
            }

            return mCartRepository.findActiveCartById(pCartId);
        }
        catch (CustomException ce) {
            throw ce;
        } catch (DataAccessException dbe) {
            throw new CustomException(ErrorType.DATABASE_ISSUES);
        } catch (RuntimeException rte) {
            throw new CustomException(ErrorType.SYSTEM_ERROR);
        } catch (Exception e) {
            throw new CustomException(ErrorType.SYSTEM_ERROR);
        }
    }

    @Transactional
    public Cart addProductToCart(Long pCartId, Long pProductId, int pItemQuantity) throws CustomException {

        try {
            if (!mCartRepository.existsActiveCartById(pCartId)) {
                throw new CustomException(ErrorType.CART_NOT_FOUND,ErrorType.CART_NOT_FOUND.getFormattedMessage(pCartId));
            }


            Cart mCart = mCartRepository.findActiveCartById(pCartId);
            List<CartDetail> mCartDetailList = mCart.getmCartDetailList();
            Product mProduct = mProductService.getProduct(pProductId);
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
                throw new CustomException(ErrorType.CART_ITEM_NOT_FOUND,ErrorType.CART_ITEM_NOT_FOUND.getFormattedMessage(pProductId));
            }

            mCart.setmCartDetailList(mCartDetailList);

            return mCartRepository.save(mCart);

        } catch (CustomException ce) {
            throw ce;
        } catch (DataAccessException dbe) {
            throw new CustomException(ErrorType.DATABASE_ISSUES);
        } catch (RuntimeException rte) {
            throw new CustomException(ErrorType.SYSTEM_ERROR);
        } catch (Exception e) {
            throw new CustomException(ErrorType.SYSTEM_ERROR);
        }
    }

    @Transactional
    public Cart updateCartDetailList(Long pCartId, Object pCartDetailList) throws CustomException {
        try {

            if (!(pCartDetailList instanceof List<?>)) {
                throw new CustomException(ErrorType.CART_DETAIL_FORMAT_ERROR,ErrorType.CART_DETAIL_FORMAT_ERROR.getFormattedMessage());
            } else {

                @SuppressWarnings("unchecked")
                List<Map<String, Object>> mCartDetailListRaw = (List<Map<String, Object>>) pCartDetailList;
                for (Map<String, Object> CartDetail : mCartDetailListRaw) {


                    Long mCartDetailProduct = ((Number) CartDetail.get("producto")).longValue();
                    int mCartDetailQuantity = (int) CartDetail.get("cantidad");
                    this.addProductToCart(pCartId, mCartDetailProduct, mCartDetailQuantity);
                }

                return this.getCart(pCartId);
            }

        } catch (CustomException ce) {
            throw ce;
        } catch (DataAccessException dbe) {
            throw new CustomException(ErrorType.DATABASE_ISSUES);
        } catch (RuntimeException rte) {
            throw new CustomException(ErrorType.SYSTEM_ERROR);
        } catch (Exception e) {
            throw new CustomException(ErrorType.SYSTEM_ERROR);
        }

    }
}