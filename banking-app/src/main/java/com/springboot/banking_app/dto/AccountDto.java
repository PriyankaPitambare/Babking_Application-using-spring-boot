package com.springboot.banking_app.dto;

import lombok.Data;

@Data
public class AccountDto {
	
	private Long id;
	public AccountDto(Long id, String username, double balance) {
		super();
		this.id = id;
		this.username = username;
		this.balance = balance;
	}
	private String username;
	private double balance;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}

}
