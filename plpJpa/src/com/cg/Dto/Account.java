package com.cg.Dto;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Account {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "wallet_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int accountId;
	
	public int getAccountId() {
		return accountId;
	}

	public void setWalletId(int walletId) {
		this.accountId = walletId;
	}

	public Account(int accountId, BigDecimal balance) {
		super();
		this.accountId = accountId;
		this.balance = balance;
	}

	private BigDecimal balance;

	
	public Account() {
		super();
	}

	public Account(BigDecimal amount) {
		this.balance=amount;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return ", balance="+balance;
	}

}
