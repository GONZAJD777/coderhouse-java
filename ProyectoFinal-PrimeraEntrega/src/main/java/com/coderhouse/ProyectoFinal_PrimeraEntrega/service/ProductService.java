package com.coderhouse.ProyectoFinal_PrimeraEntrega.service;

import com.coderhouse.ProyectoFinal_PrimeraEntrega.dto.product.ProductDTO;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.exception.CustomException;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.mapper.ProductMapper;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.mapper.TicketMapper;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.model.ErrorType;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.model.Product;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.awt.event.FocusEvent;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository mProductRepository;

    public ProductService(ProductRepository mProductRepository) {
        this.mProductRepository = mProductRepository;
    }

    public List<Product> listAll () throws CustomException {
        try {
            return mProductRepository.findAllActiveProducts();
        } catch (DataAccessException dbe) {
            throw new CustomException(ErrorType.DATABASE_ISSUES);
        } catch (RuntimeException rte){
            throw new CustomException(ErrorType.SYSTEM_ERROR);
        } catch (Exception e){
            throw new CustomException(ErrorType.SYSTEM_ERROR);
        }
    }

    public Product getProduct(Long pProductId) throws CustomException {
       try {
           if (!mProductRepository.existsActiveById(pProductId)) {
               throw new CustomException(ErrorType.PRODUCT_NOT_FOUND,ErrorType.PRODUCT_NOT_FOUND.getFormattedMessage(pProductId));
           }
           return mProductRepository.findActiveById(pProductId);

       } catch (CustomException ce) {
           throw ce;
       } catch (DataAccessException dbe) {
           throw new CustomException(ErrorType.DATABASE_ISSUES);
       } catch (RuntimeException rte){
           throw new CustomException(ErrorType.SYSTEM_ERROR);
       } catch (Exception e){
           throw new CustomException(ErrorType.SYSTEM_ERROR);
       }
    }

    @Transactional
    public Product createProduct(Product pProduct) throws CustomException {
        try {

            if(mProductRepository.existsActiveByCode(pProduct.getmProductCode())){
                throw new CustomException(ErrorType.PRODUCT_CODE_ALREADY_EXIST,ErrorType.PRODUCT_CODE_ALREADY_EXIST.getFormattedMessage(pProduct.getmProductCode()));
            }
            if(pProduct.getmProductCode()==null){
                throw new CustomException(ErrorType.PRODUCT_CODE_NOT_NULLABLE,ErrorType.PRODUCT_CODE_NOT_NULLABLE.getFormattedMessage());
            }
            pProduct.setmProductCreationDate(LocalDateTime.now());
            pProduct.setmIsActiveFlag(true);
            return mProductRepository.save(pProduct);
        } catch (CustomException ce) {
            throw ce;
        } catch (DataAccessException dbe) {
            throw new CustomException(ErrorType.DATABASE_ISSUES);
        } catch (RuntimeException rte){
            throw new CustomException(ErrorType.SYSTEM_ERROR);
        } catch (Exception e){
            throw new CustomException(ErrorType.SYSTEM_ERROR);
        }
    }

    @Transactional
    public Product updateProduct(Product pProduct) throws CustomException {
        try {
            if(!mProductRepository.existsActiveById(pProduct.getmProductId())) {
                throw new CustomException(ErrorType.PRODUCT_NOT_FOUND,ErrorType.PRODUCT_NOT_FOUND.getFormattedMessage(pProduct.getmProductId()));
            }
            Product mProduct = mProductRepository.findById(pProduct.getmProductId()).get();

            if(pProduct.getmProductName()!=null){
                mProduct.setmProductName(pProduct.getmProductName());
            }
            if(pProduct.getmProductDescription()!=null){
                mProduct.setmProductDescription(pProduct.getmProductDescription());
            }
            if(pProduct.getmProductCategory()!=null){
                mProduct.setmProductCategory(pProduct.getmProductCategory());
            }
            if(pProduct.getmProductCode()!=null){
                //se validara si el nuevo codigo del producto existe, de ser asi se interrumpe la actualizacion arrojando una exception
                if(mProductRepository.existsActiveByCode(pProduct.getmProductCode())){
                    throw new CustomException(ErrorType.PRODUCT_CODE_ALREADY_EXIST,ErrorType.PRODUCT_CODE_ALREADY_EXIST.getFormattedMessage(pProduct.getmProductCode()));
                }
                mProduct.setmProductCode(pProduct.getmProductCode());
            }
            if(pProduct.getmProductStock()!=null){
                mProduct.setmProductStock(pProduct.getmProductStock());
            }
            if(pProduct.getmProductPrice()!=null){
                mProduct.setmProductPrice(pProduct.getmProductPrice());
            }
            if(pProduct.getmProductTaxPercent()!=null){
                mProduct.setmProductTaxPercent(pProduct.getmProductTaxPercent());
            }
            if(pProduct.getmIsActiveFlag()!=null){
                mProduct.setmIsActiveFlag(pProduct.getmIsActiveFlag());
            }

            return mProductRepository.save(mProduct);
        } catch (CustomException ce) {
            throw ce;
        } catch (DataAccessException dbe) {
            throw new CustomException(ErrorType.DATABASE_ISSUES);
        } catch (RuntimeException rte){
            throw new CustomException(ErrorType.SYSTEM_ERROR);
        } catch (Exception e){
            throw new CustomException(ErrorType.SYSTEM_ERROR);
        }
    }

    @Transactional
    public Product deleteProduct(Long pProductId) throws CustomException {

        try {
            if (!mProductRepository.existsActiveById(pProductId)) {
                throw new CustomException(ErrorType.PRODUCT_NOT_FOUND,ErrorType.PRODUCT_NOT_FOUND.getFormattedMessage(pProductId));
            }
            Product mProduct = new Product();
            mProduct.setmProductId(pProductId);
            mProduct.setmIsActiveFlag(false);
            return this.updateProduct(mProduct);

        } catch (CustomException ce) {
            throw ce;
        } catch (DataAccessException dbe) {
            throw new CustomException(ErrorType.DATABASE_ISSUES);
        } catch (RuntimeException rte){
            throw new CustomException(ErrorType.SYSTEM_ERROR);
        } catch (Exception e){
            throw new CustomException(ErrorType.SYSTEM_ERROR);
        }


    }
}
