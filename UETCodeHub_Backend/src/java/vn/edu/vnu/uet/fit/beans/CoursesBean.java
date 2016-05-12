/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.edu.vnu.uet.fit.beans;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import vn.edu.vnu.uet.fit.entity.Courses;
import vn.edu.vnu.uet.fit.entity.Courseusers;
import vn.edu.vnu.uet.fit.entity.Courseusersrole;
import vn.edu.vnu.uet.fit.entity.Users;
import vn.edu.vnu.uet.fit.model.GenericModel;
import vn.edu.vnu.uet.fit.supportbeans.CourseProblemsBean;
import vn.edu.vnu.uet.fit.supportbeans.CourseUsersBean;
import vn.edu.vnu.uet.fit.utils.JSFUtil;

/**
 *
 * @author hmduong
 */
@Named(value = "coursesBean")
@SessionScoped
public class CoursesBean extends GenericBean<Courses> implements Serializable {

    Courses cloneFromCourse;
    GenericModel<Courseusers> courseUserModel;

    public CoursesBean() {
        init(Courses.class);
        courseUserModel = new GenericModel<>(Courseusers.class);
    }

    @Override
    public String getEntityMsg(Courses obj) {
        return obj.getCourseName();
    }

    @Override
    public List<Courses> initLstData() {
        return getModel().getAll();
    }

    @Override
    public void create() {        
        Users createdUser = JSFUtil.getLoginBean().getUser();
        obj.setCreatedUser(createdUser);
        super.create();
        try {
            Courseusers courseusers = new Courseusers();
            courseusers.setCourse(obj);
            courseusers.setUser(createdUser);
            Courseusersrole role = new Courseusersrole();
            role.setCourseusersroleId(1);
            courseusers.setRoleInCourse(role);
            courseUserModel.create(courseusers);
            obj = new Courses();
        } catch (Exception ex) {
            JSFUtil.addErrorMessage(null, "Init Course Creator Error", ex.getMessage());
        }
    }

    public void cloneCourse(){
        JSFUtil.addSuccessMessage(null, "Clone course", "Clone from: " + cloneFromCourse.getCourseId());
        obj.setProblems(cloneFromCourse.getProblems());
        System.out.println("===Create object problems size: " + obj.getProblems().size());
        create();
        
    }
    
    //<editor-fold defaultstate="collapsed" desc="Support Function for General List Page">
    public void active(Courses course) {
        try {
            course.setIsActive(true);
            model.update(course);
            JSFUtil.addSuccessMessage(null, "Active success", "Active course: " + getEntityMsg(course));
        } catch (Exception ex) {
            Logger.getLogger(UsersBean.class.getName()).log(Level.SEVERE, null, ex);
            JSFUtil.addErrorMessage(null, "Active fail", ex.getMessage());
        }
    }

    public void deactive(Courses course) {
        try {
            course.setIsActive(false);
            model.update(course);
            JSFUtil.addSuccessMessage(null, "Deactive success", "Deactive course: " + getEntityMsg(course));
        } catch (Exception ex) {
            Logger.getLogger(UsersBean.class.getName()).log(Level.SEVERE, null, ex);
            JSFUtil.addErrorMessage(null, "Deactive fail", ex.getMessage());
        }
    }

    public String displaySelectCourse() {
        if (selected != null) {
            JSFUtil.addSuccessMessage(null, "Context menu", "Select: " + getEntityMsg(selected));
            System.out.println("Select: " + getEntityMsg(selected));

            FacesContext context = FacesContext.getCurrentInstance();
            CourseProblemsBean courseProblemsBean = context.getApplication().evaluateExpressionGet(context, "#{courseProblemsBean}", CourseProblemsBean.class);
            courseProblemsBean.setLst(null);
            CourseUsersBean courseUsersBean = context.getApplication().evaluateExpressionGet(context, "#{courseUsersBean}", CourseUsersBean.class);
            courseUsersBean.setLst(null);

            return "CourseDetail/CourseDetail.xhtml?faces-redirect=true";
        } else {
            JSFUtil.addSuccessMessage(null, "Context menu", "Please select a course");
            return "";
        }
    }
//</editor-fold>

    public Courses getCloneFromCourse() {
        return cloneFromCourse;
    }

    public void setCloneFromCourse(Courses cloneFromCourse) {
        this.cloneFromCourse = cloneFromCourse;
    }

}
