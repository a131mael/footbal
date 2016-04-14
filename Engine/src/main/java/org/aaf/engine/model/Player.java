package org.aaf.engine.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
public class Player {

	@Id
	@GeneratedValue(generator = "GENERATE_Player", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "GENERATE_Player", sequenceName = "Player_pk_seq", allocationSize = 1)
	private Long id;

	private String name;

	private String cod;

	@ManyToOne
	private Team team;
	
	private float agility;
	
	private float velocity;
	
	private float defense;
	
	private float kick;
	
	private float pass;

	public String getCod() {
		return cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public float getAgility() {
		return agility;
	}

	public void setAgility(float agility) {
		this.agility = agility;
	}

	public float getVelocity() {
		return velocity;
	}

	public void setVelocity(float velocity) {
		this.velocity = velocity;
	}

	public float getDefense() {
		return defense;
	}

	public void setDefense(float defense) {
		this.defense = defense;
	}

	public float getKick() {
		return kick;
	}

	public void setKick(float kick) {
		this.kick = kick;
	}

	public float getPass() {
		return pass;
	}

	public void setPass(float pass) {
		this.pass = pass;
	}

}