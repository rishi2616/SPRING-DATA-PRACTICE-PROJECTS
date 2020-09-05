package com.hibernatepractice.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyKey implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6910527837167344312L;

	private String name;
	
	private String countryName;
}
