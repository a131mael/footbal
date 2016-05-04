package org.aaf.engine.service;

import java.util.Random;
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

	@Inject
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
		Random gerador = new Random();
		Player player = new Player();
		player.setCod(index+"");
		player.setName("Jogador " + index);
		player.setTeam(team);
		player.setCountry(team.getLeague().getCountry());
		
		player.setAge((float) (gerador.nextDouble() * 15)+ 18);
		player.setAggressiveness((float) (gerador.nextDouble() * 8)+ 3);
		player.setAgility((float) (gerador.nextDouble() * 8)+ 4);
		player.setDecision((float) (gerador.nextDouble() * 1)+ 3);
		player.setDisarm((float) (gerador.nextDouble() * 8)+ 3);
		player.setGoalKeaper((float) (gerador.nextDouble() * 6)+ 1);
		player.setHeight((float) (gerador.nextDouble() * 65)+ 150);
		player.setImpulse((float) (gerador.nextDouble() * 8)+ 5);
		player.setKick((float) (gerador.nextDouble() * 8)+ 3);
		player.setMark((float) (gerador.nextDouble() * 8)+ 4);
		player.setPass((float) (gerador.nextDouble() * 8)+ 5);
		player.setPositioning((float) (gerador.nextDouble() * 8)+ 5);
		player.setResistence((float) (gerador.nextDouble() * 8)+ 5);
		player.setStrength((float) (gerador.nextDouble() * 8)+ 5);
		player.setTechnique((float) (gerador.nextDouble() * 8)+ 5);
		player.setVelocity((float) (gerador.nextDouble() * 8)+ 5);
		player.setWorkIndex((float) (gerador.nextDouble() * 8)+ 5);
		
		player.setIgnore("a");
		
		return player;
	}

}