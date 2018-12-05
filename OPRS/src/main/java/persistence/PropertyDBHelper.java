/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package persistence;

import beans.SearchProperties;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Raymond
 */
public class PropertyDBHelper {
    public static Property findProperty(EntityManager em,String id) {
        Property p = em.find(Property.class, id);
        return p;
    }
    
    
    public static List<Property> findPropertiesWithCriteria(EntityManager em, SearchProperties searchProperty){
        String initQueryString = "SELECT p FROM Property p";
        String whereClauseConditions = "";
        Query query;
        
        //Set conditions for the WHERE clause of the Query
        
        
        if(!(0 == searchProperty.getNumberOfBathrooms())){ //If the NumberOfBathrooms field was used by the User
            whereClauseConditions += " p.numBathrooms = :bathNum";
        }
        if(!(0 == searchProperty.getNumberOfBedrooms())){
            if(!"".equals(whereClauseConditions)){whereClauseConditions += " AND";} //If a Condition has been added already
            whereClauseConditions += " p.numBedrooms = :bedNum";
        }
        if(!(searchProperty.getPropertyType() == null)){
            if(!"".equals(whereClauseConditions)){whereClauseConditions += " AND";} //If a Condition has been added already
            whereClauseConditions += " p.type = :pType";
        }
        if(!(0 == searchProperty.getMinPriceOfRent())){
            if(!"".equals(whereClauseConditions)){whereClauseConditions += " AND";} //If a Condition has been added already
            whereClauseConditions += " p.priceOfRent >= :minRent";
        }
        if(!(0 == searchProperty.getMaxPriceOfRent())){
            if(!"".equals(whereClauseConditions)){whereClauseConditions += " AND";} //If a Condition has been added already
            whereClauseConditions += " p.priceOfRent <= :maxRent";
        }
        if(searchProperty.getLocationOttawa() || searchProperty.getLocationToronto()){
            if(!"".equals(whereClauseConditions)){whereClauseConditions += " AND";} //If a Condition has been added already
            
            if(searchProperty.getLocationOttawa() && searchProperty.getLocationToronto()){
                whereClauseConditions += " (p.address.city = :pLocOttawa OR p.address.city = :pLocToronto)";
            }else if(searchProperty.getLocationOttawa()){
                whereClauseConditions += " p.address.city = :pLocOttawa";
            }else{
                whereClauseConditions += " p.address.city = :pLocToronto";
            } 
        }

        
        
        //If there are conditions add the WHERE clause and its conditions 
        if(!"".equals(whereClauseConditions)){
            initQueryString += " WHERE"; 
            initQueryString += whereClauseConditions; 
            query = em.createQuery(initQueryString);
        
            //Set Parameters of the Query
            if(!(0 == searchProperty.getNumberOfBathrooms())){
                query.setParameter("bathNum", searchProperty.getNumberOfBathrooms());
            }
            if(!(0 == searchProperty.getNumberOfBedrooms())){
                query.setParameter("bedNum", searchProperty.getNumberOfBedrooms());
            }
            if(!(searchProperty.getPropertyType() == null)){
                query.setParameter("pType", searchProperty.getPropertyType());
            }
            if(!(0 == searchProperty.getMinPriceOfRent())){
                query.setParameter("minRent", searchProperty.getMinPriceOfRent());
            }
            if(!(0 == searchProperty.getMaxPriceOfRent())){
                query.setParameter("maxRent", searchProperty.getMaxPriceOfRent());
            }
            if(searchProperty.getLocationOttawa()){
                query.setParameter("pLocOttawa", "Ottawa");
            }
            if(searchProperty.getLocationToronto()){
                query.setParameter("pLocToronto", "Toronto");
            } 
            
        }else{
            //Select all Properties
            query = em.createQuery("SELECT p FROM Property p");
        }
        return performQuery(query);
    }

    public static List<Property> getOwenedProperties(EntityManager em, String userId){
        String queryString = "SELECT p FROM Property p WHERE p.ownerId = '" + userId +"'";
        Query ownerQuery = em.createQuery(queryString);
        return performQuery(ownerQuery);
    }
    
    private static List<Property> performQuery(final Query query) {
        List<Property> resultList = query.getResultList();
        if (resultList.isEmpty()) {
            return null;
        } 
        ArrayList<Property> results = new ArrayList<>();
        results.addAll(resultList);
        return results;
    }
    
    

}
