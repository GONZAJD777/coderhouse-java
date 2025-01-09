package com.coderhouse.ProyectoFinal_PrimeraEntrega.controller;

import com.coderhouse.ProyectoFinal_PrimeraEntrega.dto.client.ClientDTO;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.mapper.ClientMapper;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.model.Client;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.service.ClientService;
import jakarta.ws.rs.InternalServerErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

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
    public ResponseEntity<List<ClientDTO>> getAllClients() {
        try {
            return  ResponseEntity.ok(ClientMapper.toDTO(mClientService.listAll()));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/{pClientId}")
    public ResponseEntity<List<ClientDTO>> getClientById(@PathVariable Long pClientId){
       try {
           List<Client> mClientList = List.of(mClientService.getClient(pClientId));
           return ResponseEntity.ok(ClientMapper.toDTO(mClientList));
       }catch (Exception e){
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
       }
    }

    @PostMapping
    public ResponseEntity<List<ClientDTO>> createClient(@RequestBody Client pClient) {
        System.out.println("Client Name:" + pClient.getmClientName());
        System.out.println("Client Address:" + pClient.getmClientAddress());
        System.out.println("Client Cart:" + pClient.getmClientCart());
        System.out.println("Client DocId:" + pClient.getmClientDocId());
        try {
            List<Client> mClientList = List.of(mClientService.createClient(pClient));
            return ResponseEntity.ok(ClientMapper.toDTO(mClientList));
        } catch (HttpClientErrorException.BadRequest e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (InternalServerErrorException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
