package com.coderhouse.ProyectoFinal_PrimeraEntrega.mapper;

import com.coderhouse.ProyectoFinal_PrimeraEntrega.dto.client.ClientDTO;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.dto.client.ClientReducedDTO;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.model.Client;

import java.util.ArrayList;
import java.util.List;

public class ClientMapper {

    public static ClientDTO toDTO(Client pClient) {
        ClientDTO mClientDTO = new ClientDTO();
        mClientDTO.setmClientId(pClient.getmClientId());
        mClientDTO.setmClientName(pClient.getmClientName());
        mClientDTO.setmClientAddress(pClient.getmClientAddress());
        mClientDTO.setmClientDocId(pClient.getmClientDocId());
        mClientDTO.setmClientCreationDate(pClient.getmClientCreationDate());
        mClientDTO.setmClientCart(CartMapper.toReducedDTO(pClient.getmClientCart()));

        return mClientDTO;
    }


    public static List<ClientDTO> toDTO(List<Client> pClientList) {
        List<ClientDTO> mClientDTOList = new ArrayList<>();
        for (Client mClient : pClientList ) {
            mClientDTOList.add(toDTO(mClient));
        }
        return mClientDTOList;
    }

    public static ClientReducedDTO toReducedDTO(Client pClient){
        ClientReducedDTO mClientReducedDTO = new ClientReducedDTO();
        mClientReducedDTO.setmClientId(pClient.getmClientId());
        mClientReducedDTO.setmClientName(pClient.getmClientName());
        mClientReducedDTO.setmClientAddress(pClient.getmClientAddress());
        mClientReducedDTO.setmClientDocId(pClient.getmClientDocId());

        return mClientReducedDTO;
    }



}
