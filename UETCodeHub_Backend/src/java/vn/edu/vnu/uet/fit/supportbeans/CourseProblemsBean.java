/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.edu.vnu.uet.fit.supportbeans;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import org.primefaces.component.fileupload.FileUpload;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import vn.edu.vnu.uet.fit.beans.CoursesBean;
import vn.edu.vnu.uet.fit.beans.GenericBean;
import vn.edu.vnu.uet.fit.beans.LoginBean;
import vn.edu.vnu.uet.fit.entity.Courseproblems;
import vn.edu.vnu.uet.fit.entity.Courses;
import vn.edu.vnu.uet.fit.entity.Problems;
import vn.edu.vnu.uet.fit.model.GenericModel;
import vn.edu.vnu.uet.fit.utils.JSFUtil;

/**
 *
 * @author hmduong
 */
@Named(value = "courseProblemsBean")
@SessionScoped
public class CourseProblemsBean extends GenericBean<Courseproblems> implements Serializable {

    GenericModel<Problems> problemModel;
    CoursesBean coursesBean;
    FileUpload testcaseFile;

    public CourseProblemsBean() {
        setEntityClass(Courseproblems.class);
    }

    public CoursesBean getCoursesBean() {
        FacesContext context = FacesContext.getCurrentInstance();
        CoursesBean bean = context.getApplication().evaluateExpressionGet(context, "#{coursesBean}", CoursesBean.class);
        return bean;
    }
    
    public LoginBean getLoginBean(){
        FacesContext context = FacesContext.getCurrentInstance();
        LoginBean bean = context.getApplication().evaluateExpressionGet(context, "#{loginBean}", LoginBean.class);
        return bean;
    }

    public void setCoursesBean(CoursesBean coursesBean) {
        this.coursesBean = coursesBean;
    }

    public GenericModel<Problems> getProblemModel() {
        if (problemModel == null) {
            problemModel = new GenericModel(Problems.class);
        }
        return problemModel;
    }

    public void setProblemModel(GenericModel<Problems> problemModel) {
        this.problemModel = problemModel;
    }

    public FileUpload getTestcaseFile() {
        return testcaseFile;
    }

    public void setTestcaseFile(FileUpload testcaseFile) {
        this.testcaseFile = testcaseFile;
    }

    @Override
    public String getEntityMsg(Courseproblems obj) {
        return obj.getCourseProblemId().toString();
    }

    @Override
    public List<Courseproblems> initLstData() {
        CoursesBean bean = getCoursesBean();
        if (bean.getSelected() != null) {
            return getModel().search("courseId = " + bean.getSelected().getCourseId());
        } else {
            JSFUtil.addSuccessMessage(null, "No course select", "No course select");
            return new ArrayList<>();
        }
    }

    @Override
    public void create() {
        try {
            Courseproblems obj = getObj();
            obj.setCourse(getCoursesBean().getSelected());
            obj.getProblem().setUser(getLoginBean().getUser());
            System.out.println("=== Problem Created user: " + obj.getProblem().getUser().getUsername());
            getProblemModel().create(obj.getProblem());
            super.create();
        } catch (Exception ex) {
            JSFUtil.addErrorMessage("null", "Error", ex.getMessage());
        }

    }

    @PostConstruct
    void init() {
        Courseproblems c = new Courseproblems();
        c.setCourse(new Courses());
        c.setProblem(new Problems());
        setObj(c);
    }

    public void uploadTestcase(FileUploadEvent evt) {
        InputStream inputStream = null;        
        try {
            UploadedFile uploadFile = evt.getFile();
            //JSFUtil.addSuccessMessage(null, "Upload file success", file.getFileName() + " - " + getSelected().getProblem().getProblemId());
            String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Testcase/");
            String problemFolderName = path + "/" + getSelected().getProblem().getProblemId();
            File problemFolder = new File(problemFolderName);
            if(!problemFolder.exists()){
                problemFolder.mkdir();
            }
            String targetFilename = problemFolderName + "/" + uploadFile.getFileName();
            File targetFile = new File(targetFilename);            
            inputStream = uploadFile.getInputstream();
            OutputStream out = new FileOutputStream(targetFile);
            int read = 0;
            byte[] bytes = new byte[1024];
            while ((read = inputStream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }   inputStream.close();
            out.flush();
            out.close();
            JSFUtil.addSuccessMessage(null, "Upload success", targetFile.getPath());
            
        } catch (IOException ex) {
            Logger.getLogger(CourseProblemsBean.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                inputStream.close();
            } catch (IOException ex) {
                Logger.getLogger(CourseProblemsBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
