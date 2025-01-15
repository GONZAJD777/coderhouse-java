package com.coderhouse.ProyectoFinal_SegundaEntrega.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="tickets")
public class Ticket {

    @Id // Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Autoincremental
    @Column(name="ticket_id")
    private Long mTicketId;

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn(name="ticket_client_id",nullable = false)
    private Client mTicketClient;

    @Column(name="ticket_creation_date")
    private LocalDateTime mTicketCreationDate;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="ticket_item_ticket_id")
    private List<TicketItem> mTicketDetail = new ArrayList<>();

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

    public LocalDateTime getmTicketCreationDate() {
        return mTicketCreationDate;
    }

    public void setmTicketCreationDate(LocalDateTime pTicketCreationDate) {
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
