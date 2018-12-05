/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.el.ELContext;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import persistence.Property;
import persistence.UserAccount;

/**
 *
 * @author Nick
 */
@Named(value = "viewVistingListBean")
@SessionScoped
public class ViewViewingListBean implements Serializable {
    
    private UserAccount user;
    private List<Property> properties;

    /**
     * Creates a new instance of ViewVistingListBean
     */
    public ViewViewingListBean() {
    }

    public void onload(){
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        user = (UserAccount)session.getAttribute("User");
        if (user !=null) {
            ELContext elContext = FacesContext.getCurrentInstance().getELContext();
            SearchProperties searchProperties = (SearchProperties) elContext.getELResolver().getValue(elContext, null, "searchProperties");
            properties = new ArrayList<Property>(user.getProperties());
            searchProperties.setLookupResults(properties);
        }
    }
    
}
