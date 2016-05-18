/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.edu.vnu.uet.fit.beans;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.JDBCException;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;
import vn.edu.vnu.uet.fit.model.GenericModel;
import vn.edu.vnu.uet.fit.utils.JSFUtil;

/**
 *
 * @author hmduong
 */
public abstract class GenericBean<T> {
    
    Class entityClass;
    
    List<T> lst;
    GenericModel model;
    T obj;
    List<T> selectedObjs;
    T selected;

    public void init(Class entityClass){
        try {
            this.entityClass = entityClass;
            obj = (T)entityClass.newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(GenericBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<T> getLst(){
        if(lst==null){
            lst = initLstData();
        }
        return lst;
    }

    public void setLst(List<T> lst) {
        this.lst = lst;
    }

    public GenericModel getModel() {
        if(model==null){
            model = new GenericModel(entityClass);
        }
        return model;
    }

    public void setModel(GenericModel model) {
        this.model = model;
    }

    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }

    public List<T> getSelectedObjs() {
        return selectedObjs;
    }

    public void setSelectedObjs(List<T> selectedObjs) {
        this.selectedObjs = selectedObjs;
    }

    public T getSelected() {
        return selected;
    }

    public void setSelected(T selected) {
        this.selected = selected;
    }

    public Class getEntityClass() {
        return entityClass;
    }

    public void setEntityClass(Class entityClass) {
        this.entityClass = entityClass;
    }
    
    //<editor-fold defaultstate="collapsed" desc="CRUD template">
    
    public void create(){
        try{            
            getModel().create(obj);
            JSFUtil.addSuccessMessage(null, "Insert success", "Insert success: " + getEntityMsg(obj));
            RequestContext.getCurrentInstance().execute("PF('wgCreate').hide()");
            lst = null;
        }catch(JDBCException ex){
            System.out.println("=== ERROR CODE: " + ex.getErrorCode());
            JSFUtil.addErrorMessage(null, "SQL Error", "Insert error code: " + ex.getErrorCode());
        }catch(Exception ex){
            ex.printStackTrace();
            System.out.println("=== ERROR: " + ex.getMessage());
            JSFUtil.addErrorMessage(null, "Error", ex.getMessage());
        }
    }
    
    public void onRowEdit(RowEditEvent evt){
        try {
            T selectedObj = (T) evt.getObject();
            getModel().update(selectedObj);
            JSFUtil.addSuccessMessage(null, "Edit success", getEntityMsg(selectedObj));
        } catch (Exception ex) {
            JSFUtil.addSuccessMessage(null, "Edit fail", ex.getMessage());
            Logger.getLogger(GenericBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void onRowCancel(RowEditEvent evt){
        T selectedObj = (T) evt.getObject();
        JSFUtil.addSuccessMessage(null, "Edit Cancel", getEntityMsg(selectedObj));
    }
    
    public void delete(T obj){
        System.out.println("=== Call delete");
    }
    
//</editor-fold>
    
    public abstract String getEntityMsg(T obj);
    
    public abstract List<T> initLstData();
}
