package org.aaf.engine.cron;

import java.util.logging.Logger;

import javax.inject.Inject;
import javax.validation.Validator;

import org.aaf.engine.data.MemberRepository;
import org.aaf.engine.model.Country;
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
			
			Country country = new Country();
			country.setCod("5");
			country.setName("teste");
			service.register(country);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Call to WebService");

	}

}
