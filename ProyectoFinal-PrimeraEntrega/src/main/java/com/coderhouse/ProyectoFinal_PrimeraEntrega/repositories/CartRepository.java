package com.coderhouse.ProyectoFinal_PrimeraEntrega.repositories;

import com.coderhouse.ProyectoFinal_PrimeraEntrega.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart,Long> {
}
