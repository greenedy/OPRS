/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import enums.PropertyType;
import persistence.Property;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Raymo
 */
@Named(value = "searchProperties")
@SessionScoped
public class SearchProperties implements Serializable {
    List<Property> lookupResults;
    private Boolean locationToronto;
    private Boolean locationOttawa;
    private PropertyType propertyType;
    private int numberOfBedrooms;
    private int numberOfBathrooms;
    private int numberOfOtherRooms;
    private int priceOfRent;
    
    
    /**
     * Creates a new instance of searchProperties
     */
    public SearchProperties() {
        
    }
    
    public String search() {
        return "viewProperties";
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
    // show message if no result
    public boolean getShowMessage() {
        return (lookupResults != null) && lookupResults.isEmpty();
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
