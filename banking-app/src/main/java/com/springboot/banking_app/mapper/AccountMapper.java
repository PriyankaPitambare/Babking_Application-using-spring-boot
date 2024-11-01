package com.springboot.banking_app.mapper;

import com.springboot.banking_app.dto.AccountDto;
import com.springboot.banking_app.entity.Account;

public class AccountMapper {

	
	public static Account mapToAccount(AccountDto accountDto) {
		Account account=new Account(
				accountDto.getId(),
				accountDto.getUsername(),
				accountDto.getBalance()
				
				
				
				);
		return account;
	}
	
	public static AccountDto mapToAccountDto(Account account) {
		AccountDto accountDto=new AccountDto(
				account.getId(),
				account.getUsername(),
				account.getBalance()
				
				
				
				);
		return accountDto;
	}
}
