package com.coderhouse.ProyectoFinal_PrimeraEntrega.dto.cart;

import com.coderhouse.ProyectoFinal_PrimeraEntrega.dto.client.ClientReducedDTO;

import java.time.LocalDateTime;
import java.util.List;

public class CartDTO {
    private long mCartId;
    private LocalDateTime mCartCreationDate;
    private ClientReducedDTO mCartClient;
    private List<CartDetailDTO> mCartDetailList;


    public LocalDateTime getmCartCreationDate() {
        return mCartCreationDate;
    }

    public void setmCartCreationDate(LocalDateTime pCartCreationDate) {
        mCartCreationDate = pCartCreationDate;
    }

    public ClientReducedDTO getmCartClient() {
        return mCartClient;
    }

    public void setmCartClient(ClientReducedDTO pCartClient) {
        mCartClient = pCartClient;
    }

    public List<CartDetailDTO> getmCartDetailList() {
        return mCartDetailList;
    }

    public void setmCartDetailList(List<CartDetailDTO> pCartDetailList) {
        mCartDetailList = pCartDetailList;
    }

    public long getmCartId() {
        return mCartId;
    }

    public void setmCartId(long pCartId) {
        mCartId = pCartId;
    }

    // Getters y Setters
}

