package com.coderhouse.ProyectoFinal_PrimeraEntrega.controller;

import com.coderhouse.ProyectoFinal_PrimeraEntrega.dto.client.ClientDTO;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.dto.product.ProductDTO;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.exception.CustomException;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.handler.ErrorHandler;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.mapper.ClientMapper;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.model.Client;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.response.ApiResponse;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.service.ClientService;
import jakarta.ws.rs.InternalServerErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Map;

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
    public ResponseEntity<ApiResponse<List<ClientDTO>>> getAllClients() {
        try {
            List<ClientDTO> mClientDTOList = ClientMapper.toDTO(mClientService.listAll());
            ApiResponse<List<ClientDTO>> mApiResponse = new ApiResponse<>(true,"Listado de TODOS los clientes.",mClientDTOList,null);
            return ResponseEntity.status(HttpStatus.OK).body(mApiResponse);

        }catch (CustomException e) {
            return ResponseEntity.status(ErrorHandler.getStatus(e.getErrorType())).
                    body(new ApiResponse<>(false,e.getCustomExceptionText(),null,
                            List.of(ErrorHandler.getStatus(e.getErrorType()).toString(),e.getErrorType().toString())));
        }
    }

    @GetMapping("/{pClientId}")
    public ResponseEntity<ApiResponse<ClientDTO>> getClientById(@PathVariable Long pClientId){
       try {
           ClientDTO mClientDTO = ClientMapper.toDTO(mClientService.getClient(pClientId));
           ApiResponse<ClientDTO> mApiResponse = new ApiResponse<>(true,"Este es el cliente que buscabas",mClientDTO,null);
           return ResponseEntity.status(HttpStatus.OK).body(mApiResponse);

       }catch (CustomException e) {
           return ResponseEntity.status(ErrorHandler.getStatus(e.getErrorType())).
                   body(new ApiResponse<>(false,e.getCustomExceptionText(),null,
                           List.of(ErrorHandler.getStatus(e.getErrorType()).toString(),e.getErrorType().toString())));
       }
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ClientDTO>> createClient(@RequestBody Client pClient) {
        System.out.println("Client Name:" + pClient.getmClientName());
        System.out.println("Client Address:" + pClient.getmClientAddress());
        System.out.println("Client Cart:" + pClient.getmClientCart());
        System.out.println("Client DocId:" + pClient.getmClientDocId());
        try {
            ClientDTO mClientDTO = ClientMapper.toDTO(mClientService.createClient(pClient));
            ApiResponse<ClientDTO> mApiResponse = new ApiResponse<>(true,"Se ha creado el cliente.",mClientDTO,null);

            return ResponseEntity.status(HttpStatus.OK).body(mApiResponse);

        }catch (CustomException e) {
            return ResponseEntity.status(ErrorHandler.getStatus(e.getErrorType())).
                    body(new ApiResponse<>(false,e.getCustomExceptionText(),null,
                            List.of(ErrorHandler.getStatus(e.getErrorType()).toString(),e.getErrorType().toString())));
        }
    }

    @PutMapping("/{pClientId}")
    public ResponseEntity<ApiResponse<ClientDTO>> updateClient(@PathVariable Long pClientId, @RequestBody Map<String, Object> pRequestBody) {

        Client mClient = new Client();

        if (pClientId != null) {
            mClient.setmClientId(pClientId);
        }
        if (pRequestBody.get("mClientName") != null) {
            mClient.setmClientName(pRequestBody.get("mClientName").toString());
            System.out.println("Client Name:" + mClient.getmClientName());
        }
        if (pRequestBody.get("mClientAddress") != null) {
            mClient.setmClientAddress(pRequestBody.get("mClientAddress").toString());
            System.out.println("Client Address:" + mClient.getmClientAddress());
        }
        if (pRequestBody.get("mClientDocId") != null) {
            mClient.setmClientDocId(Long.parseLong(pRequestBody.get("mClientDocId").toString()));
            System.out.println("Client DocId:" + mClient.getmClientDocId());
        }

        try {
            ClientDTO mClientDTO = ClientMapper.toDTO(mClientService.updateClient(mClient));
            ApiResponse<ClientDTO> mApiResponse = new ApiResponse<>(true,"Se ha actualizado la informacion del cliente.",mClientDTO,null);

            return ResponseEntity.status(HttpStatus.OK).body(mApiResponse);

        }catch (CustomException e) {
            return ResponseEntity.status(ErrorHandler.getStatus(e.getErrorType())).
                    body(new ApiResponse<>(false,ErrorHandler.getErrorMessage(e.getErrorType()),null,
                            List.of(ErrorHandler.getStatus(e.getErrorType()).toString(),e.toString())));
        }
    }

    @DeleteMapping("/{pClientId}")
    public ResponseEntity<ApiResponse<ClientDTO>> deleteClient(@PathVariable Long pClientId){
        try {
            ClientDTO mClientDTO = ClientMapper.toDTO(mClientService.deleteClient(pClientId));
            ApiResponse<ClientDTO> mApiResponse = new ApiResponse<>(true,"Se ha eliminado el cliente.",mClientDTO,null);

            return ResponseEntity.status(HttpStatus.OK).body(mApiResponse);
        }catch (CustomException e) {
            return ResponseEntity.status(ErrorHandler.getStatus(e.getErrorType())).
                    body(new ApiResponse<>(false,e.getCustomExceptionText(),null,
                            List.of(ErrorHandler.getStatus(e.getErrorType()).toString(),e.getErrorType().toString())));
        }
    }


}
