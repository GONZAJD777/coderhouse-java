package com.coderhouse.ProyectoFinal_PrimeraEntrega.services;


import com.coderhouse.ProyectoFinal_PrimeraEntrega.models.Cart;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.models.Client;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository mClientRepository;

    public ClientService(ClientRepository pClientRepository) {
        this.mClientRepository = pClientRepository;
    }

    public List<Client> listAll () {
        return mClientRepository.findAll();
    }

    public Client getClient(Long pClientId) {
        if(!mClientRepository.existsById(pClientId)) {
         throw new RuntimeException("Client not found with ID: " + pClientId);
        }
        return mClientRepository.findById(pClientId).get();
    }

    public Client createClient(Client pClient) {

        Cart pCart= new Cart();
        pCart.setmCartCreationDate(LocalDateTime.now());
        pCart.setmCartClient(pClient);
        pClient.setmClientCart(pCart);

        pClient.setmClientCreationDate(LocalDateTime.now());

        return mClientRepository.save(pClient);
    }

}
