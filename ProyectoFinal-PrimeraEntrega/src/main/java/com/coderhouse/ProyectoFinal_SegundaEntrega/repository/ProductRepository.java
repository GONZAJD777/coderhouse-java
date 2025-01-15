package com.coderhouse.ProyectoFinal_SegundaEntrega.repository;

import com.coderhouse.ProyectoFinal_SegundaEntrega.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
    boolean existsBymProductCode(String mProductCode);
}
