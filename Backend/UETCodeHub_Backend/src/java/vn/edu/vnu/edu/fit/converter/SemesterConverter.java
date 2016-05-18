/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.edu.vnu.edu.fit.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import vn.edu.vnu.uet.fit.entity.Semesters;
import vn.edu.vnu.uet.fit.model.GenericModel;

/**
 *
 * @author hmduong
 */
@FacesConverter("semesterConverter")
public class SemesterConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if(value != null && value.trim().length() > 0) {
            GenericModel model = new GenericModel(Semesters.class);
            return model.search("semesterId = " + value).get(0);
        }else{
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object object) {
        if(object != null) {
            return String.valueOf(((Semesters) object).getSemesterId());
        }
        else {
            return null;
        }
    }
    
}
