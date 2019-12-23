package com.vermeg.insuranceproducts.controllers;

import com.vermeg.insuranceproducts.business.ISFactory;
import com.vermeg.insuranceproducts.business.PolicyBuilder;
import com.vermeg.insuranceproducts.business.SecurityException;
import com.vermeg.insuranceproducts.entities.Question;
import com.vermeg.insuranceproducts.controllers.util.JsfUtil;
import com.vermeg.insuranceproducts.controllers.util.JsfUtil.PersistAction;
import com.vermeg.insuranceproducts.ejb.QuestionFacade;

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

@Named("questionController")
@SessionScoped
public class QuestionController implements Serializable {

    @EJB
    private com.vermeg.insuranceproducts.ejb.QuestionFacade ejbFacade;
    private List<Question> items = null;
    private Question selected;
    private List<Question> lifeItems = null;
    private List<Question> carItems = null;

    public QuestionController() {
    }

    public Question getSelected() {
        return selected;
    }

    public void setSelected(Question selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private QuestionFacade getFacade() {
        return ejbFacade;
    }

    public Question prepareCreate() {
        selected = new Question();
        initializeEmbeddableKey();
        return selected;
    }

    
    public void createLife() {
        selected.setProductType("LIFE");
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("QuestionCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

        
    public void createCar() {
        selected.setProductType("CAR");
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("QuestionCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("QuestionCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("QuestionUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("QuestionDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Question> getItems() {
        if (items == null) {
            try {
                PolicyBuilder PB = ISFactory.getInstance().getPolicyFactory().LifePolicyBuilder().setQuestions(getFacade().findAll());
                items = PB.getQuestions();
            } catch (SecurityException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = ex.getMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            }
        }
        return items;
    }

    public List<Question> getLifeItems() {
      
            try {
                PolicyBuilder PB = ISFactory.getInstance().getPolicyFactory().LifePolicyBuilder().setQuestions(getFacade().findAll());
                lifeItems = PB.getQuestions();
            } catch (SecurityException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = ex.getMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            }
        
        return lifeItems;
    }

    public List<Question> getCarItems() {

            try {
                PolicyBuilder PB = ISFactory.getInstance().getPolicyFactory().CarPolicyBuilder().setQuestions(getFacade().findAll());
                carItems = PB.getQuestions();
            } catch (SecurityException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = ex.getMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            }
       
        return carItems;
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

    public Question getQuestion(java.lang.String id) {
        return getFacade().find(id);
    }

    public List<Question> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Question> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Question.class)
    public static class QuestionControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            QuestionController controller = (QuestionController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "questionController");
            return controller.getQuestion(getKey(value));
        }

        java.lang.String getKey(String value) {
            java.lang.String key;
            key = value;
            return key;
        }

        String getStringKey(java.lang.String value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Question) {
                Question o = (Question) object;
                return getStringKey(o.getRef());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Question.class.getName()});
                return null;
            }
        }

    }

}
