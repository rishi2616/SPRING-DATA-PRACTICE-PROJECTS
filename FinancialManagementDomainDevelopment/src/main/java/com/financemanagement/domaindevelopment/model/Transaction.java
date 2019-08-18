package com.financemanagement.domaindevelopment.model;

import java.io.Serializable;
import java.time.ZonedDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.financemanagement.domaindevelopment.enums.Currency;
import com.financemanagement.domaindevelopment.enums.TransactionType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "transaction")
@Table(name = "account_transaction")
@NoArgsConstructor
@Getter
@Setter
public class Transaction implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7817135024822644549L;

	@Id
	@Column(length = 25, name = "TRNSCTN_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transactionSequenceGenerator")
	@GenericGenerator(name="transactionSequenceGenerator", 
	strategy="com.financemanagement.domaindevelopment.sequencegenerators.TransactionSequenceGenerator")
	private String transactionId;
	
	@Enumerated(EnumType.STRING)
	@Column(name="TRN_TYP")
	private TransactionType transactionType;

	@Column(length = 1000, name = "TRN_DSCRPTN")
	private String description;

	@ManyToOne
	@JoinColumn(name = "TRN_ACCT_ID", referencedColumnName = "ACCT_ID", nullable = false)
	private Account account;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "CTGRY_ID", referencedColumnName = "CTGRY_ID", nullable = false)
	private Category transactionCategory;
	
	@Enumerated(EnumType.STRING)
	@Column(name="TRN_CRNCY")
	private Currency transactionCurrency;
	
	@Column(name="TRN_AMT", columnDefinition = "NUMBER(20,2)" )
	private double transactionAmount;
	
	
	/*
	 * I just found out that @Temporal can only be used
	 * with java.util.Date or java.sql.Date
	 */
	@Column(name = "TRN_DT", columnDefinition="TIMESTAMP")
	@Type(type="org.hibernate.type.ZonedDateTimeType")
	private ZonedDateTime transactionDate;
	
	}
