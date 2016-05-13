package org.aaf.engine.service;


import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.aaf.engine.model.Property;

@Stateless
@Deprecated //decrepted 04/05/16
public class PropertyManager {

	@Inject
   private EntityManager em;

    
   public void save(Property p) {
       em.persist(p);

   }

   public List<Property> queryCache() {
       Query query = em.createNativeQuery("db.Property.find()",Property.class);

       List<Property> list = query.getResultList();
       return list;
   }
   
   public List<Property> queryCache2() {
	   
	    String query1 = "db.Property.find({'value': 'b'})";
	    Query query = em.createNativeQuery(query1, Property.class);
	 
	    List<Property> list = query.getResultList();
	    return list;
	}

}