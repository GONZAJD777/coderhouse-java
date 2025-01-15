package com.coderhouse.ProyectoFinal_SegundaEntrega.mapper;

import com.coderhouse.ProyectoFinal_SegundaEntrega.dto.cart.CartDTO;
import com.coderhouse.ProyectoFinal_SegundaEntrega.dto.cart.CartReducedDTO;
import com.coderhouse.ProyectoFinal_SegundaEntrega.model.Cart;

import java.util.ArrayList;
import java.util.List;

public class CartMapper {

    public static CartDTO toDTO(Cart cart) {
        CartDTO dto = new CartDTO();
        dto.setmCartId(cart.getmCartId());
        dto.setmCartCreationDate(cart.getmCartCreationDate());
        dto.setmCartClient(ClientMapper.toReducedDTO(cart.getmCartClient()));
        dto.setmCartDetailList(CartDetailMapper.toDTO(cart.getmCartDetailList()));

        return dto;
    }

    public static List<CartDTO> toDTO(List<Cart> pCartList){
        List<CartDTO> mCartDTOList = new ArrayList<>();
        for (Cart mCart : pCartList ) {
            mCartDTOList.add(toDTO(mCart));
        }
        return mCartDTOList;
    }

    public static CartReducedDTO toReducedDTO(Cart cart) {
        CartReducedDTO dto = new CartReducedDTO();
        dto.setmCartId(cart.getmCartId());
        dto.setmCartCreationDate(cart.getmCartCreationDate());
        dto.setmCartDetailList(CartDetailMapper.toDTO(cart.getmCartDetailList()));

        return dto;
    }


}
