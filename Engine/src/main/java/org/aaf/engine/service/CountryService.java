package org.aaf.engine.service;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.aaf.engine.model.Country;
import org.aaf.engine.model.Member;
import org.aaf.engine.model.Property;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class CountryService {

   @PersistenceContext(unitName = "mongo-ogm")
   private EntityManager em;
   
   @Inject
   private Logger log;

   public void register(Country country) throws Exception {
       log.info("Registering " + country.getName());
       em.persist(country);
   }

}