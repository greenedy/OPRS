package beans;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
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
import persistence.PropertyDBHelper;

/**
 *
 * @author green
 */
@Named(value = "viewPropertyBean")
@ManagedBean(name="viewPropertyBean")
@ViewScoped
public class viewPropertyBean implements Serializable {
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
    private Long propertyId;
    private Property property;
    private String owner;
    private String type;
    private double priceOfRent;

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public double getPriceOfRent() {
        return priceOfRent;
    }

    public void setPriceOfRent(double priceOfRent) {
        this.priceOfRent = priceOfRent;
    }
    private int numTotalRooms;
    private int numBathrooms;
    private int numBedrooms;
    private Date availableDate;
    private String description;
    private Address address;
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
    
   
    /**
     * Creates a new instance of UserProfileBean
     */
    public viewPropertyBean() {
        images = new TreeMap<>();
        
                        
    }
    
    public void onload() {
    
       property = PropertyDBHelper.findProperty(em, Long.toString(propertyId));  
       this.owner = property.getOwner();
       this.type = property.getType();
       this.numTotalRooms = property.getNumTotalRooms();
       this.numBathrooms = property.getNumBathrooms();
       this.numBedrooms = property.getNumBedrooms();
       this.priceOfRent = property.getPriceOfRent();
       this.availableDate = property.getAvailableDate();
       this.description = property.getDescription();
       this.address = property.getAddress();
       this.number = address.getNumber();
       this.street = address.getName();
       this.unit = address.getUnit();
       this.city = address.getCity();
       this.province = address.getProvince();
       this.postalCode = address.getPostalCode();
               
    }

    
     /**
     * @return the propertyId
     */
    public Long getPropertyId() {
        return propertyId;
    }

    /**
     * @param propertyId the id to set
     */
    public void setPropertyId(Long propertyId) {
        this.propertyId = propertyId;
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
     * @return the address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * @return the address
     */
    public String getAddressString() {
        return address.getNumber()+" "+address.getName() +" "+ address.getUnit();
    }
    
    /**
     * @param address the address to set
     */
    public void setAddress(Address address) {
        this.address = address;
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

    public String goToUpdateProperty() {
        return "updateProperty?faces-redirect=true&propertyId="+property.getPropertyId()+"";
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
            Logger.getLogger(viewPropertyBean.class.getName()).log(Level.SEVERE, null, ex);
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
    
       
     public String doDeleteProperty() {
       
       
        try {
            
            utx.begin();
            if (!em.contains(property)) {
                property = em.merge(property);
            }
            em.remove(property);
            utx.commit();
          
           String msg = "Property Removed Successfully";
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
           FacesContext.getCurrentInstance().getExternalContext()
                .getFlash().setKeepMessages(true);
           FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
           FacesContext.getCurrentInstance().getViewRoot().getViewMap().clear();
           return "index?faces-redirect=true";
        } catch(RuntimeException e) {
           String msg = "Error While Removing Property";
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
           FacesContext.getCurrentInstance().getExternalContext()
                .getFlash().setKeepMessages(true);
        } catch (Exception e) {
            Logger.getLogger(updatePropertyBean.class.getName()).log(Level.SEVERE, null, e);
        } 
        return null;
    
    }

}