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
public interface PolicyFactory {
    
    PolicyBuilder LifePolicyBuilder() throws SecurityException;
    PolicyBuilder CarPolicyBuilder()throws SecurityException;

}
