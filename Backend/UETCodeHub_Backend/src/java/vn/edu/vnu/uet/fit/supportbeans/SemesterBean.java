/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.edu.vnu.uet.fit.supportbeans;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import vn.edu.vnu.uet.fit.beans.GenericBean;
import vn.edu.vnu.uet.fit.entity.Semesters;

/**
 *
 * @author hmduong
 */
@Named(value = "semesterBean")
@SessionScoped
public class SemesterBean extends GenericBean<Semesters> implements Serializable {

    /**
     * Creates a new instance of SemesterBean
     */
    public SemesterBean() {
        init(Semesters.class);
    }

    @Override
    public String getEntityMsg(Semesters obj) {
        return obj.getSemesterName();
    }

    @Override
    public List<Semesters> initLstData() {
        return getModel().getAll();
    }
    
}
