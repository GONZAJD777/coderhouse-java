package com.coderhouse.ProyectoFinal_PrimeraEntrega.mapper;

import com.coderhouse.ProyectoFinal_PrimeraEntrega.dto.ClientDTO;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.dto.ClientReducedDTO;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.model.Client;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.utils.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClientMapper {

    public static List<ClientDTO> toDTO(List<Client> pClientList) {

        List<ClientDTO> mDTOClientList = new ArrayList<>();

        for (Client mClient : pClientList ) {
            ClientDTO dto = new ClientDTO();
            dto.setmClientId(mClient.getmClientId());
            dto.setmClientName(mClient.getmClientName());
            dto.setmClientAddress(mClient.getmClientAddress());
            dto.setmClientDocId(mClient.getmClientDocId());
            dto.setmClientCreationDate(mClient.getmClientCreationDate());
            dto.setmClientCart(CartMapper.toReducedDTO(mClient.getmClientCart()));
            mDTOClientList.add(dto);
        }

        return mDTOClientList;
    }

    public static ClientReducedDTO toReducedDTO(Client pClient){
        ClientReducedDTO dto = new ClientReducedDTO();
        dto.setmClientId(pClient.getmClientId());
        dto.setmClientName(pClient.getmClientName());
        dto.setmClientAddress(pClient.getmClientAddress());

        return dto;
    }



}
