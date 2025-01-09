package com.coderhouse.ProyectoFinal_PrimeraEntrega.service;

import com.coderhouse.ProyectoFinal_PrimeraEntrega.model.Product;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.event.FocusEvent;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository mProductRepository;

    public List<Product> listAll () {
        return mProductRepository.findAll();
    }

    public Product getProduct(Long pProductId) {
        if(!mProductRepository.existsById(pProductId)) {
            throw new RuntimeException("Product not found with ID: " + pProductId,new Throwable("not_found"));
        }
        return mProductRepository.findById(pProductId).get();
    }



    @Transactional
    public Product createProduct(Product pProduct) {
        try {
            pProduct.setmProductCreationDate(LocalDateTime.now());
            return mProductRepository.save(pProduct);
        } catch (Exception e) { // Manejar excepciones si es necesario
            throw new RuntimeException("Error creating product: " + e.getMessage(), e);
        }
    }

    @Transactional
    public Product updateProduct(Product pProduct) {
        try {
            if(!mProductRepository.existsById(pProduct.getmProductId())) {
                throw new RuntimeException("Product not found with ID: " + pProduct.getmProductId());
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
                if(mProductRepository.existsBymProductCode(pProduct.getmProductCode())){
                    throw new RuntimeException("Product Code already exist: " + pProduct.getmProductCode());
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

            return mProductRepository.save(mProduct);
        } catch (Exception e) { // Manejar excepciones si es necesario
            throw new RuntimeException("Error creating product: " + e.getMessage(), e);
        }
    }

}
