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
		
		List<Integer> index = new ArrayList<>();
		for(int i =0; i<8;i++){ //8 teams per League, if more, chance the number
			index.add(i);
		}
		int index2Group = index.size()/2;
		
		for (League l : leagues) {
			sqlTeamLeague.append("db.Team.find({'league_id': ");
			sqlTeamLeague.append(l.getId());
			sqlTeamLeague.append("})");

			Query queryTeans = em.createNativeQuery(sqlTeamLeague.toString(), Team.class);
			List<Team> teans = queryTeans.getResultList();
			
			for(int j=0; j< teans.size();j++){
				System.out.println("Rodada " + j);
				for(int i=0;i<teans.size()/2;i++){
			
					createMatch(teans.get(i), teans.get(adjustIndex(i + 1 + j, teans.size()-1)), j);
					System.out.println(teans.get(index.get(i)).getName() +" x " + teans.get(index.get(i+index2Group)).getName());
					
				}
				escalonar(index);
			}
//			teans = invertArray(teans);
//			for(int j=0; j<teans.size();j++){
//				System.out.println("Rodada " + j);
//				for(int i=0;i<teans.size()/2;i++){
//			
//					createMatch(teans.get(i), teans.get(adjustIndex(i + 1 + j, teans.size()-1)), i);
//					System.out.println(teans.get(index.get(i)) +" x " + teans.get(index.get(i+index2Group)));
//					
//				}
//				escalonar(index);
//			}
			
			sqlTeamLeague = new StringBuilder();
		}
	}

	private static void escalonar(List<Integer> index) {
		int finalIndex = index.size()-1;
		List<Integer> indexClone = new ArrayList<>();
		indexClone.addAll(index);
		for(int i=1; i<=finalIndex;i++){
			index.set(i, indexClone.get(adjustIndex(i+1,finalIndex)));
		}
	}

	private static int adjustIndex(int index, int maxIdex) {
		if (index > maxIdex) {
			return 1;
		}
		return index;
	}

	private static List<Team> invertArray(List<Team> array){
		List<Team> clone = new ArrayList<>();
		for(Team element: array){
			clone.add(0, element);
		}
		
		return clone;
	}

	private void createMatch(Team team, Team team2, int round) {
		Match match = new Match();
		match.setHomeTeam(team);
		match.setVisitTeam(team2);
		match.setRound(round);
		match.setSession("0");
		match.setWeek(String.valueOf(round));
		em.persist(match);

	}

}