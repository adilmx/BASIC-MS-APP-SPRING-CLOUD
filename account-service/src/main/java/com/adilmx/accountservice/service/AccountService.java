package com.adilmx.accountservice.service;

import java.util.List;

import com.adilmx.accountservice.dto.AccountDto;
import com.adilmx.accountservice.entity.Account;
import org.springframework.stereotype.Service;


public interface AccountService {

	public Account findById(Long id);
	public List<Account> findAll();
	public Account save(Account account);
	public int updateSolde(AccountDto account);
}
