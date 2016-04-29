package org.aaf.webInterface.util;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import javax.ejb.LocalBean;
import javax.enterprise.inject.Model;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.naming.NamingException;
import javax.persistence.Entity;

/**
 *
 *
 *
 * @author Edimar Chipil
 * @param
 * @return
 * @exception
 * @throws
 *
 */
@Model
public class SelectItems implements Serializable {


	public ArrayList<SelectItem> getHabilityEnum(){
		return HabilityEnum.getEnuns();
	}
   
}
