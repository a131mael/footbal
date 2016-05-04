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

import org.aaf.webInterface.model.Country;
import org.aaf.webInterface.model.League;
import org.aaf.webInterface.model.Member;
import org.aaf.webInterface.model.Team;
import org.aaf.webInterface.model.User;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import java.util.List;
import java.util.logging.Logger;

@Stateless
public class TeamService {

    @Inject
    private Logger log;

    @Inject
    private EntityManager em;

    @SuppressWarnings("unchecked")
	public Team getAvailableTeam(Country country) throws Exception {
    	StringBuilder queryLeague = new StringBuilder();
    	queryLeague.append("db.League.find({'level': ");
    	queryLeague.append(3);
    	queryLeague.append("})");
		Query query2 = em.createNativeQuery(queryLeague.toString(), League.class);
		List<League> leagues = (List<League>) query2.getResultList();
		StringBuilder queryTeam = new StringBuilder();
		Team team = null;
		for(League league :leagues){
			queryTeam.append("db.Team.find({'owner_id':{'$exists':false},");
			queryTeam.append("'league_id':");
			queryTeam.append(league.getId());
			queryTeam.append(" })");
			
			Query query = em.createNativeQuery(queryTeam.toString(), Team.class);
			team = (Team) query.getResultList().get(0);
			queryTeam = new StringBuilder();
			if(team != null){
				return team;
			}
		}
		
		return team;
    }
    
    @SuppressWarnings("unchecked")
	public List<Team> getTeans(Long idLeague) {
		StringBuilder sql = new StringBuilder();
		sql.append("db.Team.find({'league_id': ");
		sql.append(idLeague);
		sql.append("})");
  
		Query query = em.createNativeQuery(sql.toString(), Team.class);
		return  query.getResultList();
	}
}
