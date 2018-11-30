/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import persistence.Property;
import beans.SearchProperties;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import persistence.PropertyDBHelper;

/**
 *
 * @author Raymo
 */
@Named(value = "propertyLookupControl")
@RequestScoped
public class PropertyLookupControl {
    @Inject
    private SearchProperties searchProperties;
    @PersistenceContext
    EntityManager em;
    @Resource
    private javax.transaction.UserTransaction utx;
    
    /**
     * Creates a new instance of PropertyLookupControl
     */
    public PropertyLookupControl() {
    }
    public void search() {
       List<Property> results = new ArrayList();
       results = PropertyDBHelper.findPropertiesWithCriteria(em, searchProperties);
       searchProperties.setLookupResults(results);
    }
//    public void add() {
//        if (DBHelper.addUser(em,utx,userData)) {
//            userData.setAddstatus("The User Was Successfuly Added");
//        } else {
//            userData.setAddstatus("Addition of the User Failed");
//        }
//    }
//    
        /**
     * Find a user by id and check if any that the other fields are valid
     */
//    private List<User> getUserById(EntityManager em,UserData userData) {
//        ArrayList<User> result = new ArrayList<>();
//        User user = DBHelper.findUser(em,userData.getId());
//        if (user != null && user.matches(userData)) {
//            result.add(user);  
//        }
//        return result;
//    }
//
//    private List<User> getUsersByName(EntityManager em,UserData userData) {
//       List<User> allresults = DBHelper.findUsersByName(em,userData.getName());
//       if (allresults == null) return null;
//       return checkResults(allresults,userData);          
//    }
//
//    private List getUsersByBirthDate(EntityManager em,UserData userData) {
//       List<User> allresults = DBHelper.findUsersByBirthDate(em,userData.getBirthdate());
//       if (allresults == null) return null;
//       return checkResults(allresults,userData);       
//    }
    
//    private List<Property> checkResults(List<Property> allresults,Sear userData) {
//        ArrayList<Property> results = new ArrayList<>();
//        for (Property property: allresults) {
//            if (property.matches(userData)) results.add(property);
//        }
//        return results;
//    }
}

