package com.coderhouse.ProyectoFinal_PrimeraEntrega.controller;

import com.coderhouse.ProyectoFinal_PrimeraEntrega.dto.ticket.TicketDTO;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.dto.ticket.TicketExtendedDTO;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.exception.CustomException;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.handler.ErrorHandler;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.mapper.TicketMapper;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.model.Ticket;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.response.ApiResponse;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.service.TicketService;
import jakarta.ws.rs.InternalServerErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    @Autowired
    private TicketService mTicketService;

    public TicketController(TicketService pTicketService) {
        this.mTicketService = pTicketService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<TicketDTO>>> getAllTickets() {
        try {
            List<TicketDTO> mTicketList = mTicketService.listAll();
            ApiResponse<List<TicketDTO>> mApiResponse = new ApiResponse<>(true,"Listado de TODOS Tickets.",mTicketList,null);
            return ResponseEntity.status(HttpStatus.OK).body(mApiResponse);
        }catch (CustomException e){
            return ResponseEntity.status(ErrorHandler.getStatus(e.getErrorType())).
                    body(new ApiResponse<>(false,ErrorHandler.getErrorMessage(e.getErrorType()),null,
                            List.of(ErrorHandler.getStatus(e.getErrorType()).toString(),e.toString())));
        }
    }

    @GetMapping("/{pTicketId}")
    public ResponseEntity<ApiResponse<TicketDTO>> getTicketById(@PathVariable Long pTicketId){
        try {
            TicketDTO mTicketDTO = mTicketService.getTicketById(pTicketId);
            ApiResponse<TicketDTO> mApiResponse = new ApiResponse<>(true,"Este es el ticket que buscabas.",mTicketDTO,null);

            return ResponseEntity.status(HttpStatus.OK).body(mApiResponse);
        }catch (CustomException e){
            return ResponseEntity.status(ErrorHandler.getStatus(e.getErrorType())).
                    body(new ApiResponse<>(false,ErrorHandler.getErrorMessage(e.getErrorType()),null,
                            List.of(ErrorHandler.getStatus(e.getErrorType()).toString(),e.toString())));
        }
    }

    @GetMapping("/clients/{pClientId}")
    public ResponseEntity<ApiResponse<List<TicketDTO>>> getTicketByClientId(@PathVariable Long pClientId){
        try {
            List<TicketDTO> mTicketList = mTicketService.getTicketByClientId(pClientId);
            ApiResponse<List<TicketDTO>> mApiResponse = new ApiResponse<>(true,"Listado de Tickets del cliente.",mTicketList,null);

            return ResponseEntity.status(HttpStatus.OK).body(mApiResponse);
        } catch (CustomException e) {
            return ResponseEntity.status(ErrorHandler.getStatus(e.getErrorType())).
                    body(new ApiResponse<>(false,ErrorHandler.getErrorMessage(e.getErrorType()),null,
                            List.of(ErrorHandler.getStatus(e.getErrorType()).toString(),e.toString())));
        }
    }

    @PostMapping("/clients/{pClientId}")
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
                    body(new ApiResponse<>(false,
                            ErrorHandler.getErrorMessage(e.getErrorType()),null,
                            List.of(ErrorHandler.getStatus(e.getErrorType()).toString(),e.toString())));
        }
    }
}
