package com.cg.Dao;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.cg.Dto.Customer;

public class JPAUtil {
	
		static EntityManagerFactory emFact=null;
		static EntityManager entityManager=null;
		
		public static EntityManager getEntityManager() // this method give us entity manager
		{
			emFact=Persistence.createEntityManagerFactory("JPA-PU-Oracle"); //persistence unit of oracle
			entityManager=emFact.createEntityManager();
			return entityManager;
		}
	}
