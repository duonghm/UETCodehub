/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.edu.vnu.uet.fit.beans;

import vn.edu.vnu.uet.fit.entity.Problems;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import vn.edu.vnu.uet.fit.model.GenericModel;


/**
 *
 * @author hmduong
 */
@Named(value = "problemsBean")
@Dependent
public class ProblemsBean {

    private List<Problems> lst;
    private GenericModel model;
    
    public List<Problems> getLst() {
        if(lst==null){
            lst = model.getAll();
        }
        return lst;
    }

    public void setLst(List<Problems> lst) {
        this.lst = lst;
    }
    
    public ProblemsBean() {
        model = new GenericModel<>(Problems.class);
    }
    
}
