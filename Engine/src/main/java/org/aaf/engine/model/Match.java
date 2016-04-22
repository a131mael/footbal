package org.aaf.engine.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Match {

	@Id
	@GeneratedValue(generator = "GENERATE_Match", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "GENERATE_Match", sequenceName = "Match_pk_seq", allocationSize = 1)
	private Long id;

	private String name;

	private String cod;

	@ManyToOne
	private Team homeTeam;
	
	@ManyToOne
	private Team visitTeam;
	
	private int golasHomeTeam;
	
	private int golasVisitTeam;

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

	public Team getHomeTeam() {
		return homeTeam;
	}

	public void setHomeTeam(Team homeTeam) {
		this.homeTeam = homeTeam;
	}

	public Team getVisitTeam() {
		return visitTeam;
	}

	public void setVisitTeam(Team visitTeam) {
		this.visitTeam = visitTeam;
	}

	public int getGolasHomeTeam() {
		return golasHomeTeam;
	}

	public void setGolasHomeTeam(int golasHomeTeam) {
		this.golasHomeTeam = golasHomeTeam;
	}

	public int getGolasVisitTeam() {
		return golasVisitTeam;
	}

	public void setGolasVisitTeam(int golasVisitTeam) {
		this.golasVisitTeam = golasVisitTeam;
	}

	public String getCod() {
		return cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}

}