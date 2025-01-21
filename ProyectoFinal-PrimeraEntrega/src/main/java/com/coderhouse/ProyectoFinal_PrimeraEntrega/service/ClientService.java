package com.coderhouse.ProyectoFinal_PrimeraEntrega.service;


import com.coderhouse.ProyectoFinal_PrimeraEntrega.dto.client.ClientDTO;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.dto.product.ProductDTO;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.exception.CustomException;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.mapper.ClientMapper;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.mapper.ProductMapper;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.model.Cart;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.model.Client;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.model.ErrorType;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.model.Product;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.repository.ClientRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository mClientRepository;
    @Autowired
    private CartService mCartService;

    public ClientService(ClientRepository pClientRepository, CartService pCartService) {
        this.mClientRepository = pClientRepository;
        this.mCartService = pCartService;
    }

    public List<ClientDTO> listAll () throws CustomException {
        try {
            return ClientMapper.toDTO(mClientRepository.findAllActiveClients());
        } catch (DataAccessException dbe) {
            throw new CustomException(ErrorType.DATABASE_ISSUES);
        } catch (RuntimeException rte){
            throw new CustomException(ErrorType.SYSTEM_ERROR);
        } catch (Exception e){
            throw new CustomException(ErrorType.SYSTEM_ERROR);
        }
    }


    public ClientDTO getClient(Long pClientId) throws CustomException {

        try {
            if (!mClientRepository.existsActiveById(pClientId)) {
                throw new CustomException(ErrorType.CLIENT_NOT_FOUND);
            }
            return ClientMapper.toDTO(mClientRepository.findActiveById(pClientId));

        } catch (CustomException ce) {
            throw ce;
        } catch (DataAccessException dbe) {
            throw new CustomException(ErrorType.DATABASE_ISSUES);
        } catch (RuntimeException rte){
            throw new CustomException(ErrorType.SYSTEM_ERROR);
        } catch (Exception e){
            throw new CustomException(ErrorType.SYSTEM_ERROR);
        }
    }

    @Transactional
    public ClientDTO createClient(Client pClient) throws CustomException {
        try {
            if (!mClientRepository.existsActiveByDoc(pClient.getmClientDocId())) {
                throw new CustomException(ErrorType.CLIENT_DOC_ID_ALREADY_EXIST);
            }
                Cart pCart = mCartService.createCart(pClient);
                pClient.setmClientCart(pCart);
                pClient.setmClientCreationDate(LocalDateTime.now());

                return ClientMapper.toDTO(mClientRepository.save(pClient));

        } catch (CustomException ce) {
            throw ce;
        } catch (DataAccessException dbe) {
            throw new CustomException(ErrorType.DATABASE_ISSUES);
        } catch (RuntimeException rte){
            throw new CustomException(ErrorType.SYSTEM_ERROR);
        } catch (Exception e){
            throw new CustomException(ErrorType.SYSTEM_ERROR);
        }
    }

    @Transactional
    public ClientDTO updateClient(Client pClient) throws CustomException {
        try {
            if(!mClientRepository.existsActiveById(pClient.getmClientId())) {
                throw new CustomException(ErrorType.CLIENT_NOT_FOUND);            }
            Client mClient = mClientRepository.findActiveById(pClient.getmClientId());

            if(pClient.getmClientName()!=null){
                mClient.setmClientName(pClient.getmClientName());
            }
            if(pClient.getmClientAddress()!=null){
                mClient.setmClientAddress(pClient.getmClientAddress());
            }
            if(pClient.getmClientDocId()!=null){
                if(mClientRepository.existsActiveByDoc(pClient.getmClientDocId())){
                    throw new CustomException(ErrorType.CLIENT_DOC_ID_ALREADY_EXIST);
                }
                mClient.setmClientDocId(pClient.getmClientDocId());
            }
            if(pClient.getmIsActiveFlag()!=null){
                mClient.setmIsActiveFlag(pClient.getmIsActiveFlag());
            }

            return ClientMapper.toDTO(mClientRepository.save(mClient));
        } catch (CustomException ce) {
            throw ce;
        } catch (DataAccessException dbe) {
            throw new CustomException(ErrorType.DATABASE_ISSUES);
        } catch (RuntimeException rte){
            throw new CustomException(ErrorType.SYSTEM_ERROR);
        } catch (Exception e){
            throw new CustomException(ErrorType.SYSTEM_ERROR);
        }
    }


    public ClientDTO deleteClient(Long pClientId) throws CustomException {
        try {
            if (!mClientRepository.existsActiveById(pClientId)) {
                throw new CustomException(ErrorType.CLIENT_NOT_FOUND);
            }
            Client mClient = new Client();
            mClient.setmClientId(pClientId);
            mClient.setmIsActiveFlag(false);
            return this.updateClient(mClient);

        } catch (CustomException ce) {
            throw ce;
        } catch (DataAccessException dbe) {
            throw new CustomException(ErrorType.DATABASE_ISSUES);
        } catch (RuntimeException rte){
            throw new CustomException(ErrorType.SYSTEM_ERROR);
        } catch (Exception e){
            throw new CustomException(ErrorType.SYSTEM_ERROR);
        }
    }
}
