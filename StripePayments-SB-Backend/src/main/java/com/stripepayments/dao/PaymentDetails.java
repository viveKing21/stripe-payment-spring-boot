package com.stripepayments.dao;


import lombok.Data;

@Data
public class PaymentDetails {
	public enum Currency {
		INR, USD
	}
	
	Double amount;
	Currency currency;
	
}
