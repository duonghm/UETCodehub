/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.edu.vnu.uet.fit.entity.report.course;

import vn.edu.vnu.uet.fit.entity.Courses;

/**
 *
 * @author hmduong
 */
public class CourseReportOverall {
    private Integer reportId;
    private Courses course;   
    private Integer numberOfStudent;
    private Integer numberOfProblem;
    private Float averageScore;

    public CourseReportOverall() {
    }

    public Integer getReportId() {
        return reportId;
    }

    public void setReportId(Integer reportId) {
        this.reportId = reportId;
    }

    public Courses getCourse() {
        return course;
    }

    public void setCourse(Courses course) {
        this.course = course;
    }

    public Integer getNumberOfStudent() {
        return numberOfStudent;
    }

    public void setNumberOfStudent(Integer numberOfStudent) {
        this.numberOfStudent = numberOfStudent;
    }

    public Integer getNumberOfProblem() {
        return numberOfProblem;
    }

    public void setNumberOfProblem(Integer numberOfProblem) {
        this.numberOfProblem = numberOfProblem;
    }

    public Float getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(Float averageScore) {
        this.averageScore = averageScore;
    }
    
}
