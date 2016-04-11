package org.aaf.engine.service;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.aaf.engine.model.Property;

import javax.ejb.Stateless;

@Stateless

public class PropertyManager {

   @PersistenceContext(unitName = "mongo-ogm")
   private EntityManager em;

    
   public void save(Property p) {
       em.persist(p);

   }

   public List<Property> queryCache() {
       Query query = em.createQuery("FROM Property p");

       List<Property> list = query.getResultList();
       return list;
   }
   
   public List<Property> queryCache2() {
	   
	    String query1 = "db.Property.find({'value': 'value1'})";
	    Query query = em.createNativeQuery(query1, Property.class);
	 
	    List<Property> list = query.getResultList();
	    return list;
	}

}