package org.aaf.engine.service;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.aaf.engine.model.Country;

@Stateless
public class CountryService {

	@PersistenceContext(unitName = "mongo-ogm")
	private EntityManager em;

	@Inject
	private Logger log;
	
	@Inject
	private LeagueService leagueService;

	public void register(Country country) throws Exception {

		log.info("Registering " + country.getName());
		em.persist(country);
		leagueService.createLeague(country);
	}

	public void save(Country country) throws Exception {
		
		log.info("Registering " + country.getName());
		em.persist(country);
	}

}