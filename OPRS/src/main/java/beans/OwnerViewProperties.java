/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import javax.el.ELContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpSession;
import persistence.Property;
import persistence.PropertyDBHelper;
import persistence.UserAccount;

/**
 *
 * @author Nick
 */
@Named(value = "ownerViewProperties")
@SessionScoped
public class OwnerViewProperties implements Serializable {

    @PersistenceContext(unitName = "OPRS-PU")
    private EntityManager em;
    /**
     * Creates a new instance of OwnerViewProperties
     */
    public OwnerViewProperties() {
    }
    
        public void onload(){
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        UserAccount user = (UserAccount)session.getAttribute("User");
        if (user !=null) {
            ELContext elContext = FacesContext.getCurrentInstance().getELContext();
            SearchProperties searchProperties = (SearchProperties) elContext.getELResolver().getValue(elContext, null, "searchProperties");
            try {
             ArrayList<Property> properties = new ArrayList<>(PropertyDBHelper.getOwenedProperties(em, user.getUserId()));
                searchProperties.setLookupResults(properties);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
}
