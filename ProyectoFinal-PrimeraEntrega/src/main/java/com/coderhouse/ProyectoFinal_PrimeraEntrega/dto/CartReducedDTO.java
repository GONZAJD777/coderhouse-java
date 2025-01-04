package com.coderhouse.ProyectoFinal_PrimeraEntrega.dto;

import java.time.LocalDateTime;
import java.util.List;

public class CartReducedDTO {

    private long mCartId;
    private LocalDateTime mCartCreationDate;
    private List<CartDetailDTO> mCartDetailList;

    public long getmCartId() {
        return mCartId;
    }

    public void setmCartId(long pCartId) {
        mCartId = pCartId;
    }

    public LocalDateTime getmCartCreationDate() {
        return mCartCreationDate;
    }

    public void setmCartCreationDate(LocalDateTime pCartCreationDate) {
        mCartCreationDate = pCartCreationDate;
    }

    public List<CartDetailDTO> getmCartDetailList() {
        return mCartDetailList;
    }

    public void setmCartDetailList(List<CartDetailDTO> pCartDetailList) {
        mCartDetailList = pCartDetailList;
    }
}
