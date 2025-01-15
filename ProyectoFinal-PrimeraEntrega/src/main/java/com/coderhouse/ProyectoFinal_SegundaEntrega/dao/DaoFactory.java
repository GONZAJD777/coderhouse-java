package com.coderhouse.ProyectoFinal_SegundaEntrega.dao;

import com.coderhouse.ProyectoFinal_SegundaEntrega.model.*;
import jakarta.persistence.*;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;


@Service
public class DaoFactory {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void clientPersistence(Client pClient) {
        em.persist(pClient);
    }

    @Transactional
    public void productPersistence(Product pProduct) {
        em.persist(pProduct);
    }

}
