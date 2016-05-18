/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.edu.vnu.uet.fit.supportbeans;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import org.primefaces.event.RowEditEvent;
import vn.edu.vnu.uet.fit.beans.CoursesBean;
import vn.edu.vnu.uet.fit.beans.GenericBean;
import vn.edu.vnu.uet.fit.entity.Courseusers;
import vn.edu.vnu.uet.fit.utils.JSFUtil;

/**
 *
 * @author hmduong
 */
@Named(value = "courseUsersBean")
@SessionScoped
public class CourseUsersBean extends GenericBean<Courseusers> implements Serializable {

    public CourseUsersBean() {
        setEntityClass(Courseusers.class);
    }

    @PostConstruct
    void init(){
        
    }
    
    public CoursesBean getCoursesBean() {
        FacesContext context = FacesContext.getCurrentInstance();
        CoursesBean bean = context.getApplication().evaluateExpressionGet(context, "#{coursesBean}", CoursesBean.class);
        return bean;
    }

    @Override
    public String getEntityMsg(Courseusers obj) {
        return obj.getCourseUserId().toString();
    }

    @Override
    public List<Courseusers> initLstData() {
        CoursesBean coursebean = getCoursesBean();
        if (coursebean != null) {
            return getModel().search("courseId = " + coursebean.getSelected().getCourseId());
            //return getModel().getAll();
        }else{
            JSFUtil.addSuccessMessage(null, "No course select", "No course select");
            return new ArrayList<>();
        }
        
    }

}
