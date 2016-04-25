package org.aaf.webInterface.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Team implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3369305314854817888L;

	@Id
	@GeneratedValue(generator = "GENERATE_Team", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "GENERATE_Team", sequenceName = "Team_pk_seq", allocationSize = 1)
	private Long id;

	private String name;
	
	private String cod;

	@ManyToOne
	private League league;
	
	@OneToOne
	private User owner;
	
	private Double cashBox;

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

	public League getLeague() {
		return league;
	}

	public void setLeague(League league) {
		this.league = league;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public Double getCashBox() {
		return cashBox;
	}

	public void setCashBox(Double cashBox) {
		this.cashBox = cashBox;
	}

}