package com.coderhouse.ProyectoFinal_PrimeraEntrega.repository;

import com.coderhouse.ProyectoFinal_PrimeraEntrega.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart,Long> {


        @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END " +
                "FROM Cart c,Client ct " +
                "WHERE c.mCartId = :pCartId " +
                "AND ct.mIsActiveFlag = true " +
                "AND c.mCartClient.mClientId=ct.mClientId")
        boolean existsActiveCartById(@Param("pCartId") Long pCartId);

        @Query("SELECT c " +
                "FROM Cart c,Client ct " +
                "WHERE c.mCartId = :pCartId " +
                "AND ct.mIsActiveFlag = true " +
                "AND c.mCartClient.mClientId=ct.mClientId")
        Cart findActiveCartById(@Param("pCartId") Long pCartId);

        @Query("SELECT c " +
                "FROM Cart c,Client ct " +
                "WHERE ct.mIsActiveFlag = true " +
                "AND c.mCartClient.mClientId=ct.mClientId")
        List<Cart> findAllActiveCarts();


}
