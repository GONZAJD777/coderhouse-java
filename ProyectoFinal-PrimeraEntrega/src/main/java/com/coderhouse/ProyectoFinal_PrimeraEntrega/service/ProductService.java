package com.coderhouse.ProyectoFinal_PrimeraEntrega.service;

import com.coderhouse.ProyectoFinal_PrimeraEntrega.model.Product;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
            throw new RuntimeException("Client not found with ID: " + pProductId);
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

}
