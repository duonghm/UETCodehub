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

    public LoginBean() {
    }

    @PostConstruct
    void init() {
        model = new GenericModel(Users.class);
        user = new Users();
        loggedin = false;
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

    public String login() {
        List<Users> l = model.search("username = '" + user.getUsername() + "' and password = '" + user.getPassword() + "'");
        if (l.size() > 0) {
            user = l.get(0);
            if (user.isIsActive()) {
                loggedin = true;
                return "/Teacher/Courses/Courses.xhtml?faces-redirect=true";
            }else{
                JSFUtil.addErrorMessage(null, "Login fail", "Account is not actived");
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
