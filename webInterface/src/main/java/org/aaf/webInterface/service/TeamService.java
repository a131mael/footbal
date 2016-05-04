/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.aaf.webInterface.service;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.aaf.webInterface.model.Country;
import org.aaf.webInterface.model.League;
import org.aaf.webInterface.model.Team;

@Stateless
public class TeamService {

    @Inject
    private Logger log;

    @Inject
    private EntityManager em;

    @SuppressWarnings("unchecked")
	public Team getAvailableTeam(Country country) throws Exception {
    	StringBuilder sql = new StringBuilder();
    	sql.append("SELECT t from TEAM t ");
    	sql.append("left join t.league l ");
    	sql.append("left join l.country c ");
    	sql.append("where 1=1 ");
    	sql.append("and c.id = :idCountry");
    	sql.append("and l.level = :level");
    	
    	Query query = em.createQuery(sql.toString());
		query.setParameter("idCountry", country.getId());
		query.setParameter("level", 3); //TODO colocado valor arbitrario, 3, deve-se pegar o ultimo level, fazer rotina para buscar o ultimo
		return  (Team) query.getSingleResult();
    }
    //TODO query nativa mongoDB
    @SuppressWarnings("unchecked")
	public List<Team> getTeansMONGODB(Long idLeague) {
		StringBuilder sql = new StringBuilder();
		sql.append("db.Team.find({'league_id': ");
		sql.append(idLeague);
		sql.append("})");
  
		Query query = em.createNativeQuery(sql.toString(), Team.class);
		return  query.getResultList();
	}
    
    @SuppressWarnings("unchecked")
	public List<Team> getTeans(Long idLeague) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT t from  Team t ");
		sql.append("left join t.league l ");
		sql.append("where 1=1 ");
		sql.append("and l.id = :idLeague ");
  
		Query query = em.createQuery(sql.toString());
		query.setParameter("idLeague", idLeague);
		return  query.getResultList();
	}
}
