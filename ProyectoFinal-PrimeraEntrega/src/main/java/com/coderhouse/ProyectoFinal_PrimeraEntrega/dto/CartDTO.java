package com.coderhouse.ProyectoFinal_PrimeraEntrega.dto;

import com.coderhouse.ProyectoFinal_PrimeraEntrega.model.Client;

import java.time.LocalDateTime;
import java.util.List;

public class CartDTO {
    private long mCartId;
    private LocalDateTime mCartCreationDate;
    private ClientReducedDTO mCartClientDTO;
    private List<CartDetailDTO> mCartDetailList;


    public LocalDateTime getmCartCreationDate() {
        return mCartCreationDate;
    }

    public void setmCartCreationDate(LocalDateTime pCartCreationDate) {
        mCartCreationDate = pCartCreationDate;
    }

    public ClientReducedDTO getmCartClientDTO() {
        return mCartClientDTO;
    }

    public void setmCartClientDTO(ClientReducedDTO pCartClientDTO) {
        mCartClientDTO = pCartClientDTO;
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

