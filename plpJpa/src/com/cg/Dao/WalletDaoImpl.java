package com.cg.Dao;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.cg.Dto.Account;
import com.cg.Dto.Customer;
import com.cg.Dto.Transaction;
import com.cg.Exception.WalletException;

public class WalletDaoImpl implements WalletDao {
	
private EntityManager entityManager;
	
	public WalletDaoImpl() {
		entityManager = JPAUtil.getEntityManager();
	}
	
	@Override
	public boolean save(Customer customer) {
		try {
			entityManager.persist(customer);
		}
		catch(Exception e)
		{
			return false;
		}
		
		return true;
	}

	@Override
	public Customer findOne(String mobileNo) throws WalletException {
		Customer customer = entityManager.find(Customer.class, mobileNo);
		return customer;
	}

	@Override
	public void startTransaction() {
		
		entityManager.getTransaction().begin();
		
	}

	@Override
	public void commitTransaction() {
		
		entityManager.getTransaction().commit();
		
	}

	@Override
	public void update(Customer customer) {
		
		entityManager.merge(customer);
		
	}

	@Override
	public List<Transaction> getAllTrasactions(String mobileNo) {
		String str =  "select trans from Transactions trans where mobileNo =:mob";
		Query query = entityManager.createQuery(str,Transaction.class);
		query.setParameter("mob", mobileNo);
		return query.getResultList();
	}

	@Override
	public boolean save(Transaction transaction) throws WalletException {
		try {
			entityManager.persist(transaction);
		}
		catch(Exception e)
		{
			return false;
		}
		
		return true;
	}


}
