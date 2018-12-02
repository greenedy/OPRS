/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import persistence.UserAccount;
import persistence.Property;
import persistence.PropertyDBHelper;
import persistence.VisitingListDBHelper;

/**
 *
 * @author Raymo
 */
@Named(value = "VisitingListBean")
@RequestScoped
public class VisitingListBean {
    private String propertyId;
    @PersistenceContext
    EntityManager em;
    @Resource
    private javax.transaction.UserTransaction utx;
    private Boolean propertyFound = false;
    
    public String addToVisitingList(){
        try {
           VisitingListDBHelper.addToVisitingList(utx, em, propertyId);
           
           //Check if the added Property is actually in the UserAccounts properties Set
           HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
           UserAccount user = (UserAccount) session.getAttribute("User");
           Set<Property> userProperties = user.getProperties();
           setPropertyFound((Boolean) userProperties.contains(PropertyDBHelper.findProperty(em, propertyId)));
           
           
        } catch(RuntimeException e) {
           String msg = "Error While adding Property";
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
           FacesContext.getCurrentInstance().getExternalContext()
                .getFlash().setKeepMessages(true);
        }
        
        return("addToVisitingList");
    }
    
    /**
     * @return the propertyId
     */
    public String getPropertyId() {
        return propertyId;
    }

    /**
     * @param propertyId the propertyId to set
     */
    public void setPropertyId(String propertyId) {
        this.propertyId = propertyId;
    }

    /**
     * @return the propertyFound
     */
    public Boolean getPropertyFound() {
        return propertyFound;
    }

    /**
     * @param propertyFound the propertyFound to set
     */
    public void setPropertyFound(Boolean propertyFound) {
        this.propertyFound = propertyFound;
    }
    
}
