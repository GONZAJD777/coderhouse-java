package com.coderhouse.ProyectoFinal_PrimeraEntrega.repository;

import com.coderhouse.ProyectoFinal_PrimeraEntrega.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
