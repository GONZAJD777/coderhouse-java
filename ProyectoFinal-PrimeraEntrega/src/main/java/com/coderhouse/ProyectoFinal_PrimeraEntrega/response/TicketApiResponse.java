package com.coderhouse.ProyectoFinal_PrimeraEntrega.response;

import com.coderhouse.ProyectoFinal_PrimeraEntrega.dto.ticket.TicketDTO;

import java.util.List;

public class TicketApiResponse<T> {

    private String mMessage;
    private List<TicketDTO> mTicketList;
    private List<T> mDetail;

    public String getmMessage() {
        return mMessage;
    }

    public void setmMessage(String pMessage) {
        mMessage = pMessage;
    }

    public List<TicketDTO> getmTicketList() {
        return mTicketList;
    }

    public void setmTicketList(List<TicketDTO> pTicketList) {
        mTicketList = pTicketList;
    }

    public List<T> getmDetail() {
        return mDetail;
    }

    public void setmDetail(List<T> pDetail) {
        mDetail = pDetail;
    }
}
