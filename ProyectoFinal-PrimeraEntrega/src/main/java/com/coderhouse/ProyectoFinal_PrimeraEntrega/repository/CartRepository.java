package com.coderhouse.ProyectoFinal_PrimeraEntrega.repository;

import com.coderhouse.ProyectoFinal_PrimeraEntrega.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart,Long> {
}
