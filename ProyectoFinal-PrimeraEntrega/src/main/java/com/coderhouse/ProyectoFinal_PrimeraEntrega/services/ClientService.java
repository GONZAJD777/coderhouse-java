package com.coderhouse.ProyectoFinal_PrimeraEntrega.services;


import com.coderhouse.ProyectoFinal_PrimeraEntrega.models.Cart;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.models.Client;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.repositories.ClientRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository mClientRepository;
    private CartService mCartService;

    public ClientService(ClientRepository pClientRepository, CartService mCartService) {
        this.mClientRepository = pClientRepository;
        this.mCartService = mCartService;
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

    @Transactional
    public Client createClient(Client pClient) {
        try {
            // Crear y asignar el carrito al cliente
                Cart pCart = mCartService.createCart(pClient); pClient.setmClientCart(pCart);
            // Asignar la fecha de creación del cliente
                pClient.setmClientCreationDate(LocalDateTime.now());
            // Guardar el cliente (y el carrito debido a la relación)
                return mClientRepository.save(pClient);
            } catch (Exception e) { // Manejar excepciones si es necesario
                throw new RuntimeException("Error creating client: " + e.getMessage(), e);
        }
    }

}
