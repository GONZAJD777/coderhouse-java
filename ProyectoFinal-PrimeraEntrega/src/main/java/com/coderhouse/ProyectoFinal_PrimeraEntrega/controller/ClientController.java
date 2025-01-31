package com.coderhouse.ProyectoFinal_PrimeraEntrega.controller;

import com.coderhouse.ProyectoFinal_PrimeraEntrega.dto.client.ClientDTO;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.dto.product.ProductDTO;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.exception.CustomException;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.handler.ErrorHandler;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.mapper.ClientMapper;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.model.Client;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.request.ClientApiRequest;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.request.ProductApiRequest;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.response.ApiResponse;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Client", description = "Este apartado contiene los endpoint para consultar, crear ,modificar y eliminar Clientes")
@RequestMapping("/api/clients")
public class ClientController {


    @Autowired
    private ClientService mClientService;

    public ClientController(ClientService pClientService) {
        this.mClientService = pClientService;
    }

    @Operation(summary = "Devuelve un listado de TODOS los Clientes",
            description = "Devuelve un listado de TODOS los Clientes que no hayan sido borrados (IsActiveFlag=true en Cliente)"
            ,responses = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Operacion ejecutada correctamente"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Error en la request",content = @Content(schema = @Schema(implementation = ApiResponse.class)) ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Error en el servicio",content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    })
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

    @Operation(summary = "Devuelve un listado la informacion del cliente pasado como parametro",
            description = "Devuelve un listado la informacion del cliente pasado como parametro siempre y cuando no hayan sido eliminado (IsActiveFlag=true en Cliente)",responses = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Operacion ejecutada correctamente"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Error en la request",content = @Content(schema = @Schema(implementation = ApiResponse.class)) ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Error en el servicio",content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    })
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

    @Operation(summary = "Crea un cliente con la informacion provista"
            ,description = "Crea un cliente con la informacion provista."
            ,requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(  content = @Content( schema = @Schema(implementation = ClientApiRequest.class)  ) )
            ,responses = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Operacion ejecutada correctamente"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Error en la request",content = @Content(schema = @Schema(implementation = ApiResponse.class)) ),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Error en el servicio",content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    })
    @PostMapping
    public ResponseEntity<ApiResponse<ClientDTO>> createClient(@RequestBody Client pClient) {
        System.out.println("Client Name:" + pClient.getmClientName());
        System.out.println("Client Address:" + pClient.getmClientAddress());
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

    @Operation(summary = "Actualiza la informacion del cliente",
            description = "Actualiza la informacion del cliente siempre y cuando no haya sido borrado.<br>" +
                    "No es posible actualizar la flag de baja o borrado logico.<>" +
                    "El documento introducido no puede coincidir con ningun otro cliente en la base de datos, incluido el cliente que se intenta modificar."
            ,requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(  content = @Content( schema = @Schema(implementation = ClientApiRequest.class)  ) )
            ,responses = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Operacion ejecutada correctamente"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Error en la request",content = @Content(schema = @Schema(implementation = ApiResponse.class)) ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Error en el servicio",content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    })
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
                    body(new ApiResponse<>(false,e.getCustomExceptionText(),null,
                            List.of(ErrorHandler.getStatus(e.getErrorType()).toString(),e.getErrorType().toString())));
        }
    }

    @Operation(summary = "Elimina la informacion del cliente",
            description = "Este endpoint realiza la baja o eliminacion LOGICA del cliente mediante la flag IsActiveFlag=false (Eliminado).<br>" +
                    "Los clientes eliminados, no pueden volver a ser activados con los endpoints y medios disponibles tradicionales.<br>" +
                    "La informacion de los clientes continua disponible para los Tickets aun despues de eliminados.",responses = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Operacion ejecutada correctamente"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Error en la request",content = @Content(schema = @Schema(implementation = ApiResponse.class)) ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Error en el servicio",content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    })
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
