package com.financemanagement.domaindevelopment.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.financemanagement.domaindevelopment.enums.Currency;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "account")
@Inheritance(strategy = InheritanceType.JOINED)
public class Account implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1540097108451574865L;

	@Id
	@Column(length = 20, name = "ACCT_ID", updatable = false, insertable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "accSequenceGen")
	@GenericGenerator(name = "accSequenceGen", 
	strategy = "com.financemanagement.domaindevelopment.sequencegenerators.AccountSequenceGenerator")
	private String accountId;

	@Column(length = 50, name = "account_number")
	private String accountNumber;
	
	@Enumerated(EnumType.STRING)
	@Column(name="ACC_CRNCY")
	private Currency currency;

	@Column(length = 100, name = "account_title")
	private String accountDesc;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.DETACH, CascadeType.REFRESH})
	private CustomerPortfolio customerPortfolio;

	@OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, mappedBy = "account")
	private List<Transaction> transactionList;
	
	@OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	private List<AccountFees> accountFeesList;
	
	
}
