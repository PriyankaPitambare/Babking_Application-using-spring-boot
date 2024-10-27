package com.springboot.banking_app.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.springboot.banking_app.dto.AccountDto;
import com.springboot.banking_app.entity.Account;
import com.springboot.banking_app.mapper.AccountMapper;
import com.springboot.banking_app.repository.AccountRepository;
import com.springboot.banking_app.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

	
	private AccountRepository accountRepository;
	
	public AccountServiceImpl(AccountRepository accountRepository) {
		
		this.accountRepository = accountRepository;
	}

	@Override
	public AccountDto createAccount(AccountDto accountDto) {
		
		Account account=AccountMapper.mapToAccount(accountDto);
		Account savedAccount = accountRepository.save(account);
		
		return AccountMapper.mapToAccountDto(savedAccount);
	}
	
	@Override
	public AccountDto getAccountById(Long id) {
		Account account=accountRepository.findById(id).orElseThrow(()-> new RuntimeException("account does not exists"));
		return AccountMapper.mapToAccountDto(account);
	
	}
	
	@Override
	public AccountDto deposit(Long id, double amount) {
		Account account=accountRepository.findById(id).orElseThrow(()-> new RuntimeException("account does not exists"));
		
		double totalBalance=account.getBalance()+amount;
		account.setBalance(totalBalance);
		Account savedAccount=accountRepository.save(account);
		return AccountMapper.mapToAccountDto(savedAccount);
	}
	
	
	@Override
	public AccountDto withdraw(Long id, double amount) {

		Account account=accountRepository.findById(id).orElseThrow(()-> new RuntimeException("account does not exists"));

		if(account.getBalance()<amount) {
			throw new RuntimeException("insufficient balance");
		}
		
		double totalBalance=account.getBalance()-amount;
		
		account.setBalance(totalBalance);
		Account savedAccount=accountRepository.save(account);
		
		return AccountMapper.mapToAccountDto(savedAccount);
	}

	@Override
	public List<AccountDto> getAllAccounts() {

		return accountRepository.findAll().stream().map((account)->AccountMapper.mapToAccountDto(account)).collect(Collectors.toList());
	}

	@Override
	public void deleteAccount(Long id) {
		Account account=accountRepository.findById(id).orElseThrow(()-> new RuntimeException("account does not exists"));

		accountRepository.delete(account);
	}
	

}
