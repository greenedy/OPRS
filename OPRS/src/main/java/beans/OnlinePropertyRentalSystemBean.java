/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author green
 */
@Named(value = "OnlinePropertyRentalSystemBean")
@RequestScoped
public class OnlinePropertyRentalSystemBean {

    public OnlinePropertyRentalSystemBean() {
    }

    public String index(){
        return "index";
    }
    public String searchProperties(){
        return "searchProperties";
    }
    public String addProperty(){
        return "addProperty";
    }
     public String viewProperty(){
        return "viewProperty?faces-redirect=true";
    }
    public String login(){
        return "login";
    }

    
}
