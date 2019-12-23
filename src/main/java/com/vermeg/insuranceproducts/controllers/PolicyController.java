package com.vermeg.insuranceproducts.controllers;

import com.vermeg.insuranceproducts.entities.Policy;
import com.vermeg.insuranceproducts.controllers.util.JsfUtil;
import com.vermeg.insuranceproducts.controllers.util.JsfUtil.PersistAction;
import com.vermeg.insuranceproducts.ejb.PolicyFacade;
import com.vermeg.insuranceproducts.entities.Answer;
import com.vermeg.insuranceproducts.entities.Question;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

@Named("policyController")
@SessionScoped
public class PolicyController implements Serializable {

    @EJB
    private com.vermeg.insuranceproducts.ejb.PolicyFacade ejbFacade;
    @Inject
    QuestionController questionsService;
    
    @Inject
    AnswerController answerService;
    
    
    
    
    Map<Question, Answer> lifeFields;
    
    Map<Question, Answer> carFields;

    public Map<Question, Answer> getLifeFields() {
            lifeFields= new HashMap<>();
            for(Question question : questionsService.getLifeItems()){
                Answer answer = new Answer();
                answer.setQuestion(question);
                answer.setPolicy(selected);
            }
        return lifeFields;
    }

    public void setLifeFields(Map<Question, Answer> lifeFields) {
        this.lifeFields = lifeFields;
    }

    public Map<Question, Answer> getCarFields() {
        return carFields;
    }

    public void setCarFields(Map<Question, Answer> carFields) {
        this.carFields = carFields;
    }
        public void createLife() {
        selected.setInsuranceType("LIFE");
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("QuestionCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

        
    public void createCar() {
        selected.setInsuranceType("CAR");
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("QuestionCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }
    
    
    private List<Policy> items = null;
    private Policy selected;

    
    
    public PolicyController() {
    }

    public Policy getSelected() {
        return selected;
    }

    public void setSelected(Policy selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private PolicyFacade getFacade() {
        return ejbFacade;
    }

    public Policy prepareCreate() {
        selected = new Policy();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("PolicyCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("PolicyUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("PolicyDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Policy> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Policy getPolicy(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Policy> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Policy> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Policy.class)
    public static class PolicyControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PolicyController controller = (PolicyController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "policyController");
            return controller.getPolicy(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Policy) {
                Policy o = (Policy) object;
                return getStringKey(o.getIdentifier());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Policy.class.getName()});
                return null;
            }
        }

    }

}
