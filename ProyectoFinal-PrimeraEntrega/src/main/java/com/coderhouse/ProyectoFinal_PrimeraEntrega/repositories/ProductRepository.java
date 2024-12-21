package com.coderhouse.ProyectoFinal_PrimeraEntrega.repositories;

import com.coderhouse.ProyectoFinal_PrimeraEntrega.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
