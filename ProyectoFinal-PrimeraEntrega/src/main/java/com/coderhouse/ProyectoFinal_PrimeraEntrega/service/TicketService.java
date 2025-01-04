package com.coderhouse.ProyectoFinal_PrimeraEntrega.service;

import com.coderhouse.ProyectoFinal_PrimeraEntrega.model.*;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.repository.ClientRepository;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.repository.TicketRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TicketService {

    @Autowired
    private TicketRepository mTicketRepository;
    @Autowired
    private ClientRepository mClientRepository;

    public TicketService(TicketRepository pTicketRepository, ClientRepository pClientRepository) {
        this.mTicketRepository = pTicketRepository;
        this.mClientRepository = pClientRepository;
    }

    public List<Ticket> listAll() {
        return mTicketRepository.findAll();
    }

    public List<Ticket> getTicketByClientId(Long pClientId) {
        if(!mClientRepository.existsById(pClientId)) {
            throw new RuntimeException("Client not found with ID: " + pClientId);
        }
        Client mClient = mClientRepository.findById(pClientId).get();
        return mTicketRepository.findByMTicketClient(mClient);
    }

    @Transactional
    public Ticket createTicket(Long pClientId) {
        if(!mClientRepository.existsById(pClientId)) {
            throw new RuntimeException("Client not found with ID: " + pClientId);
        }

        Client mClient = mClientRepository.findById(pClientId).get();
        Cart mCart = mClient.getmClientCart();

        if(mCart.getmCartDetailList().isEmpty()) {
            throw new RuntimeException("Cart has no items: " + pClientId);
        }

        List<TicketItem> mTicketItemList = new ArrayList<>();
        List<CartDetail> mNewCartDetailList = new ArrayList<>();
        List<CartDetail> mSoldCartDetailList = new ArrayList<>();

        float mTicketTotalAmount = 0f;

        for (CartDetail mCartDetailItem : mCart.getmCartDetailList()){
            if(mCartDetailItem.getmCartDetailItemQuantity()<=mCartDetailItem.getmCartDetailProduct().getmProductStock()){
                TicketItem mTicketItem = getTicketItem(mCartDetailItem);
                mTicketTotalAmount = mTicketTotalAmount + mTicketItem.getTicketItemSubTotal();
                mTicketItemList.add(mTicketItem);
                //items "vendidos", una vez creado el ticket, se debe actualizar el stock de los productos en la BD.
                mSoldCartDetailList.add(mCartDetailItem);
            } else {
                //items que no se pudieron vender por falta de stock
                //tambien sera el nuevo CartDetail del carrito , vacio o con los items q no se pudieron vender
                mNewCartDetailList.add(mCartDetailItem);
            }
        }

        mCart.setmCartDetailList(mNewCartDetailList);
        
        Ticket mTicket = new Ticket();
        mTicket.setTicketClient(mClient);
        mTicket.setTicketCreationDate(LocalDateTime.now());
        mTicket.setTicketDetail(mTicketItemList);
        mTicket.setTicketTotal(mTicketTotalAmount);

        return mTicketRepository.save(mTicket);

    }

    private TicketItem getTicketItem(CartDetail mCartDetailItem) {
        TicketItem mTicketItem = new TicketItem();
        mTicketItem.setTicketItemProductName(mCartDetailItem.getmCartDetailProduct().getmProductName());
        mTicketItem.setTicketItemProductPrice(mCartDetailItem.getmCartDetailProduct().getmProductPrice());
        mTicketItem.setTicketItemProductTax(mCartDetailItem.getmCartDetailProduct().getmProductTaxPercent());
        mTicketItem.setTicketItemProductQuantity(mCartDetailItem.getmCartDetailItemQuantity());
        mTicketItem.setTicketItemSubTotal(mCartDetailItem.getmCartDetailItemQuantity()* mCartDetailItem.getmCartDetailProduct().getmProductPrice());
        return mTicketItem;
    }
}
