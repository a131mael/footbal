package org.aaf.webInterface.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
public class League implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8514629038725047970L;

	@Id
	@GeneratedValue(generator = "GENERATE_League", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "GENERATE_League", sequenceName = "League_pk_seq", allocationSize = 1)
	private Long id;

	private String name;

	private String cod;
	
	private int level;
	
	@OneToMany(fetch=FetchType.EAGER)
	private List<Team> teans;

	@ManyToOne
	private Country country;

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

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public List<Team> getTeans() {
		return teans;
	}

	public void setTeans(List<Team> teans) {
		this.teans = teans;
	}

}