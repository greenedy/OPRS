/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import java.util.Set;
import javax.annotation.Resource;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpSession;
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
public class VisitingListBean implements Serializable {
    private String propertyId;
    @PersistenceContext(unitName = "OPRS-PU")
    EntityManager em;
    @Resource
    private javax.transaction.UserTransaction utx;
    private Boolean propertyFound = false;
    
    public String addToVisitingListTest(){
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
    
    public String addToVisitingList(){
        VisitingListDBHelper.addToVisitingList(utx, em, propertyId);
        return("index");
    }
    
    public String doNothing(){
        System.out.println("");
        return("index");
    }
    
    // show results if any
    public boolean getShowResults() {
        return true;
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
