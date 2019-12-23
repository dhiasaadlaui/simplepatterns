/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vermeg.insuranceproducts.controllers;

/**
 *
 * @author mdsaadlaoui
 */
import com.vermeg.insuranceproducts.business.ISFactory;
import com.vermeg.insuranceproducts.ejb.DataQuery;
import com.vermeg.insuranceproducts.entities.Broker;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;

import javax.faces.context.FacesContext;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import org.primefaces.PrimeFaces;

@Named("login")
@javax.enterprise.context.SessionScoped
public class LoginController implements Serializable {

    private String email;
    private String password;
    private DataQuery query = new DataQuery();
    private Broker logedAdmin = new Broker();
    private Boolean isLoged = false;

    @Inject
    BrokerController userService;

    public Boolean getIsLoged() {
        return isLoged;
    }

    public void setIsLoged(Boolean isLoged) {
        this.isLoged = isLoged;
    }

    public Broker getLogedAdmin() {
        return logedAdmin;
    }

    public void setLogedAdmin(Broker logedAdmin) {
        this.logedAdmin = logedAdmin;
    }

    public String onload() {
        return "index.xhtml";
    }

    public String logout() {
        setIsLoged(false);
        setLogedAdmin(null);
        ISFactory.getInstance().setCurrentUser(null);
        return "/carpolicies/List.xhtml?faces-redirect=true";
    }

    public String loginControl() {
        List<Broker> brokers = userService.getItems();
   
            if (null!=brokers.get(0)) {
                setIsLoged(true);
                ISFactory.getInstance().setCurrentUser(brokers.get(0));
                logedAdmin = brokers.get(0);
                System.out.println("logincontroller bolean" + getIsLoged());
                return "lifepolicies/List.xhtml?faces-redirect=true";
            }
    

        PrimeFaces.current().ajax().update("growl");
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Invalid username or password !! "));
        return "";

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
