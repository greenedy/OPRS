/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import persistence.UserAccount;
import persistence.Property;
import persistence.PropertyDBHelper;

/**
 *
 * @author Raymo
 */
@Named(value = "VisitingListBean")
@RequestScoped
public class VisitingListBean {

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
    @PersistenceContext
    EntityManager em;
    @Resource
    private javax.transaction.UserTransaction utx;
    private String propertyId;
    /**
     * Creates a new instance of VisitingListBean
     */
    public VisitingListBean() {
    }
    
    public String addToVisitingList(){
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        UserAccount user = (UserAccount) session.getAttribute("User");
        user.addProperty(PropertyDBHelper.findProperty(em, getPropertyId()));
        merge(user);
        return("index");
    }
    
    public void merge(Object object) {
        try {
            utx.begin();
            em.merge(object);
            utx.commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            throw new RuntimeException(e);
        }
    }
    
}
