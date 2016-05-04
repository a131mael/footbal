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

import org.aaf.webInterface.model.Match;
import org.aaf.webInterface.model.Player;

@Stateless
public class MatchService {

    @Inject
    private Logger log;

    @Inject
    private EntityManager em;

    //TODO query mongoDB
	@SuppressWarnings("unchecked")
	public List<Match> getLastMatchesMongoDB(Long id) {
		StringBuilder sql = new StringBuilder();
    	sql.append("db.Match.find({ '$or':[  {'visitTeam_id': ");
    	sql.append(id);
    	sql.append("},{'homeTeam_id' : ");
    	sql.append(id);
    	sql.append("}]})");
//    	sql.append(".sort( { 'round': 1 } )");
		Query query = em.createNativeQuery(sql.toString(), Match.class);
		return  query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Match> getLastMatches(Long id) {
		StringBuilder sql = new StringBuilder();
		sql.append("Select m from Match m  ");
		sql.append("left join m.homeTeam ht ");
		sql.append("left join m.visitTeam vt ");
		sql.append("where 1=1 ");
		sql.append("and (");
		sql.append("vt.id = :idTeam ");
		sql.append("or ht.id = :idTeam ");
		sql.append(") ");
		Query query = em.createQuery(sql.toString());
		query.setParameter("idTeam", id);
		return  query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Match> getMatches(Long id,int session, int week) {
		StringBuilder sql = new StringBuilder();
		sql.append("Select m from Match m  ");
		sql.append("left join m.homeTeam ht ");
		sql.append("left join m.visitTeam vt ");
		sql.append("where 1=1 ");
		sql.append("and (");
		sql.append("vt.id = :idTeam ");
		sql.append("or ht.id = :idTeam ");
		sql.append(") ");
		sql.append("and m.session = :session");
		sql.append("and m.week = :week");
		Query query = em.createQuery(sql.toString());
		query.setParameter("session", session);
		query.setParameter("idTeam", id);
		query.setParameter("week", week);
		return  query.getResultList();
		
		
	}
}
