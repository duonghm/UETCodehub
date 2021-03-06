package vn.edu.vnu.uet.fit.entity;
// Generated May 11, 2016 1:11:24 PM by Hibernate Tools 4.3.1

import java.util.HashSet;
import java.util.Set;




/**
 * Courses generated by hbm2java
 */
public class Courses  implements java.io.Serializable {


     private Integer courseId;
     private String courseName;
     private Users createdUser;
     private Semesters semester;
     private String description;
     private boolean isActive;
     private Set<Users> members = new HashSet<Users>(0);
     private Set<Problems> problems = new HashSet<Problems>(0);

    public Courses() {        
    }

	
    public Courses(String courseName, boolean isActive) {
        this.courseName = courseName;
        this.isActive = isActive;
    }
    public Courses(String courseName, Users createdUser, Semesters semester, String description, boolean isActive) {
       this.courseName = courseName;
       this.createdUser = createdUser;
       this.semester = semester;
       this.description = description;
       this.isActive = isActive;
    }
   
    public Integer getCourseId() {
        return this.courseId;
    }
    
    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }
    public String getCourseName() {
        return this.courseName;
    }
    
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Users getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(Users createdUser) {
        this.createdUser = createdUser;
    }
    
    public Semesters getSemester() {
        return this.semester;
    }
    
    public void setSemester(Semesters semester) {
        this.semester = semester;
    }
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    public boolean isIsActive() {
        return this.isActive;
    }
    
    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public Set<Users> getMembers() {
        return members;
    }

    public void setMembers(Set<Users> members) {
        this.members = members;
    }

    public Set<Problems> getProblems() {
        return problems;
    }

    public void setProblems(Set<Problems> problems) {
        this.problems = problems;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Courses){
            return ((Courses)obj).getCourseId() == getCourseId();
        }else{
            return false;
        }
    }

}


