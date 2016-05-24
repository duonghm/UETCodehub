/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.edu.vnu.uet.fit.beans;

import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import vn.edu.vnu.uet.fit.entity.Courses;
import vn.edu.vnu.uet.fit.entity.Exams;
import vn.edu.vnu.uet.fit.entity.report.course.StudentDetailReportByCourse;
import vn.edu.vnu.uet.fit.entity.report.course.StudentReportByCourse;
import vn.edu.vnu.uet.fit.model.GenericModel;
import vn.edu.vnu.uet.fit.model.ReportModel;

/**
 *
 * @author hmduong
 */
@Named(value = "reportBean")
@Dependent
public class ReportBean {

    ReportModel model;
    public List<StudentReportByCourse> lstStudentReport;

    public ReportBean() {
    }

    public ReportModel getModel() {
        if(model == null){
            model = new ReportModel();
        }
        return model;
    }

    public List<StudentReportByCourse> getLstStudentReport() {
        if(lstStudentReport == null){
            lstStudentReport = getModel().getStudentResultByCourse(2);
        }
        return lstStudentReport;
    }

    public void setLstStudentReport(List<StudentReportByCourse> lstStudentReport) {
        this.lstStudentReport = lstStudentReport;
    }

    
    
}
