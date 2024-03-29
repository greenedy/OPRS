/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import persistence.Property;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import persistence.PropertyDBHelper;

/**
 *
 * @author Raymo
 */
@Named(value = "searchProperties")
@RequestScoped
public class SearchProperties implements Serializable {
    @PersistenceContext(unitName = "OPRS-PU")
    EntityManager em;
    @Resource
    private javax.transaction.UserTransaction utx;
    
    List<Property> lookupResults;
    private Boolean locationToronto;
    private Boolean locationOttawa;
    private String propertyType;
    private int numberOfBedrooms;
    private int numberOfBathrooms;
    private int numberOfOtherRooms;
    private double minPriceOfRent;
    private double maxPriceOfRent;
    private Boolean foundNoResults;
    private Boolean noCriteria;
    
    
    /**
     * Creates a new instance of searchProperties
     */
    public SearchProperties() {
        
    }
    
    public String search() {
       if(0 == getNumberOfBathrooms() 
               && 0 == getNumberOfBedrooms()
               && getPropertyType() == null
               && 0 == getMinPriceOfRent()
               && 0 == getMaxPriceOfRent()
               && !(getLocationOttawa() || getLocationToronto())){
            setNoCriteria(true);
            return(null);
       }
       
       List<Property> results = PropertyDBHelper.findPropertiesWithCriteria(em, this);
       setLookupResults(results);
       if(results == null || results.isEmpty()){setFoundNoResults((Boolean) true);}
        setNoCriteria(false);
       return("viewProperties");
    }
    
    public void setLookupResults(List<Property> results) {
        this.lookupResults = results;
    }
    
    public List<Property> getLookupResults() {
        return lookupResults;
    }
    // show results if any
    public boolean getShowResults() {
        return (lookupResults != null) && !lookupResults.isEmpty();
    }

    /**
     * @return the foundNoResults
     */
    public Boolean getFoundNoResults() {
        return foundNoResults;
    }

    /**
     * @param foundNoResults the foundNoResults to set
     */
    public void setFoundNoResults(Boolean foundNoResults) {
        this.foundNoResults = foundNoResults;
    }
    // show message if no result
    public boolean getShowMessage() {
        return (lookupResults != null) && lookupResults.isEmpty();
    }
    
    
    /**
     * @return the locationToronto
     */
    public Boolean getLocationToronto() {
        return locationToronto;
    }

    /**
     * @param locationToronto the locationToronto to set
     */
    public void setLocationToronto(Boolean locationToronto) {
        this.locationToronto = locationToronto;
    }

    /**
     * @return the locationOttawa
     */
    public Boolean getLocationOttawa() {
        return locationOttawa;
    }

    /**
     * @param locationOttawa the locationOttawa to set
     */
    public void setLocationOttawa(Boolean locationOttawa) {
        this.locationOttawa = locationOttawa;
    }


    /**
     * @return the numberOfBedrooms
     */
    public int getNumberOfBedrooms() {
        return numberOfBedrooms;
    }

    /**
     * @param numberOfBedrooms the numberOfBedrooms to set
     */
    public void setNumberOfBedrooms(int numberOfBedrooms) {
        this.numberOfBedrooms = numberOfBedrooms;
    }

    /**
     * @return the numberOfBathrooms
     */
    public int getNumberOfBathrooms() {
        return numberOfBathrooms;
    }

    /**
     * @param numberOfBathrooms the numberOfBathrooms to set
     */
    public void setNumberOfBathrooms(int numberOfBathrooms) {
        this.numberOfBathrooms = numberOfBathrooms;
    }

    /**
     * @return the numberOfOtherRooms
     */
    public int getNumberOfOtherRooms() {
        return numberOfOtherRooms;
    }

    /**
     * @param numberOfOtherRooms the numberOfOtherRooms to set
     */
    public void setNumberOfOtherRooms(int numberOfOtherRooms) {
        this.numberOfOtherRooms = numberOfOtherRooms;
    }



    /**
     * @return the propertyType
     */
    public String getPropertyType() {
        return propertyType;
    }

    /**
     * @param propertyType the propertyType to set
     */
    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    /**
     * @return the minPriceOfRent
     */
    public double getMinPriceOfRent() {
        return minPriceOfRent;
    }

    /**
     * @param minPriceOfRent the minPriceOfRent to set
     */
    public void setMinPriceOfRent(double minPriceOfRent) {
        this.minPriceOfRent = minPriceOfRent;
    }

    /**
     * @return the maxPriceOfRent
     */
    public double getMaxPriceOfRent() {
        return maxPriceOfRent;
    }

    /**
     * @return the noCriteria
     */
    public Boolean getNoCriteria() {
        return noCriteria;
    }

    /**
     * @param noCriteria the noCriteria to set
     */
    public void setNoCriteria(Boolean noCriteria) {
        this.noCriteria = noCriteria;
    }

    /**
     * @param maxPriceOfRent the maxPriceOfRent to set
     */
    public void setMaxPriceOfRent(double maxPriceOfRent) {
        this.maxPriceOfRent = maxPriceOfRent;
    }

 
    
}
