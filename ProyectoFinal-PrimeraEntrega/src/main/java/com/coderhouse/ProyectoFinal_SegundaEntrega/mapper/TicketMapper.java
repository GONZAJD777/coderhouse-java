package com.coderhouse.ProyectoFinal_SegundaEntrega.mapper;

import com.coderhouse.ProyectoFinal_SegundaEntrega.dto.ticket.TicketDTO;
import com.coderhouse.ProyectoFinal_SegundaEntrega.dto.ticket.TicketItemDTO;
import com.coderhouse.ProyectoFinal_SegundaEntrega.dto.ticket.TicketExtendedDTO;
import com.coderhouse.ProyectoFinal_SegundaEntrega.model.CartDetail;
import com.coderhouse.ProyectoFinal_SegundaEntrega.model.Ticket;
import jakarta.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class TicketMapper {

    public static TicketDTO toDTO(Ticket pTicket) {
        TicketDTO dto = new TicketDTO();
        dto.setmTicketId(pTicket.getmTicketId());
        dto.setmTicketCreationDate(pTicket.getmTicketCreationDate());
        dto.setmTicketClient(ClientMapper.toReducedDTO(pTicket.getmTicketClient()));
        dto.setmTicketTotal(pTicket.getmTicketTotal());

        List<TicketItemDTO> mTicketItemDTOList = pTicket.getmTicketDetail() != null ? pTicket.getmTicketDetail().stream().map(detail -> {
            TicketItemDTO mTicketItemDTO = new TicketItemDTO();
            mTicketItemDTO.setmTicketItemProductName(detail.getmTicketItemProductName());
            mTicketItemDTO.setmTicketItemProductPrice(detail.getmTicketItemProductPrice());
            mTicketItemDTO.setmTicketItemProductTax(detail.getmTicketItemProductTax());
            mTicketItemDTO.setmTicketItemProductQuantity(detail.getmTicketItemProductQuantity());
            mTicketItemDTO.setmTicketItemSubTotal(detail.getmTicketItemSubTotal());

            return mTicketItemDTO;
        }).collect(Collectors.toList()) : Collections.emptyList();

        dto.setmTicketDetail(mTicketItemDTOList);

        return dto;
    }

    public static List<TicketDTO> toDTO(List<Ticket> pTicketList){

        List<TicketDTO> mTicketDTOList = new ArrayList<>();

        for (Ticket mTicket : pTicketList ) {
            mTicketDTOList.add(toDTO(mTicket));
        }

        return mTicketDTOList;
    }

    public static TicketExtendedDTO toExtendedDTO(@Nullable Ticket pTicket, List<CartDetail> pUnSoldItems) {
        TicketExtendedDTO dto = new TicketExtendedDTO();
        if (pTicket!=null)
        {
            dto.setmTicket(toDTO(pTicket));

        } else {
            dto.setmTicket(null);
        }
        dto.setNotEnoughStockProducts(CartDetailMapper.toDTO(pUnSoldItems));

        return dto;
    }

}


