package com.vermeg.insuranceproducts.controllers;

import com.vermeg.insuranceproducts.entities.Possiblevalues;
import com.vermeg.insuranceproducts.controllers.util.JsfUtil;
import com.vermeg.insuranceproducts.controllers.util.JsfUtil.PersistAction;
import com.vermeg.insuranceproducts.ejb.PossiblevaluesFacade;

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

@Named("possiblevaluesController")
@SessionScoped
public class PossiblevaluesController implements Serializable {

    @EJB
    private com.vermeg.insuranceproducts.ejb.PossiblevaluesFacade ejbFacade;
    private List<Possiblevalues> items = null;
    private Possiblevalues selected;

    public PossiblevaluesController() {
    }

    public Possiblevalues getSelected() {
        return selected;
    }

    public void setSelected(Possiblevalues selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private PossiblevaluesFacade getFacade() {
        return ejbFacade;
    }

    public Possiblevalues prepareCreate() {
        selected = new Possiblevalues();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("PossiblevaluesCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("PossiblevaluesUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("PossiblevaluesDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Possiblevalues> getItems() {
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

    public Possiblevalues getPossiblevalues(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Possiblevalues> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Possiblevalues> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Possiblevalues.class)
    public static class PossiblevaluesControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PossiblevaluesController controller = (PossiblevaluesController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "possiblevaluesController");
            return controller.getPossiblevalues(getKey(value));
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
            if (object instanceof Possiblevalues) {
                Possiblevalues o = (Possiblevalues) object;
                return getStringKey(o.getIdentifier());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Possiblevalues.class.getName()});
                return null;
            }
        }

    }

}
