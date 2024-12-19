package com.coderhouse.ProyectoFinal_PrimeraEntrega.Dao;

import com.coderhouse.ProyectoFinal_PrimeraEntrega.Models.*;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.CriteriaUpdate;
import jakarta.persistence.metamodel.Metamodel;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;


import java.util.List;
import java.util.Map;


@Service
public class DaoFactory {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void clientPersistence(Client pClient) {
        em.persist(pClient);
    }

    @Transactional
    public void cartPersistence(Cart pCart) {
        em.persist(pCart);
    }

    @Transactional
    public void cartDetailtPersistence(CartDetail pCartDetail) {
        em.persist(pCartDetail);
    }


    @Transactional
    public void productPersistence(Product pProduct) {
        em.persist(pProduct);
    }

    @Transactional
    public void ticketPersistence(Ticket pTicket) {
        em.persist(pTicket);
    }




}
