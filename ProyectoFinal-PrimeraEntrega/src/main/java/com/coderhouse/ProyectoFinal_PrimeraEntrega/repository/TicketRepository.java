package com.coderhouse.ProyectoFinal_PrimeraEntrega.repository;

import com.coderhouse.ProyectoFinal_PrimeraEntrega.model.Client;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket,Long> {
    //List<Ticket> findByMTicketClient(Long pClientId);
    List<Ticket> findByMTicketClient(Client mTicketClient);
}
