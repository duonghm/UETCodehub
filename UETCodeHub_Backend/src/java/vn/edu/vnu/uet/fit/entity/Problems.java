package vn.edu.vnu.uet.fit.entity;
// Generated May 11, 2016 1:11:24 PM by Hibernate Tools 4.3.1



/**
 * Problems generated by hbm2java
 */
public class Problems  implements java.io.Serializable {


     private Integer problemId;
     private Users user;
     private String content;
     private Float timelimit;
     private Integer defaultScore;
     private String tagValues;
     private boolean isActive;

    public Problems() {
    }

	
    public Problems(boolean isActive) {
        this.isActive = isActive;
    }
    public Problems(Users user, String content, Float timelimit, Integer defaultScore, String tagValues, boolean isActive) {
       this.user = user;
       this.content = content;
       this.timelimit = timelimit;
       this.defaultScore = defaultScore;
       this.tagValues = tagValues;
       this.isActive = isActive;
    }
   
    public Integer getProblemId() {
        return this.problemId;
    }
    
    public void setProblemId(Integer problemId) {
        this.problemId = problemId;
    }
    public Users getUser() {
        return this.user;
    }
    
    public void setUser(Users user) {
        this.user = user;
    }
    public String getContent() {
        return this.content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    public Float getTimelimit() {
        return this.timelimit;
    }
    
    public void setTimelimit(Float timelimit) {
        this.timelimit = timelimit;
    }
    public Integer getDefaultScore() {
        return this.defaultScore;
    }
    
    public void setDefaultScore(Integer defaultScore) {
        this.defaultScore = defaultScore;
    }
    public String getTagValues() {
        return this.tagValues;
    }
    
    public void setTagValues(String tagValues) {
        this.tagValues = tagValues;
    }
    public boolean isIsActive() {
        return this.isActive;
    }
    
    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }




}

