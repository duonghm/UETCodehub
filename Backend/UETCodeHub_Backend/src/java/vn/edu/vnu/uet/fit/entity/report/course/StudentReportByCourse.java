/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.edu.vnu.uet.fit.entity.report.course;

import vn.edu.vnu.uet.fit.entity.Users;

/**
 *
 * @author hmduong
 */
public class StudentReportByCourse {
    private Integer reportId;
    private Integer courseId;
    private Users user;
    private Integer summitedExercise;
    private Integer courseScore;

    public Integer getReportId() {
        return reportId;
    }

    public void setReportId(Integer reportId) {
        this.reportId = reportId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Integer getSummitedExercise() {
        return summitedExercise;
    }

    public void setSummitedExercise(Integer summitedExercise) {
        this.summitedExercise = summitedExercise;
    }

    public Integer getCourseScore() {
        return courseScore;
    }

    public void setCourseScore(Integer courseScore) {
        this.courseScore = courseScore;
    }
    
    
}
