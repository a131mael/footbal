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

	@Inject
	private EntityManager em;

	@Inject
	private Logger log;

	@Inject
	private LeagueService leagueService;

	@Inject
	private TeamService teamService;

	//TODO query nativa para mongoDB
	@SuppressWarnings("unchecked")
	public void createMatchesMongo(Country country) {

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
			
			for(int j=0; j<(teans.size()-1)*2;j++){
				System.out.println("Rodada " + j);
				for(int i=0;i<teans.size()/2;i++){
			
					createMatch(teans.get(i), teans.get(index.get(i+index2Group)), j);
					System.out.println(teans.get(i).getName() +" x " + teans.get(index.get(i+index2Group)).getName());
					
				}
				escalonar(index);
			}
		
			sqlTeamLeague = new StringBuilder();

		}
	}

	@SuppressWarnings("unchecked")
	public void createMatches(Country country) {

		StringBuilder sql = new StringBuilder();
		sql.append("Select l From League l left join l.country c  where c.id = :countryID ");
		
		Query query = em.createQuery(sql.toString());
		query.setParameter("countryID", country.getId());
		List<League> leagues = (List<League>) query.getResultList();

		List<Integer> index = new ArrayList<>();
		for(int i =0; i<8;i++){ //8 teams per League, if more, chance the number
			index.add(i);
		}
		int index2Group = index.size()/2;

		StringBuilder sqlTeamLeague = new StringBuilder();
		for (League l : leagues) {
			sqlTeamLeague.append("SELECT t From Team t left join t.league l where l.id = :idLeague ");
			
			Query queryTeans = em.createQuery(sqlTeamLeague.toString());
			queryTeans.setParameter("idLeague", l.getId());
			List<Team> teans = queryTeans.getResultList();

			for(int j=0; j<(teans.size()-1)*2;j++){
				System.out.println("Rodada " + j);
				for(int i=0;i<teans.size()/2;i++){
			
					createMatch(teans.get(i), teans.get(index.get(i+index2Group)), j);
					System.out.println(teans.get(i).getName() +" x " + teans.get(index.get(i+index2Group)).getName());
					
				}
				escalonar(index);
			}
		
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
		match.setRound(round);
		match.setWeek(String.valueOf(round));
		match.setSession("0");
		if(round%2==0){
			match.setHomeTeam(team);
			match.setVisitTeam(team2);
		}else{
			match.setHomeTeam(team2);
			match.setVisitTeam(team);
		}
		em.persist(match);

	}

}