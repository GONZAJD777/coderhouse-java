package com.coderhouse.ProyectoFinal_PrimeraEntrega.controller;

import com.coderhouse.ProyectoFinal_PrimeraEntrega.dto.ticket.TicketDTO;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.dto.ticket.TicketExtendedDTO;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.exception.CustomException;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.handler.ErrorHandler;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.mapper.TicketMapper;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.model.Ticket;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.response.ApiResponse;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.service.TicketService;
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

@RestController
@RequestMapping("/api/tickets")
@Tag(name = "Ticket", description = "Este apartado contiene los endpoint para generar y consultar comprobantes")
public class TicketController {

    @Autowired
    private TicketService mTicketService;

    public TicketController(TicketService pTicketService) {
        this.mTicketService = pTicketService;
    }

    @GetMapping
    @Operation(summary = "Devuelve un listado de TODOS los tickets generados hasta el momento", description = "Descripción del endpoint de ejemplo")
    public ResponseEntity<ApiResponse<List<TicketDTO>>> getAllTickets() {
        try {
            List<TicketDTO> mTicketList = TicketMapper.toDTO(mTicketService.listAll());
            ApiResponse<List<TicketDTO>> mApiResponse = new ApiResponse<>(true,"Listado de TODOS Tickets.",mTicketList,null);
            return ResponseEntity.status(HttpStatus.OK).body(mApiResponse);
        }catch (CustomException e) {
            return ResponseEntity.status(ErrorHandler.getStatus(e.getErrorType())).
                    body(new ApiResponse<>(false,e.getCustomExceptionText(),null,
                            List.of(ErrorHandler.getStatus(e.getErrorType()).toString(),e.getErrorType().toString())));
        }
    }

    @GetMapping("/{pTicketId}")
    @Operation(summary = "Devuelve la informacion del ticket especificado como parametro", description = "Descripción del endpoint de ejemplo")
    public ResponseEntity<ApiResponse<TicketDTO>> getTicketById(@PathVariable Long pTicketId){
        try {
            TicketDTO mTicketDTO = TicketMapper.toDTO(mTicketService.getTicketById(pTicketId));
            ApiResponse<TicketDTO> mApiResponse = new ApiResponse<>(true,"Este es el ticket que buscabas.",mTicketDTO,null);

            return ResponseEntity.status(HttpStatus.OK).body(mApiResponse);
        }catch (CustomException e) {
            return ResponseEntity.status(ErrorHandler.getStatus(e.getErrorType())).
                    body(new ApiResponse<>(false,e.getCustomExceptionText(),null,
                            List.of(ErrorHandler.getStatus(e.getErrorType()).toString(),e.getErrorType().toString())));
        }
    }


    @GetMapping("/clients/{pClientId}")
    @Operation(summary = "Devuelve un listado de TODOS los tickets generados hasta el momento para el cliente especificado como parametro",
            description = "Devuelve un listado de TODOS los tickets generados hasta el momento para el cliente especificado como parametro.<br>"+
                          "Los comprobantes y su informacion no deben ser borrados, por lo que aun cuando un cliente haya sido eliminado, se podra consultar los mismos buscando por su ID.")
    public ResponseEntity<ApiResponse<List<TicketDTO>>> getTicketByClientId(@PathVariable Long pClientId){
        try {
            List<TicketDTO> mTicketList = TicketMapper.toDTO(mTicketService.getTicketByClientId(pClientId));
            ApiResponse<List<TicketDTO>> mApiResponse = new ApiResponse<>(true,"Listado de Tickets del cliente.",mTicketList,null);

            return ResponseEntity.status(HttpStatus.OK).body(mApiResponse);
        } catch (CustomException e) {
            return ResponseEntity.status(ErrorHandler.getStatus(e.getErrorType())).
                    body(new ApiResponse<>(false,e.getCustomExceptionText(),null,
                            List.of(ErrorHandler.getStatus(e.getErrorType()).toString(),e.getErrorType().toString())));
        }
    }

    @PostMapping("/clients/{pClientId}")
    @Operation(summary = "Genera el ticket para la compra de los productos del carrito del cliente especificado como parametro",
            description = "Genera el ticket para la compra de los productos del carrito del cliente especificado como parametro.<br>"+
                          "Si el cliente no tiene productos en su carrito devolvera error informando la situacion.<br>" +
                          "Si no hay suficiente stock de alguno de los productos del carrito del cliente, se informara en la respuesta.<br>"+
                          "No sera posible generar un ticket a un cliente que ha sido eliminado.")
    public ResponseEntity<ApiResponse<TicketExtendedDTO>> createTicket(@PathVariable Long pClientId) {
        try {

            TicketExtendedDTO mTicketExtendedDTO = mTicketService.createTicket(pClientId);
            ApiResponse<TicketExtendedDTO> mApiResponse = new ApiResponse<>(true,"El Ticket se genero correctamente.",mTicketExtendedDTO,null);

            if(mTicketExtendedDTO.getmTicket()==null){
                mApiResponse.setSuccess(false);
                mApiResponse.setMessage("Ticket couldn't be generated, none of the items in the client's cart fulfill the requirements.");
            }
            if (!mTicketExtendedDTO.getNotEnoughStockProducts().isEmpty()) {
                mApiResponse.addError("Some items could not be sold because there is not enough stock. See notEnoughStockProducts");
            }

            return ResponseEntity.status(HttpStatus.OK).body(mApiResponse);

        } catch (CustomException e) {
            return ResponseEntity.status(ErrorHandler.getStatus(e.getErrorType())).
                    body(new ApiResponse<>(false,e.getCustomExceptionText(),null,
                            List.of(ErrorHandler.getStatus(e.getErrorType()).toString(),e.getErrorType().toString())));
        }
    }
}
