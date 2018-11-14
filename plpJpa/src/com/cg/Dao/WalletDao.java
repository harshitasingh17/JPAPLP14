package com.cg.Dao;

import java.math.BigDecimal;
import java.util.List;

import com.cg.Dto.Customer;
import com.cg.Dto.Transaction;
import com.cg.Exception.WalletException;

public interface WalletDao {

	public boolean save(Customer customer) throws WalletException;

	public Customer findOne(String mobileNo) throws WalletException;

	public void startTransaction();
	
	public void commitTransaction();

	public void update(Customer customer);
	
	public List<Transaction> getAllTrasactions(String mobileNo);
	
	public boolean save(Transaction transaction) throws WalletException;

}
