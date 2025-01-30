package com.coderhouse.ProyectoFinal_PrimeraEntrega.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Schema(description = "Ticket")
@Table(name="tickets")
public class Ticket {

    @Id // Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Autoincremental
    @Schema(name="mTicketId", description = "ID del Ticket, autoincremental")
    @Column(name="ticket_id")
    private Long mTicketId;

    @Schema(name="mTicketClient", description = "Cliente al que corresponde el Ticket")
    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn(name="ticket_client_id",nullable = false)
    private Client mTicketClient;

    @Schema(name="mTicketCreationDate", description = "Fecha y hora de creacion del Ticket")
    @Column(name="ticket_creation_date")
    private Date mTicketCreationDate;

    @Schema(name="mTicketDetail", description = "Listado de items que conforman el Detalle del Ticket")
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="ticket_item_ticket_id")
    private List<TicketItem> mTicketDetail = new ArrayList<>();

    @Schema(name="mTicketTotal", description = "Monto total del Ticket")
    @Column(name="ticket_total_amount")
    private Float mTicketTotal;

    public Ticket() {
        super();
    }


    public Long getmTicketId() {
        return mTicketId;
    }

    public void setmTicketId(Long pTicketId) {
        mTicketId = pTicketId;
    }

    public Client getmTicketClient() {
        return mTicketClient;
    }

    public void setmTicketClient(Client pTicketClient) {
        mTicketClient = pTicketClient;
    }

    public Date getmTicketCreationDate() {
        return mTicketCreationDate;
    }

    public void setmTicketCreationDate(Date pTicketCreationDate) {
        mTicketCreationDate = pTicketCreationDate;
    }

    public List<TicketItem> getmTicketDetail() {
        return mTicketDetail;
    }

    public void setmTicketDetail(List<TicketItem> pTicketDetail) {
        mTicketDetail = pTicketDetail;
    }

    public Float getmTicketTotal() {
        return mTicketTotal;
    }

    public void setmTicketTotal(Float pTicketTotal) {
        mTicketTotal = pTicketTotal;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "mTicketId=" + mTicketId +
                ", mTicketClient=" + mTicketClient +
                ", mTicketCreationDate=" + mTicketCreationDate +
                ", mTicketDetail=" + mTicketDetail +
                ", mTicketTotal=" + mTicketTotal +
                '}';
    }
}
