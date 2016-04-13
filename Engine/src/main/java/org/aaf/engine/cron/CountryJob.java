package org.aaf.engine.cron;

import java.util.logging.Logger;

import javax.inject.Inject;

import org.aaf.engine.dto.CountryDTO;
import org.aaf.engine.service.CountryService;
import org.aaf.engine.util.ServiceLocator;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class CountryJob implements Job {

	@Inject
	private Logger log;

	private CountryService service;

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		try {
			service = ServiceLocator.getInstance().getEJB("java:global/Engine/CountryService!org.aaf.engine.service.CountryService");
			
			CountryDTO country = new CountryDTO();
			country.setCod("1");
			country.setName("Pais 1");
			service.register(country);
		} catch (Exception e) {
			log.info("CRIANDO PAIS");
		}
		System.out.println("Call to WebService");

	}

}
