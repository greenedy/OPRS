/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import enums.PropertyType;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author Raymo
 */
@Named(value = "searchProperties")
@SessionScoped
public class searchProperties implements Serializable {
    private Boolean locationToronto;
    private Boolean locationOttawa;
    private PropertyType propertyType;
    private int numberOfBedrooms;
    private int numberOfBathrooms;
    private int numberOfOtherRooms;
    private int priceOfRent;
    
    private Boolean selLocToronto;
    private Boolean selLocOttawa;
    private PropertyType selPropType;
    private int selNumBedrooms;
    private int selNumBathrooms;
    private int selNumOtherRooms;
    private int selRent;
    
    
    /**
     * Creates a new instance of searchProperties
     */
    public searchProperties() {
        
    }
    
    public void search(Boolean locationToronto, Boolean locationOttawa, PropertyType propertyType, int numberOfBedrooms, int numberOfBathrooms, int numberOfOtherRooms, int priceOfRent) {
        ArrayList<Object> results = new ArrayList<>();
        if (locationToronto == selLocToronto && locationOttawa == selLocOttawa && propertyType == selPropType && numberOfBedrooms == selNumBedrooms && numberOfBathrooms == selNumBathrooms && numberOfOtherRooms == selNumOtherRooms && priceOfRent == selRent){
            List<Object[]> obj = findProperties(selLocToronto, selLocOttawa, selPropType, selNumBedrooms, selNumBathrooms, selNumOtherRooms,selRent);
        }
    }
    
    private List findProperties(Boolean selLocToronto, Boolean selLocOttawa, PropertyType selPropType, int selNumBedrooms, int selNumBathrooms, int selNumOtherRooms,int selRent) {
        String queryString = "SELECT a FROM PropertyBean";
        //Query query = em.createQuery(queryString)
                //.setParameter("minRent", rent)
                //.setParameter("maxRent", maxRent);

        //return performQuery();
        return null;
    }
    
    private static List performQuery(final Query query) {
        List resultList = query.getResultList();
        if (resultList.isEmpty()) {
            return null;
        }
        ArrayList<Object[]> results;
        results = new ArrayList<>();
        results.addAll(resultList);
        return results;
    }
    
    public PropertyType[] getTypes () {
        return PropertyType.values();
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
     * @return the priceOfRent
     */
    public int getPriceOfRent() {
        return priceOfRent;
    }

    /**
     * @param priceOfRent the priceOfRent to set
     */
    public void setPriceOfRent(int priceOfRent) {
        this.priceOfRent = priceOfRent;
    }

    /**
     * @return the propertyType
     */
    public PropertyType getPropertyType() {
        return propertyType;
    }

    /**
     * @param propertyType the propertyType to set
     */
    public void setPropertyType(PropertyType propertyType) {
        this.propertyType = propertyType;
    }
    
}
