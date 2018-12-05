package beans;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import enums.UserType;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;
import persistence.UserAccount;

/**
 *
 * @author green
 */
@ManagedBean
public class MenuView {
 
    private MenuModel model;
 
    @PostConstruct
    public void init() {
        
        
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        
        UserAccount user = (UserAccount)session.getAttribute("User");
        
        model = new DefaultMenuModel();
        
        if (user == null) {
          
        DefaultSubMenu firstSubmenu = new DefaultSubMenu("OPRS");
        
        DefaultMenuItem itemIndex = new DefaultMenuItem("Home");
        itemIndex.setOutcome("index");
        firstSubmenu.addElement(itemIndex);
        
        DefaultMenuItem itemSearch = new DefaultMenuItem("Search Properties");
        itemSearch.setOutcome("searchProperties");
        firstSubmenu.addElement(itemSearch);
 
        model.addElement(firstSubmenu);
        
        DefaultSubMenu secondSubmenu = new DefaultSubMenu("");
 
        DefaultMenuItem itemLogin = new DefaultMenuItem("Login");
        itemLogin.setOutcome("login");
        secondSubmenu.addElement(itemLogin);
  
        model.addElement(secondSubmenu);
            
            
        } else if(user.getUserType().equals(UserType.AGENT)){
            
        DefaultSubMenu firstSubmenu = new DefaultSubMenu("OPRS");
        
        DefaultMenuItem itemIndex = new DefaultMenuItem("Home");
        itemIndex.setOutcome("index");
        firstSubmenu.addElement(itemIndex);
        
        DefaultMenuItem itemCreateAccount = new DefaultMenuItem("Create Account");
        itemCreateAccount.setOutcome("signIn");
        firstSubmenu.addElement(itemCreateAccount);
        
        DefaultMenuItem itemSearch = new DefaultMenuItem("Search Properties");
        itemSearch.setOutcome("searchProperties");
        firstSubmenu.addElement(itemSearch);
 
        model.addElement(firstSubmenu);
        
        
        DefaultSubMenu secondSubmenu = new DefaultSubMenu(user.getFirstname()+" "+user.getLastname());
 
        DefaultMenuItem itemViewAccount = new DefaultMenuItem("View Account");
        itemViewAccount.setOutcome("viewAccount");
        secondSubmenu.addElement(itemViewAccount);
        
        DefaultMenuItem itemLogout = new DefaultMenuItem("Logout");
        itemLogout.setCommand("#{menuView.logout}");
        secondSubmenu.addElement(itemLogout);
  
        model.addElement(secondSubmenu);
            
        } else if(user.getUserType().equals(UserType.CUSTOMER)){
            
        DefaultSubMenu firstSubmenu = new DefaultSubMenu("OPRS");
        
        DefaultMenuItem itemIndex = new DefaultMenuItem("Home");
        itemIndex.setOutcome("index");
        firstSubmenu.addElement(itemIndex);
               
        DefaultMenuItem itemSearch = new DefaultMenuItem("Search Properties");
        itemSearch.setOutcome("searchProperties");
        firstSubmenu.addElement(itemSearch);
        
        model.addElement(firstSubmenu);
        
        
        DefaultSubMenu secondSubmenu = new DefaultSubMenu(user.getFirstname()+" "+user.getLastname());
        
        DefaultMenuItem itemViewViewingList = new DefaultMenuItem("View Viewing List");
        itemViewViewingList.setOutcome("viewViewingList");
        secondSubmenu.addElement(itemViewViewingList);
        
        DefaultMenuItem itemViewAccount = new DefaultMenuItem("View Account");
        itemViewAccount.setOutcome("viewAccount");
        secondSubmenu.addElement(itemViewAccount);
 
        DefaultMenuItem itemLogout = new DefaultMenuItem("Logout");
        itemLogout.setCommand("#{menuView.logout}");
        secondSubmenu.addElement(itemLogout);
  
        model.addElement(secondSubmenu);
            
            
            
        } else if(user.getUserType().equals(UserType.OWNER)){
            
        DefaultSubMenu firstSubmenu = new DefaultSubMenu("OPRS");
        
        DefaultMenuItem itemIndex = new DefaultMenuItem("Home");
        itemIndex.setOutcome("index");
        firstSubmenu.addElement(itemIndex);
        
        DefaultMenuItem itemAddProperty = new DefaultMenuItem("Add Property");
        itemAddProperty.setOutcome("addProperty");
        firstSubmenu.addElement(itemAddProperty);
        
        DefaultMenuItem itemSearch = new DefaultMenuItem("Search Properties");
        itemSearch.setOutcome("searchProperties");
        firstSubmenu.addElement(itemSearch);
 
        model.addElement(firstSubmenu);
        
        DefaultSubMenu secondSubmenu = new DefaultSubMenu(user.getFirstname()+" "+user.getLastname());
        
        DefaultMenuItem itemOwnerViewProperties = new DefaultMenuItem("View Owned Properties");
        itemOwnerViewProperties.setOutcome("ownerViewProperties");
        secondSubmenu.addElement(itemOwnerViewProperties);
 
        DefaultMenuItem itemViewAccount = new DefaultMenuItem("View Account");
        itemViewAccount.setOutcome("viewAccount");
        secondSubmenu.addElement(itemViewAccount);
        
        DefaultMenuItem itemLogout = new DefaultMenuItem("Logout");
        itemLogout.setCommand("#{menuView.logout}");
        secondSubmenu.addElement(itemLogout);
  
        model.addElement(secondSubmenu);
            
            
        }
 
       
    }
 
    public MenuModel getModel() {
        return model;
    }
 
    public String logout() {
        // invalidate session to remove User
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        session.invalidate();
        // navigate to index - see faces-config.xml for navigation rules
        return "index?faces-redirect=true";
    }
 
    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}