package com.cg.Service;

import java.math.BigDecimal;

import java.util.List;
import java.util.Date;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.cg.Dao.WalletDao;
import com.cg.Dao.WalletDaoImpl;
import com.cg.Dto.Account;
import com.cg.Dto.Customer;
import com.cg.Dto.Transaction;
import com.cg.Exception.LowBalance;
import com.cg.Exception.WalletException;

public class WalletServiceImpl implements WalletService {
	private WalletDao dao;

	public WalletServiceImpl() {
		dao = new WalletDaoImpl();
	}

	public Customer createAccount(String name, String mobileNo, BigDecimal amount) throws WalletException {
		if(isValid(mobileNo) && isValidName(name) && amount.compareTo(new BigDecimal(0)) > 0) {
			Account account = new Account();
			Customer customer = new Customer();
		
			account.setBalance(amount);
			customer.setName(name);
			customer.setMobileNo(mobileNo);
			customer.setWallet(account);
			
			dao.startTransaction();
			dao.save(customer);
			dao.commitTransaction();

			return customer;
		}
		else throw new WalletException();

	}

	public Customer showBalance(String mobileNo) throws WalletException {
		
		dao.startTransaction();
		Customer customer=dao.findOne(mobileNo);
		dao.commitTransaction();
		
		if(customer!=null)
			return customer;
		else
			throw new WalletException("Invalid mobile no ");
	}

	public Customer fundTransfer(String sourceMobileNo, String targetMobileNo, BigDecimal amount) throws WalletException, LowBalance {
		
		if(isValid(sourceMobileNo) == false || isValid(targetMobileNo) == false) 
			throw new WalletException();
		
		Customer customer = withdrawAmount(sourceMobileNo, amount);
		depositAmount(targetMobileNo, amount);
		
		return customer;
	}

	public Customer depositAmount(String mobileNo, BigDecimal amount) throws WalletException 
	{
		if(amount.compareTo(new BigDecimal(0)) <= 0) 
			throw new WalletException("Enter valid amount");
		
		if(isValid(mobileNo)) {
			Customer customer = dao.findOne(mobileNo);
			Account wallet = customer.getAccount();
			
			wallet.setBalance(wallet.getBalance().add(amount));
			Transaction transactions = new Transaction();
			transactions.setAmount(amount);
			transactions.setMobileNo(mobileNo);
			transactions.setTransactionType("deposit");
			transactions.setTransactionStatus("success");
			transactions.setTransactionDate(new Date());
			dao.save(transactions);
			
			dao.startTransaction();
			dao.update(customer);
			dao.commitTransaction();
			
			return customer;
		}
		else throw new WalletException("Enter valid mobile number");
	}

	public boolean isValid(String mobileNo) {
		if(mobileNo.matches("[1-9][0-9]{9}")) 
		{
			return true;
		}		
		else 
			return false;
	}
	
	private boolean isValidName(String name) {
		if( name == null || name.trim().isEmpty() )
			return false;
		return true;
	}

	public Customer withdrawAmount(String mobileNo, BigDecimal amount) throws WalletException, LowBalance 
	{	
		if(amount.compareTo(new BigDecimal(0)) <= 0) 
			throw new WalletException("Enter valid amount");
		
		if(isValid(mobileNo)) 
		{
			Customer customer = dao.findOne(mobileNo);
			Account account = customer.getAccount();
			
			if(amount.compareTo(account.getBalance()) > 0) 
				throw new LowBalance("Amount is not sufficient in your account");
			
			account.setBalance(account.getBalance().subtract(amount));
			customer.setWallet(account);
			
			Transaction transactions = new Transaction();
			transactions.setAmount(amount);
			transactions.setMobileNo(mobileNo);
			transactions.setTransactionType("withdraw");
			transactions.setTransactionStatus("success");
			transactions.setTransactionDate(new Date());
			dao.save(transactions);
			
			dao.startTransaction();
			dao.update(customer);
			dao.commitTransaction();
			
			return customer;
		}
		else throw new WalletException("Enter valid mobile number");
	}

	@Override
	public List<Transaction> getAllTrasactions(String mobileNo) {
		return dao.getAllTrasactions(mobileNo);
		
	}

	}


