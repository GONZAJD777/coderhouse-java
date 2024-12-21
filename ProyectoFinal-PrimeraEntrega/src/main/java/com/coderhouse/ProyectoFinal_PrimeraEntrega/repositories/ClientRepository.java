package com.coderhouse.ProyectoFinal_PrimeraEntrega.repositories;

import com.coderhouse.ProyectoFinal_PrimeraEntrega.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {

}
