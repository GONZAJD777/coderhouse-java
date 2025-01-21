package com.coderhouse.ProyectoFinal_PrimeraEntrega.repository;

import com.coderhouse.ProyectoFinal_PrimeraEntrega.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {

    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN TRUE ELSE FALSE END FROM Product p WHERE p.mProductCode = :pProductCode AND p.mIsActiveFlag = true")
    boolean existsActiveByCode(@Param("pProductCode") String pProductCode);

    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN TRUE ELSE FALSE END FROM Product p WHERE p.mProductId = :pProductId AND p.mIsActiveFlag = true")
    boolean existsActiveById(@Param("pProductId") Long pProductId);

    @Query("SELECT p FROM Product p WHERE p.mIsActiveFlag = true")
    List<Product> findAllActiveProducts();

    @Query("SELECT p FROM Product p WHERE p.mProductId=:pProductId AND p.mIsActiveFlag = true")
    Product findActiveById(@Param("pProductId") Long mProductId);


}
