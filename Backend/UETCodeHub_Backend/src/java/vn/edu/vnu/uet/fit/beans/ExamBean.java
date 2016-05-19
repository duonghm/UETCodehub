/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.edu.vnu.uet.fit.beans;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import org.hibernate.Session;
import vn.edu.vnu.uet.fit.entity.Courses;
import vn.edu.vnu.uet.fit.entity.Exams;
import vn.edu.vnu.uet.fit.model.GenericModel;
import vn.edu.vnu.uet.fit.utils.HibernateUtil;
import vn.edu.vnu.uet.fit.utils.JSFUtil;

/**
 *
 * @author hmduong
 */
@Named(value = "examsBean")
@SessionScoped
public class ExamBean extends GenericBean<Exams> implements Serializable {

    /**
     * Creates a new instance of ExamBean
     */
    public ExamBean() {
        init(Exams.class);
    }

    @PostConstruct
    void init(){
        obj.setCourse(new Courses());
    }
    
    @Override
    public String getEntityMsg(Exams obj) {
        return obj.getExamName();
    }

    @Override
    public List<Exams> initLstData() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        return session.createCriteria(Exams.class).list();        
    }

    @Override
    public void create() {
        System.out.println("=== BEFORE CREATE: " + getLst().size());
        super.create();
        System.out.println("=== AFTER CREATE: " + getLst().size());
    }

    public void refresh() {
        lst = initLstData();
        JSFUtil.addSuccessMessage(null, "Refresh", "Current size: " + lst.size());
        System.out.println("=== Lst size: " + lst.size());
    }

    public void active(Exams exam){
        try {
            exam.setIsActive(true);
            getModel().update(exam);
            JSFUtil.addSuccessMessage(null, "Active success", "Active exam: " + getEntityMsg(exam));
        } catch (Exception ex) {
            Logger.getLogger(ExamBean.class.getName()).log(Level.SEVERE, null, ex);
            JSFUtil.addErrorMessage(null, "Active fail", ex.getMessage());
        }
    }
    
    public void deactive(Exams exam){
        try {
            exam.setIsActive(false);
            getModel().update(exam);
            JSFUtil.addSuccessMessage(null, "Deactive success", "Deactive exam: " + getEntityMsg(exam));
        } catch (Exception ex) {
            Logger.getLogger(ExamBean.class.getName()).log(Level.SEVERE, null, ex);
            JSFUtil.addErrorMessage(null, "Deactive fail", ex.getMessage());
        }
        
    }
    
}
