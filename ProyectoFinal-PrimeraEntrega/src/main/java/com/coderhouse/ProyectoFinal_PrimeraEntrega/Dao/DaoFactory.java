package com.coderhouse.ProyectoFinal_PrimeraEntrega.Dao;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.CriteriaUpdate;
import jakarta.persistence.metamodel.Metamodel;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;



import com.coderhouse.ProyectoFinal_PrimeraEntrega.Models.Cart;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.Models.Client;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.Models.Product;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.Models.Ticket;

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
    public void productPersistence(Product pProduct) {
        em.persist(pProduct);
    }

    @Transactional
    public void ticketPersistence(Ticket pTicket) {
        em.persist(pTicket);
    }
}
