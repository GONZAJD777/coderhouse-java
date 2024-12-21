package com.coderhouse.ProyectoFinal_PrimeraEntrega.controllers;

import com.coderhouse.ProyectoFinal_PrimeraEntrega.models.Cart;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.models.Client;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/clients")
public class ClientController {


    @Autowired
    private ClientRepository mClientRepository;

    public ClientController(ClientRepository pClientRepository) {
        this.mClientRepository = pClientRepository;
    }


    @GetMapping
    public List<Client> getAllClients() {
        return mClientRepository.findAll();
    }

    @GetMapping("/{pClientId}")
    public ResponseEntity<Client> getClientById(@PathVariable Long pClientId){
        if(mClientRepository.existsById(pClientId)) {
            Client mClient = mClientRepository.findById(pClientId).get();
            return ResponseEntity.ok(mClient);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Client createClient(@RequestBody Client pClient) {
        System.out.println("Client Name:" + pClient.getmClientName());
        System.out.println("Client Address:" + pClient.getmClientAddress());
        System.out.println("Client Cart:" + pClient.getmClientCart());
        System.out.println("Client DocId:" + pClient.getmClientDocId());
        Cart pCart= new Cart();
        pCart.setmCartCreationDate(LocalDateTime.now());
        pCart.setmCartClient(pClient);
        pClient.setmClientCart(pCart);

        pClient.setmClientCreationDate(LocalDateTime.now());
        return mClientRepository.save(pClient);
    }

}
