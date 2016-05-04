package org.aaf.engine.service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.aaf.engine.dto.CountryDTO;
import org.aaf.engine.model.Country;
import org.aaf.engine.model.Property;

@Stateless
public class CountryService {

	@Inject
	private EntityManager em;

	@Inject
	private Logger log;

	@Inject
	private LeagueService leagueService;
	
	@Inject
	private TeamService teamService;
	

	public void register(CountryDTO country) throws Exception {

		log.info("Registering " + country.getName());
		leagueService.createLeague(country);
	}

	public void save(Country country) throws Exception {

		log.info("Registering " + country.getName());
		em.persist(country);
	}

	@SuppressWarnings("unchecked")
	public List<CountryDTO> findAllOrderedByName() {

		Query query = em.createQuery("FROM Country p");

		List<CountryDTO> list = new ArrayList<>();
		List<Country> countries = (List<Country>) query.getResultList();

		for (Country country : countries) {
			CountryDTO countryDTO = new CountryDTO();
			countryDTO.setName(country.getName());
			countryDTO.setCod(country.getCod());
			countryDTO.setActiveTeams(teamService.countActiveTeams(country));
			countryDTO.setCapacity(teamService.countCapacity(country));
			list.add(countryDTO);
		}
		return list;
	}

	//TODO query para mongo nativo
	public List<Country> queryCache2MONGODB() {
		String query1 = "db.Property.find({'value': 'b'})";
		Query query = em.createNativeQuery(query1, Country.class);

		List<Country> list = query.getResultList();
		return list;
	}

}