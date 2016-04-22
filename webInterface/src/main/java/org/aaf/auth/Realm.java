package org.aaf.auth;


import javax.inject.Inject;
import javax.naming.NamingException;

import org.aaf.webInterface.model.User;
import org.aaf.webInterface.service.UserService;
import org.aaf.webInterface.util.ServiceLocator;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;


/**
 *
 * @author abimael
 */
public class Realm extends AuthorizingRealm {
	
	@Inject
	private UserService userService;


	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {

        User usuario = (User) SecurityUtils.getSubject().getPrincipal();

        SimpleAuthorizationInfo info = null;
        
        if (usuario != null) {
        	info = new SimpleAuthorizationInfo();
        }

		return info;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken arg0) throws AuthenticationException {
		try {
			StringBuilder sb = new StringBuilder();
			
			for(char c : (char [])arg0.getCredentials()){
				sb.append(c);	
			}
			 
			System.out.println("Autorizacao");
			User m = new User();
			m.setLogin((String)arg0.getPrincipal());
			m.setSenha(sb.toString());
			userService = getEjb(UserService.class, UserService.class);
			User member = userService.login(m);
			if(member != null){
				return new SimpleAuthenticationInfo(member, m.getSenha(), getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return null;
	}

	@SuppressWarnings("rawtypes")
	private UserService getEjb(Class impl, Class local) {
		try {
			return ServiceLocator.getInstance().getEjb(impl.getSimpleName(), local.getName());
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return userService;
	}

}