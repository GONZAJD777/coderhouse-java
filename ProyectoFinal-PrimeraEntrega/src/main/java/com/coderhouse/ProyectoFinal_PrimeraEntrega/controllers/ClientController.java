package com.coderhouse.ProyectoFinal_PrimeraEntrega.controllers;

import com.coderhouse.ProyectoFinal_PrimeraEntrega.models.Cart;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.models.Client;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.repositories.ClientRepository;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/clients")
public class ClientController {


    @Autowired
    private ClientService mClientService;

    public ClientController(ClientService pClientService) {
        this.mClientService = pClientService;
    }

    @GetMapping
    public ResponseEntity<List<Client>> getAllClients() {
        return  ResponseEntity.ok(mClientService.listAll());
    }

    @GetMapping("/{pClientId}")
    public ResponseEntity<Client> getClientById(@PathVariable Long pClientId){
       try {
           return ResponseEntity.ok(mClientService.getClient(pClientId));
       }catch (Exception e){
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
       }
    }

    @PostMapping
    public ResponseEntity<Client> createClient(@RequestBody Client pClient) {
        System.out.println("Client Name:" + pClient.getmClientName());
        System.out.println("Client Address:" + pClient.getmClientAddress());
        System.out.println("Client Cart:" + pClient.getmClientCart());
        System.out.println("Client DocId:" + pClient.getmClientDocId());
        try {
            return ResponseEntity.ok(mClientService.createClient(pClient));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

    }

}
