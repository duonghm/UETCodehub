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
import vn.edu.vnu.uet.fit.entity.report.course.CourseReportOverall;
import vn.edu.vnu.uet.fit.entity.report.course.StudentDetailReportByCourse;
import vn.edu.vnu.uet.fit.entity.report.course.StudentReportByCourse;
import vn.edu.vnu.uet.fit.model.CourseReportModel;

/**
 *
 * @author hmduong
 */
@Named(value = "courseReportBean")
@SessionScoped
public class CourseReportBean implements Serializable {

    private CourseReportModel model;
    private List<CourseReportOverall> lst;

    public CourseReportModel getModel() {
        if(model == null){
            model = new CourseReportModel();
        }
        return model;
    }

    public List<CourseReportOverall> getLst() {
        if(lst == null){
            lst = getModel().getCourseReports();
        }
        return lst;
    }

    public void setLst(List<CourseReportOverall> lst) {
        this.lst = lst;
    }
    
    public List<StudentReportByCourse> getCourseReport(int courseId){
        return getModel().getStudentResultByCourse(courseId);
    }
    
    public List<StudentDetailReportByCourse> getStudentInCourseReport(int courseId, int userId){
        return getModel().getStudentDetailResultByCourse(courseId, userId);
    }
    
    public CourseReportBean() {
    }
    
}
