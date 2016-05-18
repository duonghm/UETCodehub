/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.edu.vnu.uet.fit.beans;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import vn.edu.vnu.uet.fit.entity.Problems;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import vn.edu.vnu.uet.fit.entity.Users;
import vn.edu.vnu.uet.fit.model.GenericModel;
import vn.edu.vnu.uet.fit.service.TestcaseService;
import vn.edu.vnu.uet.fit.service.TestcaseService_Service;
import vn.edu.vnu.uet.fit.supportbeans.CourseProblemsBean;
import vn.edu.vnu.uet.fit.utils.JSFUtil;

/**
 *
 * @author hmduong
 */
@Named(value = "problemsBean")
@SessionScoped
public class ProblemsBean extends GenericBean<Problems> implements Serializable {

    public ProblemsBean() {
        init(Problems.class);
    }

    @Override
    public String getEntityMsg(Problems obj) {
        return obj.getProblemId().toString();
    }

    @Override
    public List<Problems> initLstData() {
        return getModel().getAll();
    }

    @Override
    public void create() {
        Users createdUser = JSFUtil.getLoginBean().getSearchUser();
        obj.setUser(createdUser);
        super.create();
    }

    public void deactive(Problems problem) {
        try {
            problem.setIsActive(false);
            getModel().update(problem);
            JSFUtil.addSuccessMessage(null, "Deactive success", "Deactive problem: " + getEntityMsg(problem));
        } catch (Exception ex) {
            Logger.getLogger(UsersBean.class.getName()).log(Level.SEVERE, null, ex);
            JSFUtil.addErrorMessage(null, "Deactive fail", ex.getMessage());
        }
    }

    public void active(Problems problem) {
        try {
            problem.setIsActive(true);
            getModel().update(problem);
            JSFUtil.addSuccessMessage(null, "Active success", "Active account: " + getEntityMsg(problem));
        } catch (Exception ex) {
            Logger.getLogger(UsersBean.class.getName()).log(Level.SEVERE, null, ex);
            JSFUtil.addErrorMessage(null, "Active fail", ex.getMessage());
        }
    }

    public void uploadTestcase(FileUploadEvent evt) {
        InputStream inputStream = null;
        try {
            UploadedFile uploadFile = evt.getFile();
            //JSFUtil.addSuccessMessage(null, "Upload file success", file.getFileName() + " - " + getSelected().getProblem().getProblemId());
            String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Testcase/");
            String problemFolderName = path + "/" + getSelected().getProblemId();
            File problemFolder = new File(problemFolderName);
            if (!problemFolder.exists()) {
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
            }
            inputStream.close();
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

    public void uploadTestcase2(FileUploadEvent evt) {
        try {            
            UploadedFile uploadFile = evt.getFile();
            InputStream inputStream = uploadFile.getInputstream();
            
            int read = 0;
            byte[] bytes = new byte[1024];
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            while ((read = inputStream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            inputStream.close();
            out.flush();
            byte[] data = out.toByteArray();
            out.close();

            TestcaseService service = new TestcaseService_Service().getTestcaseServicePort();
            int uploadResult = service.uploadTestcase(getSelected().getProblemId().toString(), data, uploadFile.getFileName());
            if (uploadResult == 1) {
                JSFUtil.addSuccessMessage(null, "Upload success", "");
            }else{
                JSFUtil.addErrorMessage(null, "Upload fail", "");
            }
        } catch (IOException ex) {
            Logger.getLogger(ProblemsBean.class.getName()).log(Level.SEVERE, null, ex);
            JSFUtil.addErrorMessage(null, "Upload fail", ex.getMessage());
        }

    }

}
