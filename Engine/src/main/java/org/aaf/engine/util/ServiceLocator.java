package org.aaf.engine.util;

import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;

import org.aaf.engine.service.CountryService;

public class ServiceLocator {

	private InitialContext jndiContext;
	private static ServiceLocator instance;

	private ServiceLocator() {
		try {
			jndiContext = new InitialContext();
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}

	public static ServiceLocator getInstance() {
		if (instance == null) {
			instance = new ServiceLocator();
		}
		return instance;
	}

	public CountryService getEJB(String jndiName) {
		Object ref = null;
		try {
			ref = jndiContext.lookup(jndiName);
			CountryService home = (CountryService) PortableRemoteObject.narrow(ref, CountryService.class);
		} catch (Exception exc) {
			exc.printStackTrace();
		}
		return (CountryService) ref;
	}

}
