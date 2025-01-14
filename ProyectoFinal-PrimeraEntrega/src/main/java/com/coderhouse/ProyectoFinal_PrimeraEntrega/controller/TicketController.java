package com.coderhouse.ProyectoFinal_PrimeraEntrega.controller;

import com.coderhouse.ProyectoFinal_PrimeraEntrega.dto.ticket.TicketDTO;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.dto.ticket.TicketExtendedDTO;
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
    public ResponseEntity<List<TicketDTO>> getAllTickets() {
        try {
            return ResponseEntity.ok(TicketMapper.toDTO(mTicketService.listAll()));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/{pTicketId}")
    public ResponseEntity<TicketDTO> getTicketById(@PathVariable Long pTicketId){
        try {
            Ticket mTicket = mTicketService.getTicketById(pTicketId);
            return ResponseEntity.ok(TicketMapper.toDTO(mTicket));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/clients/{pClientId}")
    public ResponseEntity<List<TicketDTO>> getTicketByClientId(@PathVariable Long pClientId){
        try {
            List<Ticket> mTicketList = mTicketService.getTicketByClientId(pClientId);
            return ResponseEntity.ok(TicketMapper.toDTO(mTicketList));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping("/clients/{pClientId}")
    public ResponseEntity<ApiResponse<TicketExtendedDTO>> createTicket(@PathVariable Long pClientId) {
        try {

            TicketExtendedDTO mTicketExtendedDTO = mTicketService.createTicket(pClientId);
            ApiResponse<TicketExtendedDTO> mApiResponse = new ApiResponse<>(true,"El Ticket se genero correctamente",mTicketExtendedDTO,null);

            if(mTicketExtendedDTO.getmTicket()==null){
                mApiResponse.setSuccess(false);
                mApiResponse.setMessage("Ticket couldn't be generated, none of the items in the client's cart fulfill the requirements.");
            }
            if (!mTicketExtendedDTO.getNotEnoughStockProducts().isEmpty()) {
                mApiResponse.addError("Some items could not be sold because there is not enough stock. See notEnoughStockProducts");
            }

            return ResponseEntity.status(HttpStatus.OK).body(mApiResponse);

        } catch (HttpClientErrorException.BadRequest e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<>(false,e.getMessage(),null,List.of(e.getStatusText())));
        } catch (InternalServerErrorException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>(false,e.getMessage(),null,List.of(e.toString())));
        }
    }
}
