/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enums;
/**
 *
 * @author Nick
 */
public enum UserType {
    AGENT("Agent"),
    CUSTOMER("Customer"),
    OWNER("Owner");
    
    private String label;
    
    private UserType(String label) {
        this.label = label;
    }
    
    public String getLabel(){
        return label;
    }
}
