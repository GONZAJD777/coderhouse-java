package com.coderhouse.ProyectoFinal_PrimeraEntrega.mapper;

import com.coderhouse.ProyectoFinal_PrimeraEntrega.dto.cart.CartDetailDTO;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.model.CartDetail;

import java.util.ArrayList;
import java.util.List;

public class CartDetailMapper {

    public static CartDetailDTO toDTO(CartDetail pCartDetail) {

            CartDetailDTO mCartDetailDTO = new CartDetailDTO();
            mCartDetailDTO.setmCartDetailItemProduct(pCartDetail.getmCartDetailProduct());
            mCartDetailDTO.setmCartDetailItemQuantity(pCartDetail.getmCartDetailItemQuantity());
            return mCartDetailDTO;

    }

    public static List<CartDetailDTO> toDTO(List<CartDetail> pCartDetailList){

        List<CartDetailDTO> mCartDetailDTOList = new ArrayList<>();

        for (CartDetail mCartDetailItem : pCartDetailList ) {
            mCartDetailDTOList.add(toDTO(mCartDetailItem));
        }

        return mCartDetailDTOList;
    }

}
