/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author green
 */
@Entity
@Table(name="Property")
public class Property implements Serializable {
    private static long serialVersionUID = 1L;

    /**
     * @return the serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * @param aSerialVersionUID the serialVersionUID to set
     */
    public static void setSerialVersionUID(long aSerialVersionUID) {
        serialVersionUID = aSerialVersionUID;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String propertyId;
    private String owner;
    private String type;
    private double priceOfRent;
    private int numTotalRooms;
    private int numBathrooms;
    private int numBedrooms;
    @Temporal(TemporalType.DATE)
    private Date availableDate;
    @Lob
    private String description;
    @OneToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn(name="PROPERTY_ADDRESS",
            referencedColumnName="id")
    private Address address;
    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, 
            mappedBy="property")
    private Collection<Image> pictures;
    @ManyToMany(mappedBy = "properties")
    private Set<UserAccount> users = new HashSet<>();
    
    
    public Property() {
        
    }
    
    public Property(String owner, String type, int numTotalRooms, 
            int numBathrooms, int numBedrooms, Date availableDate, Address address,
            String description, double priceOfRent) {
        this.owner = owner;
        this.type = type;
        this.numTotalRooms = numTotalRooms;
        this.numBathrooms = numBathrooms;
        this.numBedrooms = numBedrooms;
        this.availableDate = availableDate;
        this.address = address;
        this.description = description;
        this.priceOfRent = priceOfRent;
        this.pictures = new ArrayList<>();
    }
    
    public String getPropertyId() {
        return propertyId;
    }

    public void setId(String id) {
        this.propertyId = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (propertyId != null ? propertyId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Property)) {
            return false;
        }
        Property other = (Property) object;
        if ((this.propertyId == null && other.propertyId != null) || (this.propertyId != null && !this.propertyId.equals(other.propertyId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Property[ propertyId=" + propertyId + " ]";
    }

    /**
     * @return the owner
     */
    public String getOwner() {
        return owner;
    }

    /**
     * @param owner the owner to set
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the numTotalRooms
     */
    public int getNumTotalRooms() {
        return numTotalRooms;
    }

    /**
     * @param numTotalRooms the numTotalRooms to set
     */
    public void setNumTotalRooms(int numTotalRooms) {
        this.numTotalRooms = numTotalRooms;
    }

    /**
     * @return the numBathrooms
     */
    public int getNumBathrooms() {
        return numBathrooms;
    }

    /**
     * @param numBathrooms the numBathrooms to set
     */
    public void setNumBathrooms(int numBathrooms) {
        this.numBathrooms = numBathrooms;
    }
    
    /**
     * @return the numBedrooms
     */
    public int getNumBedrooms() {
        return numBedrooms;
    }

    /**
     * @param numBedrooms the numBedrooms to set
     */
    public void setNumBedrooms(int numBedrooms) {
        this.numBedrooms = numBedrooms;
    }

    /**
     * @return the availableDate
     */
    public Date getAvailableDate() {
      return availableDate;
    }

    /**
     * @param availableDate the availableDate to set
     */
    public void setAvailableDate(Date availableDate) {
        this.availableDate = availableDate;
    }

  /**
   * @return the description
   */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

  /**
   * @return the address
   */
    public Address getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * @return the pictures
     */
    public Collection<Image> getPictures() {
        return pictures;
    }

    /**
     * @param pictures the pictures to set
     */
    public void setPictures(Collection<Image> pictures) {
        this.pictures = pictures;
    }

    public void addPicture(Image pim) {
        this.pictures.add(pim);
    }

    /**
     * @return the users
     */
    public Set<UserAccount> getUsers() {
        return users;
    }

    /**
     * @param users the users to set
     */
    public void setUsers(Set<UserAccount> users) {
        this.users = users;
    }

    /**
     * @return the priceOfRent
     */
    public double getPriceOfRent() {
        return priceOfRent;
    }

    /**
     * @param priceOfRent the priceOfRent to set
     */
    public void setPriceOfRent(double priceOfRent) {
        this.priceOfRent = priceOfRent;
    }
}
