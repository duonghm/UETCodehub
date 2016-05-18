/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.edu.vnu.uet.fit.supportbeans;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import vn.edu.vnu.uet.fit.beans.GenericBean;
import vn.edu.vnu.uet.fit.entity.Courseusersrole;

/**
 *
 * @author hmduong
 */
@Named(value = "courseUsersRoleBean")
@SessionScoped
public class CourseUsersRoleBean extends GenericBean<Courseusersrole>implements Serializable {
    
    public CourseUsersRoleBean() {
        init(Courseusersrole.class);
    }

    @Override
    public String getEntityMsg(Courseusersrole obj) {
        return obj.getCourseusersroleName();
    }

    @Override
    public List<Courseusersrole> initLstData() {
        return getModel().getAll();
    }
    
}
