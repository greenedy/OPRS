/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.PhaseId;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import persistence.Address;
import persistence.Property;


/**
 *
 * @author green
 */
@Named(value = "PropertyBean")
@SessionScoped
public class PropertyBean implements Serializable {
    /**
     * Internal class to represent images prior to persisting
     */
    class Image {
        byte[] contents;
        String type;

        private Image(byte[] contents, String type) {
            this.contents = contents;
            this.type = type;
        }

        private byte[] getContents() {
            return contents;
        }

        private String getType() {
            return type;
        }
    }
    
    private String owner;
    private String type;
    private int numTotalRooms;
    private int numBathrooms;
    private int numBedrooms;
    private Date availableDate;
    private String description;
    private String number;
    private String street;
    private String unit;
    private String city;
    private String province;
    private String postalCode;
    @PersistenceContext(unitName = "OPRS-PU")
    private EntityManager em;
    @Resource
    private javax.transaction.UserTransaction utx;
    private Map<String,Image> images;
    
    private List<Property> lookupResults;

    /**
     * Creates a new instance of UserProfileBean
     */
    public PropertyBean() {
        images = new TreeMap<>();
    }

    /**
     * @return the firstName
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
     * @param description the bio to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the number
     */
    public String getNumber() {
        return number;
    }

    /**
     * @param number the number to set
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * @return the name
     */
    public String getStreet() {
        return street;
    }

    /**
     * @param street the name to set
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * @return the unit
     */
    public String getUnit() {
        return unit;
    }

    /**
     * @param unit the unit to set
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the province
     */
    public String getProvince() {
        return province;
    }

    /**
     * @param province the province to set
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * @return the postalCode
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * @param postalCode the postalCode to set
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public void handlePropertyPicUpload(FileUploadEvent event) {
        UploadedFile uploadedFile = event.getFile();
        try {
            byte[] contents = IOUtils.toByteArray(uploadedFile.getInputstream()); // uploadedFile.getContents() doesn't work as expected
            //byte[] contents = uploadedFile.getContents();
            String type = uploadedFile.getContentType();
            Image image = new Image(contents, type);
            String filename = uploadedFile.getFileName();
            images.put(filename, image);
        } catch (IOException ex) {
            Logger.getLogger(PropertyBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public StreamedContent getStreamedImage() {
        FacesContext context = FacesContext.getCurrentInstance();

        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            return new DefaultStreamedContent();
        } else {
            String name = context.getExternalContext().getRequestParameterMap().get("id");
            Image image = images.get(name);

            return new DefaultStreamedContent(
                    new ByteArrayInputStream(image.getContents()), image.getType());
        }
    }

    /**
     * @return the imageIds
     */
    public Collection<String> getImageIds() {
        return images.keySet();
    }

    /**
     * Add the user to the database
     * @param actionEvent
     * @return 
     */
    public String doAddProperty(ActionEvent actionEvent) {
        Address address = new Address(number, street, unit, city, province, postalCode);
        Property property = new Property(owner, type, numTotalRooms, numBathrooms, numBedrooms, availableDate, address, description);
        for (Image p: images.values()) {
            persistence.Image pim = new persistence.Image(p.getContents(),p.getType());
            pim.setProperty(property);
            property.addPicture(pim);
        }
        try {
           persist(property); 
           String msg = "Property Created Successfully";
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
           FacesContext.getCurrentInstance().getExternalContext()
                .getFlash().setKeepMessages(true);
           FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
           FacesContext.getCurrentInstance().getViewRoot().getViewMap().clear();
        } catch(RuntimeException e) {
           String msg = "Error While Creating Property";
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
           FacesContext.getCurrentInstance().getExternalContext()
                .getFlash().setKeepMessages(true);
        }
        return null;
    }
    
       public String doUpdateProperty(ActionEvent actionEvent) {
        Address address = new Address(number, street, unit, city, province, postalCode);
        Property property = new Property(owner, type, numTotalRooms, numBathrooms, numBedrooms, availableDate, address, description);
        for (Image p: images.values()) {
            persistence.Image pim = new persistence.Image(p.getContents(),p.getType());
            pim.setProperty(property);
            property.addPicture(pim);
        }
        try {
           persist(property); 
           String msg = "Property Updated Successfully";
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
           FacesContext.getCurrentInstance().getExternalContext()
                .getFlash().setKeepMessages(true);
           FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
           FacesContext.getCurrentInstance().getViewRoot().getViewMap().clear();
        } catch(RuntimeException e) {
           String msg = "Error While Updating Property";
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
           FacesContext.getCurrentInstance().getExternalContext()
                .getFlash().setKeepMessages(true);
        }
        return null;
    }

    public void persist(Object object) {
        try {
            utx.begin();
            em.persist(object);
            utx.commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            throw new RuntimeException(e);
        }
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
    

}
