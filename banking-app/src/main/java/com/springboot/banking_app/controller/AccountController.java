package com.springboot.banking_app.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.banking_app.dto.AccountDto;
import com.springboot.banking_app.service.AccountService;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

	private AccountService accountService;

	public AccountController(AccountService accountService) {
		
		this.accountService = accountService;
	}
	
	//add account to rest API
	
	@PostMapping
	public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto){
		return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
	}
	
	//get Account Details based on Id
	@GetMapping("/{id}")
	public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id){
		
	AccountDto accountDto=	accountService.getAccountById(id);
	return ResponseEntity.ok(accountDto);
	}
	
	
	//update the details
	@PutMapping("/{id}/deposit")
	public ResponseEntity<AccountDto> deposit(@PathVariable Long id, @RequestBody Map<String, Double> request){

		
		AccountDto accountDto=accountService.deposit(id, request.get("amount"));
		return ResponseEntity.ok(accountDto);
	}
	
	//withdraw money from account
	@PutMapping("/{id}/withdraw")
	public ResponseEntity<AccountDto> withdraw(@PathVariable Long id, @RequestBody Map<String, Double> request){

		AccountDto accountDto=accountService.withdraw(id, request.get("amount"));

		return ResponseEntity.ok(accountDto);
	}
	
	//get all data for delete
	@GetMapping
	public ResponseEntity<List<AccountDto>>getAllAccounts(){
		
		
		List<AccountDto> accountDto=accountService.getAllAccounts();
		return ResponseEntity.ok(accountDto);
	}
	
	//delete
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteAccount(@PathVariable Long id){

		accountService.deleteAccount(id);
		return ResponseEntity.ok("Account deleted succesfully !!");
	}
	
}
