/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.edu.vnu.uet.fit.beans;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import org.hibernate.JDBCException;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;
import vn.edu.vnu.uet.fit.entity.Userrole;
import vn.edu.vnu.uet.fit.entity.Users;
import vn.edu.vnu.uet.fit.model.GenericModel;
import vn.edu.vnu.uet.fit.utils.JSFUtil;

/**
 *
 * @author hmduong
 */
@Named(value = "usersBean")
@SessionScoped
//@RequestScoped
public class UsersBean implements Serializable{
    
    Users createdUser;
    GenericModel model;
    List<Users> lst;
    List<Users> selectedUsers;   
    
    List<Userrole> lstRole;
    GenericModel roleModel;

    public List<Users> getLst() {
        if(lst == null){
            lst = model.getAll();
        }
        return lst;
    }

    public void setLst(List<Users> lst) {
        this.lst = lst;
    }

    public Users getCreatedUser() {
        return createdUser;
    }
    
    public void setCreatedUser(Users createdUser) {
        this.createdUser = createdUser;
    }   
    
    public UsersBean() {
        model = new GenericModel(Users.class);
        roleModel = new GenericModel(Userrole.class);
        createdUser = new Users();
    }    

    public List<Users> getSelectedUsers() {
        return selectedUsers;
    }

    public void setSelectedUsers(List<Users> selectedUser) {
        this.selectedUsers = selectedUser;
    }

    public List<Userrole> getLstRole() {
        if(lstRole == null){
            lstRole = roleModel.getAll();
        }
        return lstRole;
    }
    
    
        
    //<editor-fold defaultstate="collapsed" desc="Datatable method">
    public void create(){
        try{
            System.out.println("CREATE USER: " + createdUser);
            model.create(createdUser);
            JSFUtil.addSuccessMessage("frm:msg", "Insert success", "Insert success account " + createdUser.getUsername());
            RequestContext.getCurrentInstance().execute("PF('wgCreate').hide()");
            lst = null;
        }catch(JDBCException ex){
            System.out.println("=== ERROR CODE: " + ex.getErrorCode());
            JSFUtil.addErrorMessage(null, "SQL Error", "Insert error code: " + ex.getErrorCode());
        }catch(Exception ex){
            System.out.println("=== ERROR: " + ex.getMessage());
            JSFUtil.addErrorMessage(null, "Error", ex.getMessage());
        }
    }
    
    public void onRowEdit(RowEditEvent evt){
        try {
            Users selectedUser = (Users)evt.getObject();
            model.update(selectedUser);
            JSFUtil.addSuccessMessage(null, "Edit Success", selectedUser.getUsername());
        } catch (Exception ex) {
            JSFUtil.addErrorMessage(null, "Edit Fail", ex.getMessage());
            Logger.getLogger(UsersBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void onRowCancel(RowEditEvent evt){
        Users selectedUser = (Users)evt.getObject();
        JSFUtil.addSuccessMessage(null, "Edit Cancel", selectedUser.getUsername());
    }
    
    public void deactive(Users user){
        try {
            user.setIsActive(false);
            model.update(user);
            JSFUtil.addSuccessMessage(null, "Deactive success", "Deactive account: " +user.getUsername());
        } catch (Exception ex) {
            Logger.getLogger(UsersBean.class.getName()).log(Level.SEVERE, null, ex);
            JSFUtil.addErrorMessage(null, "Deactive fail", ex.getMessage());
        }
    }
    
    public void active(Users user){
        try {
            user.setIsActive(true);
            model.update(user);
            JSFUtil.addSuccessMessage(null, "Active success", "Active account: " +user.getUsername());
        } catch (Exception ex) {
            Logger.getLogger(UsersBean.class.getName()).log(Level.SEVERE, null, ex);
            JSFUtil.addErrorMessage(null, "Active fail", ex.getMessage());
        }
    }
    
//</editor-fold>
}
