package org.aaf.engine.dto;

import org.aaf.engine.model.Country;

public class CountryDTO {
 
    private String name;
 
    private String cod;
    
    private String firtLeague;
    
    private long capacity;
    
    private long activeTeams;
    
    private Country country;
    
    public Country getCountry(){
    	if(country == null){
    		country = new Country();
    	}
    	country.setCod(this.cod);
    	country.setName(this.name);
		return country;
    }

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

	public String getFirtLeague() {
		return firtLeague;
	}

	public void setFirtLeague(String firtLeague) {
		this.firtLeague = firtLeague;
	}

	public long getCapacity() {
		return capacity;
	}

	public void setCapacity(long capacity) {
		this.capacity = capacity;
	}

	public long getActiveTeams() {
		return activeTeams;
	}

	public void setActiveTeams(long activeTeams) {
		this.activeTeams = activeTeams;
	}
}