package org.aaf.engine.service;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.aaf.engine.model.Country;
import org.aaf.engine.model.League;
import org.aaf.engine.model.Member;
import org.aaf.engine.model.Property;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class LeagueService {

	@PersistenceContext(unitName = "mongo-ogm")
	private EntityManager em;

	@Inject
	private Logger log;

	public void register(Country country) throws Exception {

		log.info("Registering " + country.getName());
		em.persist(country);
	}

	public void save(Country country) throws Exception {

		log.info("Registering " + country.getName());
		em.persist(country);
	}

	public void createLeague(Country country) {
		League league = new League();
		league.setCod("1");
		league.setName("League 1");
		
		log.info("Registering " + league.getName());
		em.persist(league);
		
		League league2 = new League();
		league.setCod("2");
		league.setName("League 2");
		
		log.info("Registering " + league.getName());
		em.persist(league2);
	}

}