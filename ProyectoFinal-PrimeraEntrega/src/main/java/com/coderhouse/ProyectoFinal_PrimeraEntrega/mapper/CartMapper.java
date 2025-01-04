package com.coderhouse.ProyectoFinal_PrimeraEntrega.mapper;

import com.coderhouse.ProyectoFinal_PrimeraEntrega.dto.CartDTO;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.dto.CartDetailDTO;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.dto.CartReducedDTO;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.model.Cart;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.utils.Utils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CartMapper {

    public static CartDTO toDTO(Cart cart) {
        CartDTO dto = new CartDTO();
        dto.setmCartId(cart.getmCartId());
        dto.setmCartCreationDate(cart.getmCartCreationDate());
        dto.setmCartClientDTO(ClientMapper.toReducedDTO(cart.getmCartClient()));

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

//        List<String> fieldsToRemove = List.of("mCartClientDTO");
//        return Utils.toDTO(Utils.excludeFields(dto,fieldsToRemove),CartDTO.class);
        return dto;
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

//        List<String> fieldsToRemove = List.of("mCartClientDTO");
//        return Utils.toDTO(Utils.excludeFields(dto,fieldsToRemove),CartDTO.class);
        return dto;
    }


}
