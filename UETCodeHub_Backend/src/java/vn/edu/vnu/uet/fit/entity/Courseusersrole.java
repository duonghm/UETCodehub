package vn.edu.vnu.uet.fit.entity;
// Generated May 11, 2016 1:11:24 PM by Hibernate Tools 4.3.1

/**
 * Courseusersrole generated by hbm2java
 */
public class Courseusersrole implements java.io.Serializable {

    private Integer courseusersroleId;
    private String courseusersroleName;

    public Courseusersrole() {
    }

    public Courseusersrole(String courseusersroleName) {
        this.courseusersroleName = courseusersroleName;
    }
    
    public Integer getCourseusersroleId() {
        return courseusersroleId;
    }

    public void setCourseusersroleId(Integer courseusersroleId) {
        this.courseusersroleId = courseusersroleId;
    }

    public String getCourseusersroleName() {
        return courseusersroleName;
    }

    public void setCourseusersroleName(String courseusersroleName) {
        this.courseusersroleName = courseusersroleName;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Courseusersrole){
            return ((Courseusersrole)obj).getCourseusersroleId() == getCourseusersroleId();
        }else{
            return false;
        }
    }

    
    
}
