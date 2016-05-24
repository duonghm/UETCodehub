/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.edu.vnu.uet.fit.entity.report.course;

/**
 *
 * @author hmduong
 */
public class StudentDetailReportByCourse {
    private Integer reportId;
    private Integer courseId;
    private Integer userId;
    private Integer problemId;
    private Integer submitId;
    private String submissionResult;
    private Integer resultScore;

    public StudentDetailReportByCourse() {
    }

    public StudentDetailReportByCourse(Integer userId, Integer problemId, Integer submitId) {
        this.userId = userId;
        this.problemId = problemId;
        this.submitId = submitId;
    }

    public StudentDetailReportByCourse(Integer reportId, Integer courseId, Integer userId, Integer problemId, Integer submitId, String submissionResult, Integer resultScore) {
        this.reportId = reportId;
        this.courseId = courseId;
        this.userId = userId;
        this.problemId = problemId;
        this.submitId = submitId;
        this.submissionResult = submissionResult;
        this.resultScore = resultScore;
    }

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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getProblemId() {
        return problemId;
    }

    public void setProblemId(Integer problemId) {
        this.problemId = problemId;
    }

    public Integer getSubmitId() {
        return submitId;
    }

    public void setSubmitId(Integer submitId) {
        this.submitId = submitId;
    }

    public String getSubmissionResult() {
        return submissionResult;
    }

    public void setSubmissionResult(String submissionResult) {
        this.submissionResult = submissionResult;
    }

    public Integer getResultScore() {
        return resultScore;
    }

    public void setResultScore(Integer resultScore) {
        this.resultScore = resultScore;
    }
    
    
}
