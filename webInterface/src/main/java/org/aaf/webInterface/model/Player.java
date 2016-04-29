package org.aaf.webInterface.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Player implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8458130884793236986L;

	@Id
	@GeneratedValue(generator = "GENERATE_Player", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "GENERATE_Player", sequenceName = "Player_pk_seq", allocationSize = 1)
	private Long id;

	private String name;
	private float height;
	private float age;
	private Float salary;
	
	@ManyToOne
	private Country country;

	private String cod;

	@ManyToOne
	private Team team;
	
//	Fisicas
	private float agility;
	private float velocity;
	private float resistence;
	private float impulse;
	private float strength;
	
//	Tecnicas
	private float pass;
	private float kick;
	private float disarm;
	private float technique;
	private float mark;
	
	//Psico
	private float positioning;
	private float decision;
	private float aggressiveness;
	private float goalKeaper;
	private float workIndex;
	
	private String ignore;
	
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

	public float getResistence() {
		return resistence;
	}

	public void setResistence(float resistence) {
		this.resistence = resistence;
	}

	public float getImpulse() {
		return impulse;
	}

	public void setImpulse(float impulse) {
		this.impulse = impulse;
	}

	public float getStrength() {
		return strength;
	}

	public void setStrength(float strength) {
		this.strength = strength;
	}

	public float getDisarm() {
		return disarm;
	}

	public void setDisarm(float disarm) {
		this.disarm = disarm;
	}

	public float getTechnique() {
		return technique;
	}

	public void setTechnique(float technique) {
		this.technique = technique;
	}

	public float getMark() {
		return mark;
	}

	public void setMark(float mark) {
		this.mark = mark;
	}

	public float getPositioning() {
		return positioning;
	}

	public void setPositioning(float positioning) {
		this.positioning = positioning;
	}

	public float getDecision() {
		return decision;
	}

	public void setDecision(float decision) {
		this.decision = decision;
	}

	public float getAggressiveness() {
		return aggressiveness;
	}

	public void setAggressiveness(float aggressiveness) {
		this.aggressiveness = aggressiveness;
	}

	public float getGoalKeaper() {
		return goalKeaper;
	}

	public void setGoalKeaper(float goalKeaper) {
		this.goalKeaper = goalKeaper;
	}

	public float getWorkIndex() {
		return workIndex;
	}

	public void setWorkIndex(float workIndex) {
		this.workIndex = workIndex;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public float getAge() {
		return age;
	}

	public void setAge(float age) {
		this.age = age;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public Float getSalary() {
		return salary;
	}

	public void setSalary(Float salary) {
		this.salary = salary;
	}

	public String getIgnore() {
		return ignore;
	}

	public void setIgnore(String ignore) {
		this.ignore = ignore;
	}

}