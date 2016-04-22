package org.aaf.webInterface.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
 
@Entity
public class Country implements Serializable{
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 4669406790432703794L;

	@Id
	@GeneratedValue(generator = "GENERATE_Country", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "GENERATE_Country", sequenceName = "Country_pk_seq", allocationSize = 1)
	private Long id;
 
    private String name;
 
    private String cod;

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
}