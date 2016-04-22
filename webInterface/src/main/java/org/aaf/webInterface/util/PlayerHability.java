/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.aaf.webInterface.util;

import javax.enterprise.inject.Model;

/**
 *Conversor de habilidades
 * 
 * @author Abimael Fidencio
 */
@Model
public class PlayerHability {
    
   
	public String getHability(float valor){
		String hab = "";
		if(valor > 20){
			hab = "Lendário";
		}else if(valor > 19){
			hab = "Oxtenta";
		}else if(valor > 18){
			hab = "Top das galaxias";
		}else if(valor > 17){
			hab = "Super Lendário";
		}else if(valor > 16){
			hab = "Lendário";
		}else if(valor > 15){
			hab = "Sobrenatural";
		}else if(valor > 14){
			hab = "Brilhante";
		}else if(valor > 13){
			hab = "Incrivel";
		}else if(valor > 12){
			hab = "Fenomanel";
		}else if(valor > 11){
			hab = "Formidavél";
		}else if(valor > 10){
			hab = "Excelente";
		}else if(valor > 9){
			hab = "Muito bom";
		}else if(valor > 8){
			hab = "Sólido";
		}else if(valor > 7){
			hab = "Bom";
		}else if(valor > 6){
			hab = "Mediano";
		}else if(valor > 5){
			hab = "Inadequado";
		}else if(valor > 4){
			hab = "Fraco";
		}else if(valor > 3){
			hab = "Ruim";
		}else if(valor > 2){
			hab = "Péssimo";
		}else if(valor > 1){
			hab = "Terrivel";
		}
		
		return hab;
		
		
	}
	
    public int getAge(float valor){
    	return (int) valor; 
    }
    
    public float getHeigth(float valor){
    	return  valor/100; 
    }
}
