package com.coderhouse.ProyectoFinal_SegundaEntrega.service;


import com.coderhouse.ProyectoFinal_SegundaEntrega.model.Cart;
import com.coderhouse.ProyectoFinal_SegundaEntrega.model.Client;
import com.coderhouse.ProyectoFinal_SegundaEntrega.repository.ClientRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository mClientRepository;
    @Autowired
    private CartService mCartService;

    public ClientService(ClientRepository pClientRepository, CartService pCartService) {
        this.mClientRepository = pClientRepository;
        this.mCartService = pCartService;
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
