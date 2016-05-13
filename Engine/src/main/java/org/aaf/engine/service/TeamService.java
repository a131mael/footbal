package org.aaf.engine.service;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.aaf.engine.model.Country;
import org.aaf.engine.model.League;
import org.aaf.engine.model.Property;
import org.aaf.engine.model.Team;


@Stateless
public class TeamService {

	@Inject
	private EntityManager em;

	@Inject
	private Logger log;

	@Inject
	private PlayerService playerService;

	@Inject
	private MatchService matchService;

	
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
		team.setCashBox(500000D);
		team.setLeague(league);
		return team;
	}

	//TODO MongoDB native query
	@SuppressWarnings("unchecked")
	public long countActiveTeamsMONGODB(Country country) {
		StringBuilder queryLeague = new StringBuilder();
		queryLeague.append("db.League.find({'country_id': ");
		queryLeague.append(country.getId());
		queryLeague.append("})");
		Query query2 = em.createNativeQuery(queryLeague.toString(), League.class);
		List<League> leagues = (List<League>) query2.getResultList();
		
		StringBuilder queryTeam = new StringBuilder();
		long countTeam = 0;
		for (League league : leagues) {
			queryTeam.append("db.Team.find({'league_id': ");
			queryTeam.append(league.getId());
			queryTeam.append(", ");
			queryTeam.append("'owner_id':{'$exists':true}");
			queryTeam.append("})");
			try{
				countTeam += (Long) em.createNativeQuery(queryTeam.toString()).getSingleResult();
				
			}catch(Exception e){
				//TODO ignore
			}
			
			queryTeam = new StringBuilder();
		}
		return countTeam;
	}

	@SuppressWarnings("unchecked")
	public long countActiveTeams(Country country) {
		StringBuilder queryLeague = new StringBuilder();
		queryLeague.append("Select t from Team t ");
		queryLeague.append("left join t.owner o ");
		queryLeague.append("left join t.league l ");
		queryLeague.append("left join l.country c  ");
		queryLeague.append("where 1=1 ");
		queryLeague.append("and o is not null ");
		queryLeague.append("and c.id = :idPais");
		Query query = em.createQuery(queryLeague.toString());
		query.setParameter("idPais", country.getId());
		
		List<Team> times = query.getResultList();
		Long total =  (long) times.size() ;
		
		
		return total;
	}

	@SuppressWarnings("unchecked")
	public long countCapacity(Country country) {

		StringBuilder queryLeague = new StringBuilder();
		queryLeague.append("db.League.find({'country_id': ");
		queryLeague.append(country.getId());
		queryLeague.append("})");
		Query query2 = em.createNativeQuery(queryLeague.toString(), League.class);
		List<League> leagues = (List<League>) query2.getResultList();
		StringBuilder queryTeam = new StringBuilder();
		long countTeam = 0;
		for (League league : leagues) {
			queryTeam.append("db.Team.count({'league_id': ");
			queryTeam.append(league.getId());
			queryTeam.append("})");
			countTeam += (Long) em.createNativeQuery(queryTeam.toString()).getSingleResult();

			queryTeam = new StringBuilder();
		}

		return countTeam;
	}
}