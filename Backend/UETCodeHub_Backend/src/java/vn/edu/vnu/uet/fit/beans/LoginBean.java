/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.edu.vnu.uet.fit.beans;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;
import vn.edu.vnu.uet.fit.entity.Users;
import vn.edu.vnu.uet.fit.model.GenericModel;
import vn.edu.vnu.uet.fit.utils.JSFUtil;

/**
 *
 * @author hmduong
 */
@ManagedBean(name = "loginBean")
@javax.faces.bean.SessionScoped
public class LoginBean implements Serializable {

    private Users user;
    boolean loggedin;
    GenericModel<Users> model;
    Users searchUser;

    public LoginBean() {
    }

    @PostConstruct
    void init() {
        model = new GenericModel(Users.class);
        user = new Users();
        loggedin = false;
    }

    public GenericModel<Users> getModel() {
        if (model == null) {
            model = new GenericModel<>(Users.class);
        }
        return model;
    }

    public void setModel(GenericModel<Users> model) {
        this.model = model;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public boolean isLoggedin() {
        return loggedin;
    }

    public void setLoggedin(boolean loggedin) {
        this.loggedin = loggedin;
    }

    public Users getSearchUser() {
        return searchUser;
    }

    public String login() {
        List<Users> l = getModel().search("username = '" + user.getUsername() + "' and password = '" + user.getPassword() + "'");
        if (l.size() > 0) {
            searchUser = l.get(0);
            if (searchUser.isIsActive()) {
                loggedin = true;
                //return "/Teacher/Courses/Courses.xhtml?faces-redirect=true";
                switch (searchUser.getRole().getRoleId()) {
                    case 1:
                        return "/User/Administrator/Users/Users.xhtml?faces-redirect=true";                        
                    case 2:
                        return "/User/Teacher/Courses/Courses.xhtml?faces-redirect=true";                        
                    default:
                        loggedin = false;
                        JSFUtil.addErrorMessage(null, "Login fail", "Account has no permission to access");
                        return "";
                        
                }
                
            } else {
                JSFUtil.addErrorMessage(null, "Login fail", "Account is not actived");
                user = new Users();
                searchUser = null;
                return "";
            }
        } else {
            JSFUtil.addErrorMessage(null, "Login fail", "Username and password invalid");
            return "";
        }
    }

    public String logout() {
        user = new Users();
        loggedin = false;
        return "/Login/Login.xhtml?faces-redirect=true";
    }

}
