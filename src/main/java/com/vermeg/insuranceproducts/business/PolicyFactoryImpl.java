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
public class PolicyFactoryImpl implements PolicyFactory{

    @Override
    public PolicyBuilder LifePolicyBuilder() throws SecurityException {
    return new LifePolicyBuilderImpl();
    }

    @Override
    public PolicyBuilder CarPolicyBuilder() throws SecurityException {
       return new CarPolicyBuilderImpl();
    }
    
}
