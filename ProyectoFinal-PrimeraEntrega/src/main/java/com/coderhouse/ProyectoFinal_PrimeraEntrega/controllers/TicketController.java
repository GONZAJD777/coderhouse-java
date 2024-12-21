package com.coderhouse.ProyectoFinal_PrimeraEntrega.controllers;

import com.coderhouse.ProyectoFinal_PrimeraEntrega.repositories.ClientRepository;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    @Autowired
    private TicketRepository mTicketRepository;

}
