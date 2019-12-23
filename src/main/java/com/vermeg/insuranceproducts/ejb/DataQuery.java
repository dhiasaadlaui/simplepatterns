/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vermeg.insuranceproducts.ejb;

import com.vermeg.insuranceproducts.entities.Broker;

/**
 *
 * @author mdsaadlaoui
 */
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DataQuery {


    
    EntityManagerFactory emf;
    EntityManager em;
    public DataQuery(){
        emf = Persistence.createEntityManagerFactory("com.vermeg_insuranceproducts_war_2.0-SNAPSHOTPU");
        em = emf.createEntityManager();
    }
    public Broker loginControl(String email, String password){
        
        try{
           
       
        Broker a = em.createNamedQuery("Broker.findByUsername", Broker.class)
                .setParameter("username", email)
                .getSingleResult();
        if (a!=null){
            return a;
        }
        return a;
       } catch (Exception e) {
           return null;
       } 
    }
        public void refreshData(){
            emf.getCache().evictAll();
        }
}

