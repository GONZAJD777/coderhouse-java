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

    private final CartService mCartService;

    @Autowired
    public ClientService(ClientRepository pClientRepository, CartService pCartService) {
        this.mClientRepository = pClientRepository;
        this.mCartService = pCartService;
    }

    public List<Client> listAll () throws CustomException {
        try {
            return mClientRepository.findAllActiveClients();
        } catch (DataAccessException dbe) {
            throw new CustomException(ErrorType.DATABASE_ISSUES);
        } catch (RuntimeException rte){
            throw new CustomException(ErrorType.SYSTEM_ERROR);
        } catch (Exception e){
            throw new CustomException(ErrorType.SYSTEM_ERROR);
        }
    }


    public Client getClient(Long pClientId) throws CustomException {

        try {
            if (!mClientRepository.existsActiveById(pClientId)) {
                throw new CustomException(ErrorType.CLIENT_NOT_FOUND,ErrorType.CLIENT_NOT_FOUND.getFormattedMessage(pClientId));
            }
            return mClientRepository.findActiveById(pClientId);

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
    public Client createClient(Client pClient) throws CustomException {
        try {
            if(pClient.getmClientDocId()==null){
                throw new CustomException(ErrorType.CLIENT_DOC_NOT_NULLABLE,ErrorType.CLIENT_DOC_NOT_NULLABLE.getFormattedMessage(pClient.getmClientDocId()));
            }
            if (mClientRepository.existsActiveByDoc(pClient.getmClientDocId())) {
                throw new CustomException(ErrorType.CLIENT_DOC_ID_ALREADY_EXIST,ErrorType.CLIENT_DOC_ID_ALREADY_EXIST.getFormattedMessage(pClient.getmClientDocId()));
            }

                Client mClient = new Client(pClient.getmClientName(), pClient.getmClientAddress(), pClient.getmClientDocId());

                Cart pCart = mCartService.createCart(mClient);
                mClient.setmClientCart(pCart);
                mClient.setmClientCreationDate(LocalDateTime.now());

                return mClientRepository.save(mClient);

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
    public Client updateClient(Client pClient) throws CustomException {
        try {
            if(!mClientRepository.existsActiveById(pClient.getmClientId())) {
                throw new CustomException(ErrorType.CLIENT_NOT_FOUND,ErrorType.CLIENT_NOT_FOUND.getFormattedMessage(pClient.getmClientId()));
            }
            Client mClient = mClientRepository.findActiveById(pClient.getmClientId());

            if(pClient.getmClientName()!=null){
                mClient.setmClientName(pClient.getmClientName());
            }
            if(pClient.getmClientAddress()!=null){
                mClient.setmClientAddress(pClient.getmClientAddress());
            }
            if(pClient.getmClientDocId()!=null){
                if(mClientRepository.existsActiveByDoc(pClient.getmClientDocId())){
                    throw new CustomException(ErrorType.CLIENT_DOC_ID_ALREADY_EXIST,ErrorType.CLIENT_DOC_ID_ALREADY_EXIST.getFormattedMessage(pClient.getmClientDocId()));
                }
                mClient.setmClientDocId(pClient.getmClientDocId());
            }
            if(pClient.getmIsActiveFlag()!=null){
                mClient.setmIsActiveFlag(pClient.getmIsActiveFlag());
            }

            return mClientRepository.save(mClient);
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
    public Client deleteClient(Long pClientId) throws CustomException {
        try {
            if (!mClientRepository.existsActiveById(pClientId)) {
                throw new CustomException(ErrorType.CLIENT_NOT_FOUND,ErrorType.CLIENT_NOT_FOUND.getFormattedMessage(pClientId));
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
