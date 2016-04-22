/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.aaf.webInterface.util;

/**
 *Constantes utilizadas no modulo EJB
 * 
 * @author Abimael Fidencio
 */
public class Constant {
    
    private String ContextoGlobalEJB = "java:global";
    private String Projeto = "webInterface";
    private String ProjetoEJB = "driver";
    private String barra = "/";
	
    public String getContextoGlobalEJB() {
		return ContextoGlobalEJB;
	}
	public void setContextoGlobalEJB(String contextoGlobalEJB) {
		ContextoGlobalEJB = contextoGlobalEJB;
	}
	public String getProjeto() {
		return Projeto;
	}
	public void setProjeto(String projeto) {
		Projeto = projeto;
	}
	public String getProjetoEJB() {
		return ProjetoEJB;
	}
	public void setProjetoEJB(String projetoEJB) {
		ProjetoEJB = projetoEJB;
	}
	public String getBarra() {
		return barra;
	}
	public void setBarra(String barra) {
		this.barra = barra;
	}
    
}
