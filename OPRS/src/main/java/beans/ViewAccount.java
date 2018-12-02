/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpSession;
import javax.transaction.UserTransaction;
import persistence.UserAccount;

/**
 *
 * @author nick
 */
@Named(value = "viewAccountBean")
@RequestScoped
public class ViewAccount {
    private String status;
    private String userId;
    private String firstname;
    private String lastname;
    private String birthDate;
    private String city;    
    @PersistenceContext(unitName = "OPRS-PU")
    private EntityManager em;
    @Resource
    private UserTransaction utx;
    /**
     * Creates a new instance of ViewAccount
     */
    public ViewAccount() {
    }
    Logger log = Logger.getLogger(ViewAccount.class.getName());
    @PostConstruct
    private void init() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        UserAccount user = (UserAccount)session.getAttribute("User");
        log.info("RUNNING");
        if (user ==null) {
            log.info("RUNNING2");

            status = "Please login to view account.";
        } else {
            log.info("RUNNING3");

            userId = user.getUserId();
            firstname = user.getFirstname();
            lastname = user.getLastname();
            birthDate = user.getBirthDate().toString();
            city = user.getCity();
        }
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
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the city to set
     */
    public void setStatus(String status) {
        this.status = status;
    }
    
}
