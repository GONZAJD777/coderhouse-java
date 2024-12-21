package com.coderhouse.ProyectoFinal_PrimeraEntrega.repositories;

import com.coderhouse.ProyectoFinal_PrimeraEntrega.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket,Long> {
}
