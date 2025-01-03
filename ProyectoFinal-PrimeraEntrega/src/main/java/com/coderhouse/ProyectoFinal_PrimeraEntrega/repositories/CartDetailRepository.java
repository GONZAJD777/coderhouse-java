package com.coderhouse.ProyectoFinal_PrimeraEntrega.repositories;

import com.coderhouse.ProyectoFinal_PrimeraEntrega.models.CartDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartDetailRepository extends JpaRepository<CartDetail,Long> {
}
