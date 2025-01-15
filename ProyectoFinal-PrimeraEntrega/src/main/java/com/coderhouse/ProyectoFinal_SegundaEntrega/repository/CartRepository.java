package com.coderhouse.ProyectoFinal_SegundaEntrega.repository;

import com.coderhouse.ProyectoFinal_SegundaEntrega.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart,Long> {
}
