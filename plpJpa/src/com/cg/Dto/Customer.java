package com.cg.Dto;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "customer")
public class Customer {
	
	
		private static final long serialVersionUID = 1L;
		private String name;
		@Id
		private String mobileNo;
		@OneToOne(cascade=CascadeType.ALL)
		@JoinColumn(name="wallet_id")
		private Account account;

		public Customer() {
			super();
		}

		public Customer(String name, Account account) {
			super();
			this.name = name;
			this.account = account;
		}

		public Customer(String name2, String mobileNo2, Account account2) {
			this.name=name2;
			mobileNo=mobileNo2;
			account=account2;
		}
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getMobileNo() {
			return mobileNo;
		}
		public void setMobileNo(String mobileNo) {
			this.mobileNo = mobileNo;
		}
		public Account getAccount() {
			return account;
		}
		public void setWallet(Account account) {
			this.account = account;
		}
		@Override
		public String toString() {
			return "Customer name=" + name + ", mobileNo=" + mobileNo
					 + account;
		}
		
	}

