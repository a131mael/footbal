package org.aaf.webInterface.util;

import java.util.ArrayList;

import javax.faces.model.SelectItem;

public enum HabilityEnum {

	AGILITY("Agilidade"),
	
	VELOCITY("Velocidade");
	
	private String label;
    
	
	HabilityEnum(String name){
		this.label = name;
	}
	
	//TODO internacionalizar
	public static ArrayList<SelectItem> getEnuns() {

        ArrayList<SelectItem> items = new ArrayList<SelectItem>();
        for (HabilityEnum g : HabilityEnum.values()) {
            items.add(new SelectItem(g, g.getLabel()));
        }

        return items;
    }

    public String getLabel() {
        return label;
    }
}
