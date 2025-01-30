package com.coderhouse.ProyectoFinal_PrimeraEntrega.dto.ticket;

import com.coderhouse.ProyectoFinal_PrimeraEntrega.dto.client.ClientReducedDTO;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Schema(description = "TicketDTO reemplaza el objeto Client por un ClientReducedDTO, excluyendo la informacion del carrito, innecesaria en este apartado.<br>" +
        "Ademas, reemplaza TicketItem por TicketItemDTO el cual elimina los id de referencia al ticket al que pertenecen.")

public class TicketDTO {

    @Schema(name="mTicketId", description = "ID del Ticket, autoincremental")
    private Long mTicketId;

    @Schema(name="mTicketClient", description = "Cliente al que corresponde el Ticket")
    private ClientReducedDTO mTicketClient;

    @Schema(name="mTicketCreationDate", description = "Fecha y hora de creacion del Ticket")
    private Date mTicketCreationDate;

    @Schema(name="mTicketDetail", description = "Listado de items que conforman el Detalle del Ticket")
    private List<TicketItemDTO> mTicketDetail = new ArrayList<>();

    @Schema(name="mTicketTotal", description = "Monto total del Ticket")
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

    public Date getmTicketCreationDate() {
        return mTicketCreationDate;
    }

    public void setmTicketCreationDate(Date pTicketCreationDate) {
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
