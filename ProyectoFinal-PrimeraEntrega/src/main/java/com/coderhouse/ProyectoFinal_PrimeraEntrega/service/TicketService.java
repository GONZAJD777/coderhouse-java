package com.coderhouse.ProyectoFinal_PrimeraEntrega.service;

import com.coderhouse.ProyectoFinal_PrimeraEntrega.dto.DateTimeRestAPI;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.dto.cart.CartDTO;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.dto.client.ClientDTO;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.dto.ticket.TicketDTO;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.dto.ticket.TicketExtendedDTO;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.exception.CustomException;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.mapper.TicketMapper;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.model.*;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.repository.ClientRepository;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.repository.TicketRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class TicketService {



    @Autowired
    private TicketRepository mTicketRepository;
    @Autowired
    private ClientRepository mClientRepository;
    @Autowired
    private CartService mCartService;
    @Autowired
    private WorldDateService mWorldDateService;
    @Autowired
    private ProductService mProductService;
    @Autowired
    private ClientService mClientService;


    public TicketService(TicketRepository pTicketRepository, ClientRepository pClientRepository, CartService mCartService, ProductService mProductService, RestTemplate mRestTemplate, WorldDateService mWorldDateService, ClientService mClientService) {
        this.mTicketRepository = pTicketRepository;
        this.mClientRepository = pClientRepository;
        this.mCartService = mCartService;
        this.mProductService = mProductService;
        this.mWorldDateService = mWorldDateService;
        this.mClientService = mClientService;
    }

    public List<Ticket> listAll() throws CustomException {
        try {
            return mTicketRepository.findAll();
        } catch (DataAccessException dbe) {
            throw new CustomException(ErrorType.DATABASE_ISSUES);
        } catch (RuntimeException rte) {
            throw new CustomException(ErrorType.SYSTEM_ERROR);
        } catch (Exception e) {
            throw new CustomException(ErrorType.SYSTEM_ERROR);
        }

    }

    public Ticket getTicketById(Long pTicketId) throws CustomException {
        try {
            if(!mTicketRepository.existsById(pTicketId)) {
                throw new CustomException(ErrorType.TICKET_NOT_FOUND,ErrorType.TICKET_NOT_FOUND.getFormattedMessage(pTicketId));
            }
            return mTicketRepository.findById(pTicketId).get();
        } catch (CustomException ce) {
            throw ce;
        } catch (DataAccessException dbe) {
            throw new CustomException(ErrorType.DATABASE_ISSUES);
        } catch (RuntimeException rte) {
            throw new CustomException(ErrorType.SYSTEM_ERROR);
        } catch (Exception e) {
            throw new CustomException(ErrorType.SYSTEM_ERROR);
        }

    }

    public List<Ticket> getTicketByClientId(Long pClientId) throws CustomException {
        try {
            if(!mClientRepository.existsById(pClientId)) {
                throw new CustomException(ErrorType.CLIENT_NOT_FOUND,ErrorType.CLIENT_NOT_FOUND.getFormattedMessage(pClientId));
            }
            Client mClient = mClientRepository.findById(pClientId).get();
            return mTicketRepository.findByMTicketClient(mClient);

        } catch (CustomException ce) {
            throw ce;
        } catch (DataAccessException dbe) {
            throw new CustomException(ErrorType.DATABASE_ISSUES);
        } catch (RuntimeException rte) {
            throw new CustomException(ErrorType.SYSTEM_ERROR);
        } catch (Exception e) {
            throw new CustomException(ErrorType.SYSTEM_ERROR);
        }
    }

    @Transactional
    public TicketExtendedDTO createTicket(Long pClientId) throws CustomException {

        List<TicketItem> mTicketItemList = new ArrayList<>();
        List<CartDetail> mNewCartDetailList = new ArrayList<>();
        List<CartDetail> mSoldCartDetailList = new ArrayList<>();
        TicketExtendedDTO mTicketExtendedDTO = new TicketExtendedDTO();
        try {

            Client mClient = mClientService.getClient(pClientId);
            Cart mCart = mClient.getmClientCart();
            float mTicketTotalAmount = 0f; //variable que acumulara el total de la venta a medida q se recorre el carrito y se agregan los productos al ticket

            if(mCart.getmCartDetailList().isEmpty()) {
                throw new CustomException(ErrorType.CART_IS_EMPTY,ErrorType.CART_IS_EMPTY.getFormattedMessage(""));
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
                mTicket.setmTicketCreationDate(this.getUTCDate());
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

                    mCartService.addProductToCart(mCart.getmCartId()
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

        } catch (CustomException ce) {
            throw ce;
        } catch (DataAccessException dbe) {
            throw new CustomException(ErrorType.DATABASE_ISSUES);
        } catch (RuntimeException rte) {
            throw new CustomException(ErrorType.SYSTEM_ERROR);
        } catch (Exception e) {
            throw new CustomException(ErrorType.SYSTEM_ERROR);
        }
    }

    private Date getUTCDate () throws CustomException {
        DateTimeRestAPI mDateTimeRestAPI;
        try {
            mDateTimeRestAPI = mWorldDateService.makeRequest();
            assert mDateTimeRestAPI != null;
            System.out.println("Respuesta del servicio: "+mDateTimeRestAPI.toString());
            return mDateTimeRestAPI.getCurrentDateTime();
        } catch (Exception e)
        {
            Calendar calendar = Calendar.getInstance();
            TimeZone timeZone = TimeZone.getDefault();
            calendar.setTimeZone(timeZone);
            System.out.println("Fecha calculada , el servicio no respondio: "+calendar.getTime());
            System.out.println("Respuesta del servicio: "+e);
            return calendar.getTime();
            //throw new CustomException(ErrorType.DATE_SERVICE_UNAVAILABLE);
        }
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
