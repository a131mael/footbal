package org.aaf.engine.service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.aaf.engine.dto.CountryDTO;
import org.aaf.engine.model.Country;
import org.aaf.engine.model.League;
import org.aaf.engine.model.Match;
import org.aaf.engine.model.Property;
import org.aaf.engine.model.Team;

@Stateless
public class MatchService {

	@PersistenceContext(unitName = "mongo-ogm")
	private EntityManager em;

	@Inject
	private Logger log;

	@Inject
	private LeagueService leagueService;

	@Inject
	private TeamService teamService;

	@SuppressWarnings("unchecked")
	public void createMatches(Country country) {

		StringBuilder sql = new StringBuilder();
		sql.append("db.League.find({'country_id': ");
		sql.append(country.getId());
		sql.append("})");
		Query query = em.createNativeQuery(sql.toString(), League.class);
		List<League> leagues = (List<League>) query.getResultList();

		StringBuilder sqlTeamLeague = new StringBuilder();
		for (League l : leagues) {
			sqlTeamLeague.append("db.Team.find({'league_id': ");
			sqlTeamLeague.append(l.getId());
			sqlTeamLeague.append("})");

			Query queryTeans = em.createNativeQuery(sqlTeamLeague.toString(), Team.class);
			List<Team> teans = queryTeans.getResultList();
			for (int j = 0; j < teans.size(); j++) { // rodadas
				System.out.println("Rodada  : " + j);
				for (int i = 0; i < teans.size() - 1; i++) {// Time
					createMatch(teans.get(i), teans.get(adjustIndex(i + 1 + j, teans.size()-1)));
				}
			}
		}
	}

	private int adjustIndex(int index, int maxIdex) {
		if (index > maxIdex) {
			return index - maxIdex;
		}
		return index;
	}

	private void createMatch(Team team, Team team2) {
		Match match = new Match();
		match.setHomeTeam(team);
		match.setVisitTeam(team2);
		
		System.out.println("Partida : " + team.getCod() + " - " + team2.getCod());
		
//		em.persist(match);

	}

}