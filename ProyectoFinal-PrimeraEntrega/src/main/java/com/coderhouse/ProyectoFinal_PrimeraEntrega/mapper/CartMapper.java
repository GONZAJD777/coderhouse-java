package com.coderhouse.ProyectoFinal_PrimeraEntrega.mapper;

import com.coderhouse.ProyectoFinal_PrimeraEntrega.dto.*;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.model.Cart;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CartMapper {

    public static CartDTO toDTO(Cart cart) {
        CartDTO dto = new CartDTO();
        dto.setmCartId(cart.getmCartId());
        dto.setmCartCreationDate(cart.getmCartCreationDate());
        dto.setmCartClient(ClientMapper.toReducedDTO(cart.getmCartClient()));

        List<CartDetailDTO> detailDTOs = cart.getmCartDetailList() != null ? cart.getmCartDetailList().stream().map(detail -> {
            CartDetailDTO detailDTO = new CartDetailDTO();
            detailDTO.setmCartDetailProductId(detail.getmCartDetailProduct().getmProductId());
            detailDTO.setmCartDetailProductName(detail.getmCartDetailProduct().getmProductName());
            detailDTO.setmCartDetailProductDescription(detail.getmCartDetailProduct().getmProductDescription());
            detailDTO.setmCartDetailProductCategory(detail.getmCartDetailProduct().getmProductCategory());
            detailDTO.setmCartDetailProductPrice(detail.getmCartDetailProduct().getmProductPrice());
            detailDTO.setmCartDetailItemQuantity(detail.getmCartDetailItemQuantity());
            return detailDTO;
        }).collect(Collectors.toList()) : Collections.emptyList();
        dto.setmCartDetailList(detailDTOs);

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

        List<CartDetailDTO> detailDTOs = cart.getmCartDetailList() != null ? cart.getmCartDetailList().stream().map(detail -> {
            CartDetailDTO detailDTO = new CartDetailDTO();
            detailDTO.setmCartDetailProductId(detail.getmCartDetailProduct().getmProductId());
            detailDTO.setmCartDetailProductName(detail.getmCartDetailProduct().getmProductName());
            detailDTO.setmCartDetailProductDescription(detail.getmCartDetailProduct().getmProductDescription());
            detailDTO.setmCartDetailProductCategory(detail.getmCartDetailProduct().getmProductCategory());
            detailDTO.setmCartDetailProductPrice(detail.getmCartDetailProduct().getmProductPrice());
            detailDTO.setmCartDetailItemQuantity(detail.getmCartDetailItemQuantity());
            return detailDTO;
        }).collect(Collectors.toList()) : Collections.emptyList();
        dto.setmCartDetailList(detailDTOs);

        return dto;
    }


}
