/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vermeg.insuranceproducts.ejb;

import com.vermeg.insuranceproducts.entities.Broker;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author mdsaadlaoui
 */
@Stateless
public class BrokerFacade extends AbstractFacade<Broker> {

    @PersistenceContext(unitName = "com.vermeg_insuranceproducts_war_2.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BrokerFacade() {
        super(Broker.class);
    }
    
}
