package com.coderhouse.ProyectoFinal_PrimeraEntrega.dto.ticket;

import com.coderhouse.ProyectoFinal_PrimeraEntrega.dto.client.ClientReducedDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TicketDTO {

    private Long mTicketId;

    private ClientReducedDTO mTicketClient;

    private LocalDateTime mTicketCreationDate;

    private List<TicketItemDTO> mTicketDetail = new ArrayList<>();

    private Float mTicketTotal;

    public Long getmTicketId() {
        return mTicketId;
    }

    public void setmTicketId(Long pTicketId) {
        mTicketId = pTicketId;
    }

    public ClientReducedDTO getmTicketClient() {
        return mTicketClient;
    }

    public void setmTicketClient(ClientReducedDTO pTicketClient) {
        mTicketClient = pTicketClient;
    }

    public LocalDateTime getmTicketCreationDate() {
        return mTicketCreationDate;
    }

    public void setmTicketCreationDate(LocalDateTime pTicketCreationDate) {
        mTicketCreationDate = pTicketCreationDate;
    }

    public List<TicketItemDTO> getmTicketDetail() {
        return mTicketDetail;
    }

    public void setmTicketDetail(List<TicketItemDTO> pTicketDetail) {
        mTicketDetail = pTicketDetail;
    }

    public Float getmTicketTotal() {
        return mTicketTotal;
    }

    public void setmTicketTotal(Float pTicketTotal) {
        mTicketTotal = pTicketTotal;
    }

}
