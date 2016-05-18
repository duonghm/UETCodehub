/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.edu.vnu.uet.fit.utils;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import vn.edu.vnu.uet.fit.beans.LoginBean;

/**
 *
 * @author hmduong
 */
public class JSFUtil {
    
    public static void addSuccessMessage(String id, String summary, String detail){
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(id, message);
    }
    
    public static void addErrorMessage(String id, String summary, String detail){
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, detail);
        FacesContext.getCurrentInstance().addMessage(id, message);
    }
    
    public static LoginBean getLoginBean(){
        FacesContext context = FacesContext.getCurrentInstance();
        LoginBean bean = context.getApplication().evaluateExpressionGet(context, "#{loginBean}", LoginBean.class);
        return bean;
    }
}
