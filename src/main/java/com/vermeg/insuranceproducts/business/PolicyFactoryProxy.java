/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vermeg.insuranceproducts.business;

/**
 *
 * @author mdsaadlaoui
 */
public class PolicyFactoryProxy implements PolicyFactory {

    PolicyFactory policyFactory;

    public PolicyFactoryProxy(PolicyFactory policyFactory) {
        this.policyFactory = policyFactory;
    }
    
    @Override
    public PolicyBuilder LifePolicyBuilder() throws SecurityException {
        if (null!=ISFactory.getInstance().getCurrentUser() && ISFactory.getInstance().getCurrentUser().getRole().getLife() ) {
            return policyFactory.LifePolicyBuilder();
        }
        throw new SecurityException();
    }

    @Override
    public PolicyBuilder CarPolicyBuilder() throws SecurityException {
        if (null!=ISFactory.getInstance().getCurrentUser() && ISFactory.getInstance().getCurrentUser().getRole().getCar()) {
            return policyFactory.CarPolicyBuilder();
       }
       throw new SecurityException();
    }

}
