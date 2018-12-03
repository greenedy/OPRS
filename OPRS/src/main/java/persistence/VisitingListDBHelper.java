/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package persistence;

import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;
import javax.transaction.UserTransaction;

/**
 *
 * @author Raymond
 */
public class VisitingListDBHelper {
    
    
    public static void addToVisitingList(UserTransaction utx, EntityManager em, String propertyId){
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        UserAccount user = (UserAccount) session.getAttribute("User");
        user.addProperty(PropertyDBHelper.findProperty(em, propertyId));
        merge(user, utx, em);
    }
    
    public static void merge(Object object, UserTransaction utx, EntityManager em) {
        try {
            utx.begin();
            em.merge(object);
            utx.commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
