package com.cg.Service;

import java.math.BigDecimal;
import java.util.List;

import com.cg.Dto.Customer;
import com.cg.Dto.Transaction;
import com.cg.Exception.LowBalance;
import com.cg.Exception.WalletException;

public interface WalletService {

	public Customer createAccount(String name ,String mobileno, BigDecimal amount) throws WalletException;
	public Customer showBalance (String mobileno) throws WalletException;
	public Customer fundTransfer (String sourceMobileNo,String targetMobileNo, BigDecimal amount) throws WalletException, LowBalance;
	public Customer depositAmount (String mobileNo,BigDecimal amount ) throws WalletException;
	public Customer withdrawAmount(String mobileNo, BigDecimal amount) throws WalletException, LowBalance;
	public List<Transaction> getAllTrasactions(String mobileNo);
}
