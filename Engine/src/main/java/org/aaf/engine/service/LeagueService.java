package org.aaf.engine.service;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.aaf.engine.dto.CountryDTO;
import org.aaf.engine.model.Country;
import org.aaf.engine.model.League;

@Stateless
public class LeagueService {

	@Inject
	private EntityManager em;

	@Inject
	private Logger log;

	@Inject
	private TeamService teamService;
	
	@Inject
	private MatchService matchService;

	public void register(Country country) throws Exception {

		log.info("Registering " + country.getName());
		em.persist(country);
	}

	public void save(Country country) throws Exception {

		log.info("Registering " + country.getName());
		em.persist(country);
	}

	public void createLeague(CountryDTO country) {
		try {
			em.persist(country.getCountry());
			for(int i = 1 ; i<=37; i++){
				teamService.register(createLeague(i, country));	;
			}
			matchService.createMatches(country.getCountry());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}	

	private League createLeague(int index, CountryDTO country) {

		League league = new League();
		league.setCod(index+"");
		league.setCountry(country.getCountry());
		if(index == 1){
			league.setLevel(1);	
			league.setName(country.getFirtLeague());
		}else if(index > 1 && index < 6){
			league.setLevel(2);
			league.setName("League B " + (index -1));
		}else{
			league.setLevel(3);
			league.setName("League C " + (index -5));
		}
		
		return league;
	}

}