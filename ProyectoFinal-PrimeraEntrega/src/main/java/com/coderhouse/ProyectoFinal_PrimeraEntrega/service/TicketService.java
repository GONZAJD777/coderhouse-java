package com.coderhouse.ProyectoFinal_PrimeraEntrega.service;

import com.coderhouse.ProyectoFinal_PrimeraEntrega.dto.ticket.TicketExtendedDTO;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.mapper.TicketMapper;
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
    @Autowired
    private CartService mCartService;
    @Autowired
    private ProductService mProductService;


    public TicketService(TicketRepository pTicketRepository, ClientRepository pClientRepository, CartService mCartService) {
        this.mTicketRepository = pTicketRepository;
        this.mClientRepository = pClientRepository;
        this.mCartService = mCartService;
    }

    public List<Ticket> listAll() {
        return mTicketRepository.findAll();
    }

    public Ticket getTicketById(Long pTicketId) {
        if(!mTicketRepository.existsById(pTicketId)) {
            throw new RuntimeException("Ticket not found with ID: " + pTicketId);
        }
        return mTicketRepository.findById(pTicketId).get();
    }

    public List<Ticket> getTicketByClientId(Long pClientId) {
        if(!mClientRepository.existsById(pClientId)) {
            throw new RuntimeException("Client not found with ID: " + pClientId);
        }
        Client mClient = mClientRepository.findById(pClientId).get();
        return mTicketRepository.findByMTicketClient(mClient);
    }

    @Transactional
    public TicketExtendedDTO createTicket(Long pClientId) {

        List<TicketItem> mTicketItemList = new ArrayList<>();
        List<CartDetail> mNewCartDetailList = new ArrayList<>();
        List<CartDetail> mSoldCartDetailList = new ArrayList<>();
        TicketExtendedDTO mTicketExtendedDTO = new TicketExtendedDTO();

        if(!mClientRepository.existsById(pClientId)) {
            throw new RuntimeException("Client not found with ID: " + pClientId);
        }

        Client mClient = mClientRepository.findById(pClientId).get();
        Cart mCart = mClient.getmClientCart();
        float mTicketTotalAmount = 0f; //variable que acumulara el total de la venta a medida q se recorre el carrito y se agregan los productos al ticket

        if(mCart.getmCartDetailList().isEmpty()) {
            throw new RuntimeException("Cart has no items: " + pClientId);
        }

        for (CartDetail mCartDetailItem : mCart.getmCartDetailList()){
            if(mCartDetailItem.getmCartDetailItemQuantity()<=mCartDetailItem.getmCartDetailProduct().getmProductStock()){
                TicketItem mTicketItem = getTicketItem(mCartDetailItem);
                mTicketTotalAmount = mTicketTotalAmount + mTicketItem.getmTicketItemSubTotal();
                mTicketItemList.add(mTicketItem);
                //items "vendidos", una vez creado el ticket, se debe actualizar el stock de los productos en la BD.
                mSoldCartDetailList.add(mCartDetailItem);
            } else {
                //items que no se pudieron vender por falta de stock
                //tambien sera el nuevo CartDetail del carrito , vacio o con los items q no se pudieron vender
                mNewCartDetailList.add(mCartDetailItem);
            }
        }

        if (!mSoldCartDetailList.isEmpty()){
            Ticket mTicket = new Ticket();
            mTicket.setmTicketClient(mClient);
            mTicket.setmTicketCreationDate(LocalDateTime.now());
            mTicket.setmTicketDetail(mTicketItemList);
            mTicket.setmTicketTotal(mTicketTotalAmount);

            //procedemos a guardar el ticket en la base de datos
            //luego se actualiza el carrito haciendo uso del metodo en CartService
            //y se actualiza el nuevo stock del producto con el metodo de ProductService
            mTicketRepository.save(mTicket);
            for (CartDetail mCartDetailItem : mSoldCartDetailList){
                Product mAuxProduct = new Product();
                    //recuperamos el id del producto vendido
                mAuxProduct.setmProductId(mCartDetailItem.getmCartDetailProduct().getmProductId());
                    //recuperamos el stock del producto vendido y le descotamos la cantidad de items
                mAuxProduct.setmProductStock(mCartDetailItem.getmCartDetailProduct().getmProductStock()-mCartDetailItem.getmCartDetailItemQuantity());

                mCartService.updateCart(mCart.getmCartId()
                        ,mCartDetailItem.getmCartDetailProduct().getmProductId()
                        ,mCartDetailItem.getmCartDetailItemQuantity()*(-1) //se llama al servicio de carritos para agregar una cantidad negativa lo q removera el item
                );
                    //actualizamos el stock del producto
                mProductService.updateProduct(mAuxProduct);
            }
            mTicketExtendedDTO = TicketMapper.toExtendedDTO(mTicket,mNewCartDetailList);
        } else {
            mTicketExtendedDTO = TicketMapper.toExtendedDTO(null,mNewCartDetailList);
        }


        return  mTicketExtendedDTO;
    }

    private TicketItem getTicketItem(CartDetail mCartDetailItem) {
        TicketItem mTicketItem = new TicketItem();
        mTicketItem.setmTicketItemProductName(mCartDetailItem.getmCartDetailProduct().getmProductName());
        mTicketItem.setmTicketItemProductPrice(mCartDetailItem.getmCartDetailProduct().getmProductPrice());
        mTicketItem.setmTicketItemProductTax(mCartDetailItem.getmCartDetailProduct().getmProductTaxPercent());
        mTicketItem.setmTicketItemProductQuantity(mCartDetailItem.getmCartDetailItemQuantity());
        mTicketItem.setmTicketItemSubTotal(mCartDetailItem.getmCartDetailItemQuantity()* mCartDetailItem.getmCartDetailProduct().getmProductPrice());
        return mTicketItem;
    }


}
