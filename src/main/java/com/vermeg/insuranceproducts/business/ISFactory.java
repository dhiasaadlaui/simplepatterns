/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vermeg.insuranceproducts.business;

import com.vermeg.insuranceproducts.entities.Broker;


/**
 *
 * @author mdsaadlaoui
 */
public class ISFactory {
    private Broker currentUser;
    private static ISFactory instance=null;
    PolicyFactory policyFactory;
    private ISFactory(){    
        this.policyFactory=new PolicyFactoryProxy(new PolicyFactoryImpl());
    }
    
    public static ISFactory getInstance(){
        if(instance==null)
        instance = new ISFactory();
        return instance;
    }

    public Broker getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(Broker currentUser) {
        this.currentUser = currentUser;
    }

    public PolicyFactory getPolicyFactory() {
        return policyFactory;
    }
    
    
    
    
    
    
}
