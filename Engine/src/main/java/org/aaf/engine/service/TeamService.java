package org.aaf.engine.service;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.aaf.engine.model.Country;
import org.aaf.engine.model.League;
import org.aaf.engine.model.Team;

@Stateless
public class TeamService {

	@PersistenceContext(unitName = "mongo-ogm")
	private EntityManager em;

	@Inject
	private Logger log;

	@Inject
	private PlayerService playerService;

	public void register(League league) throws Exception {
		em.persist(league);

		for (int i = 1; i <= 8; i++) {
			playerService.register(createTeam(i, league));
		}
		log.info("Registering " + league.getName());
	}

	public void save(League league) throws Exception {

		log.info("Registering " + league.getName());
		em.persist(league);
	}

	private Team createTeam(int index, League league) {
		Team team = new Team();
		team.setCod(index + "");
		team.setName("Time " + index);
		team.setLeague(league);
		return team;
	}

	public long countActiveTeams(Country country) {
		Query query = em.createQuery("SELECT COUNT(c) FROM Team t JOIN t.league l JOIN l.country c where c.id = :idCountry");
		query.setParameter("idCountry", country.getId());
		
		long countryCount = (long) query.getSingleResult();
		return countryCount;
	}

	public long countCapacity(Country country) {
		Query query = em.createQuery("SELECT COUNT(c) FROM Team t JOIN t.league l JOIN l.country c where c.id = :idCountry");
		query.setParameter("idCountry", country.getId());
		
		long countryCount = (long) query.getSingleResult();
		return countryCount;
	}
}