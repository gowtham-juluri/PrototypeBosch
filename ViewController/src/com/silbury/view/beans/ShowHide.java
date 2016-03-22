package com.silbury.view.beans;

import java.io.Serializable;

public class ShowHide implements Serializable {
    
    private String xyz = null;
    
    public ShowHide() {
    }
    
    
    
    public void setxyz(String xyz){
        this.xyz = xyz;
        }
    
    public String getxyz(){
        return xyz;
        }


    public String ShowHide_action() {
        String status = getxyz();
        
        if (status=="unHide"){
            this.setxyz("Hide");
        }   else{
            this.setxyz("unHide");
            }
        return null;
    }

}
