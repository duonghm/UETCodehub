/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.edu.vnu.uet.fit.beans;

import java.io.Serializable;
import vn.edu.vnu.uet.fit.entity.Problems;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;
import vn.edu.vnu.uet.fit.entity.Users;
import vn.edu.vnu.uet.fit.model.GenericModel;
import vn.edu.vnu.uet.fit.utils.JSFUtil;


/**
 *
 * @author hmduong
 */
@Named(value = "problemsBean")
@SessionScoped
public class ProblemsBean extends GenericBean<Problems> implements Serializable{

    public ProblemsBean() {
        init(Problems.class);
    }
    
    @Override
    public String getEntityMsg(Problems obj) {
        return obj.getProblemId().toString();
    }

    @Override
    public List<Problems> initLstData() {
        return getModel().getAll();
    }

    @Override
    public void create() {
        Users createdUser = JSFUtil.getLoginBean().getSearchUser();
        obj.setUser(createdUser);        
        super.create(); 
    }
    
    public void deactive(Problems problem){
        try {
            problem.setIsActive(false);
            getModel().update(problem);
            JSFUtil.addSuccessMessage(null, "Deactive success", "Deactive problem: " + getEntityMsg(problem));
        } catch (Exception ex) {
            Logger.getLogger(UsersBean.class.getName()).log(Level.SEVERE, null, ex);
            JSFUtil.addErrorMessage(null, "Deactive fail", ex.getMessage());
        }
    }
    
    public void active(Problems problem){
        try {
            problem.setIsActive(true);
            getModel().update(problem);
            JSFUtil.addSuccessMessage(null, "Active success", "Active account: " + getEntityMsg(problem));
        } catch (Exception ex) {
            Logger.getLogger(UsersBean.class.getName()).log(Level.SEVERE, null, ex);
            JSFUtil.addErrorMessage(null, "Active fail", ex.getMessage());
        }
    }
 
}
