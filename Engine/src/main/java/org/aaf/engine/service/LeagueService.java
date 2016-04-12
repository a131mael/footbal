package org.aaf.engine.service;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.aaf.engine.model.Country;
import org.aaf.engine.model.League;

@Stateless
public class LeagueService {

	@PersistenceContext(unitName = "mongo-ogm")
	private EntityManager em;

	@Inject
	private Logger log;

	@Inject
	private TeamService teamService;

	public void register(Country country) throws Exception {

		log.info("Registering " + country.getName());
		em.persist(country);
	}

	public void save(Country country) throws Exception {

		log.info("Registering " + country.getName());
		em.persist(country);
	}

	public void createLeague(Country country) {
		try {
			em.persist(country);
			
			for(int i = 1 ; i<=5; i++){
				teamService.register(createLeague(i, country));	;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private League createLeague(int index, Country country) {

		League league = new League();
		league.setCod(index+"");
		league.setCountry(country);
		league.setName("League " + index);
		if(index == 1){
			league.setLevel(1);	
		}else{
			league.setLevel(2);
		}
		
		return league;
	}

}