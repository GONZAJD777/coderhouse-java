package com.coderhouse.ProyectoFinal_PrimeraEntrega.repository;

import com.coderhouse.ProyectoFinal_PrimeraEntrega.model.Client;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN TRUE ELSE FALSE END FROM Client c WHERE c.mClientId = :pClientId AND c.mIsActiveFlag = true")
    boolean existsActiveById(@Param("pClientId")Long pClientId);

    @Query("SELECT c FROM Client c WHERE c.mClientId=:pClientId AND c.mIsActiveFlag = true")
    Client findActiveById(@Param("pClientId")Long pClientId);

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN TRUE ELSE FALSE END FROM Client c WHERE c.mClientDocId = :pClientDocId AND c.mIsActiveFlag = true")
    boolean existsActiveByDoc(@Param("pClientDocId") Long pClientDocId);

    @Query("SELECT c FROM Client c WHERE c.mIsActiveFlag = true")
    List<Client> findAllActiveClients();
}

