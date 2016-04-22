/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.aaf.webInterface.util;

import java.util.Properties;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.aaf.webInterface.service.UserService;



/**
 *
 * @author Abimael Fidencio
 */
public class ServiceLocator {

    private InitialContext jndiContext;
    private static ServiceLocator instance;

    private ServiceLocator() {
        try {
            
            Properties props = new Properties();


            jndiContext = new InitialContext(props);
            
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    public static ServiceLocator getInstance() {
        if (instance == null || instance.jndiContext == null) {
            instance = new ServiceLocator();
        }
        return instance;
    }

    public UserService getEjb(String simpleNameImpl, String nameInterface) throws NamingException {
    		Constant c = new Constant();
    	
    		Object ejbHome2 = (Object) jndiContext.lookup(c.getContextoGlobalEJB() + c.getBarra() 
                                + c.getProjeto() + c.getBarra() 
                                + simpleNameImpl +"!"  + nameInterface);

            return (UserService) ejbHome2;
        }
}
