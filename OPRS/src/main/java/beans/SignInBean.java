/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import enums.UserType;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Date;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpSession;
import persistence.UserAccount;

/**
 *
 * @author ssome
 */
@Named(value = "signInBean")
@RequestScoped
public class SignInBean {
    private String userId;
    private UserType userType;
    private String firstname;
    private String lastname;
    private String birthDate;
    private String city;
    private String password;
    @PersistenceContext(unitName = "OPRS-PU")
    private EntityManager em;
    @Resource
    private javax.transaction.UserTransaction utx;
    
    private String status;
    /**
     * Creates a new instance of SignInBean
     */
    public SignInBean() {
    }

    
    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
    
    /**
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    public UserType[] getUserTypes() {
        return UserType.values();
    }

    /**
     * @return the firstname
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * @param firstname the firstname to set
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * @return the lastname
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * @param lastname the lastname to set
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * @return the birthDate
     */
    public String getBirthDate() {
        return birthDate;
    }

    /**
     * @param birthDate the birthDate to set
     */
    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
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
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the status message
     */
    public String getStatus() {
        return status;
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
    
    public String addUser() {
        try {
            UserAccount acc = new UserAccount();
            acc.setUserId(userId);
            acc.setUserType(userType);
            acc.setFirstname(firstname);
            acc.setLastname(lastname);
            acc.setCity(city);
            acc.setBirthDate(Date.valueOf(birthDate));
            // randomly generate salt value
            final Random r = new SecureRandom();
            byte[] salt = new byte[32];
            r.nextBytes(salt);
            String saltString = new String(salt, "UTF-8");
            // hash password using SHA-256 algorithm
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            String saltedPass = saltString+password;
            byte[] passhash = digest.digest(saltedPass.getBytes("UTF-8"));
            acc.setSalt(salt);
            acc.setPassword(passhash);
            persist(acc);
            

            return "login?faces-redirect=true";
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException | RuntimeException ex ) {
            Logger.getLogger(SignInBean.class.getName()).log(Level.SEVERE, null, ex);
            String msg = "Error Creating Account";
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
           FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
           FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
           FacesContext.getCurrentInstance().getViewRoot().getViewMap().clear();
        }
        
        return null;
    }
    
}
