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

import org.aaf.webInterface.model.Player;
import org.aaf.webInterface.util.HabilityEnum;

@Stateless
public class PlayerService {

    @Inject
    private Logger log;

    @Inject
    private EntityManager em;

//TODO query nativa para mongo
	public List<Player> getPlayersMONGODB(Long id, String orderBy) {
		StringBuilder sql = new StringBuilder();
    	sql.append("db.Player.find({'team_id': ");
    	sql.append(id);
    	sql.append("},");//Query
    	sql.append("{'ignore': 0},"); //Projecao
    	
    	sql.append("{ 'sort': [['"); //Sorte
//    	sql.append(orderBy);
    	sql.append("age");
    	sql.append("',");
    	sql.append("'asc'");
    	sql.append("]]}");
    	sql.append(")");
		Query query = em.createNativeQuery(sql.toString(), Player.class);
		return  query.getResultList();
		
	}
	@SuppressWarnings("unchecked")
	public List<Player> getPlayers(Long teamID, String orderBy) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT p from  Player p ");
		sql.append("left join p.team t ");
		sql.append("where 1 = 1 ");
		sql.append("and t.id = :teamID ");
		
		Query query = em.createQuery(sql.toString());
		query.setParameter("teamID", teamID);
		return  query.getResultList();
		
	}
}
