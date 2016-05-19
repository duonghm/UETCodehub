package vn.edu.vnu.uet.fit.entity;
// Generated May 11, 2016 1:11:24 PM by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * Exams generated by hbm2java
 */
public class Exams  implements java.io.Serializable {


     private Integer examId;
     private String examName;
     private Courses course;
     private Date availableFrom;
     private Date availableTo;
     private Integer duration;
     private boolean isActive;
     private boolean isFinish;

    public Exams() {
    }

	
    public Exams(String examName, boolean isActive, boolean isFinish) {
        this.examName = examName;
        this.isActive = isActive;
        this.isFinish = isFinish;
    }
    public Exams(String examName, Courses course, Date availableFrom, Date availableTo, Integer duration, boolean isActive, boolean isFinish) {
       this.examName = examName;
       this.course = course;
       this.availableFrom = availableFrom;
       this.availableTo = availableTo;
       this.duration = duration;
       this.isActive = isActive;
       this.isFinish = isFinish;
    }
   
    public Integer getExamId() {
        return this.examId;
    }
    
    public void setExamId(Integer examId) {
        this.examId = examId;
    }
    public String getExamName() {
        return this.examName;
    }
    
    public void setExamName(String examName) {
        this.examName = examName;
    }

    public Courses getCourse() {
        return course;
    }

    public void setCourse(Courses course) {
        this.course = course;
    }
    
    public Date getAvailableFrom() {
        return this.availableFrom;
    }
    
    public void setAvailableFrom(Date availableFrom) {
        this.availableFrom = availableFrom;
    }
    public Date getAvailableTo() {
        return this.availableTo;
    }
    
    public void setAvailableTo(Date availableTo) {
        this.availableTo = availableTo;
    }
    public Integer getDuration() {
        return this.duration;
    }
    
    public void setDuration(Integer duration) {
        this.duration = duration;
    }
    public boolean isIsActive() {
        return this.isActive;
    }
    
    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }
    public boolean isIsFinish() {
        return this.isFinish;
    }
    
    public void setIsFinish(boolean isFinish) {
        this.isFinish = isFinish;
    }

}


