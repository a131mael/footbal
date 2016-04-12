package org.aaf.engine.service;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.aaf.engine.model.Country;
import org.aaf.engine.model.Player;
import org.aaf.engine.model.Team;

@Stateless
public class PlayerService {

	@PersistenceContext(unitName = "mongo-ogm")
	private EntityManager em;

	@Inject
	private Logger log;


	public void save(Country country) throws Exception {

		log.info("Registering " + country.getName());
		em.persist(country);
	}
	
	public void register(Team team) throws Exception {
		em.persist(team);
		
		for(int i=1; i<=22; i++){
			em.persist(createPlayer(i, team));
		}

		log.info("Registering " + team.getName());
		
	}
	
	private Player createPlayer(int index, Team team){
		Player player = new Player();
		player.setCod(index+"");
		player.setName("Jogador " + index);
		player.setTeam(team);
		return player;
	}

}