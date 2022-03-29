package com.adilmx.accountservice.service.impl;


import java.util.List;

import com.adilmx.accountservice.dto.AccountDto;
import com.adilmx.accountservice.entity.Account;
import com.adilmx.accountservice.repository.AccountRepo;
import com.adilmx.accountservice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
    private AccountRepo accountRepo;
	
	
	@Override
	public Account findById(Long id) {
		return accountRepo.findById(id).get();
	}

	@Override
	public List<Account> findAll() {
		return accountRepo.findAll();
	}

	@Override
	public Account save(Account account) {
		return accountRepo.save(account);
	}

	@Override
	public int updateSolde(AccountDto accountDto) {
		Account accountEntity =  findById(accountDto.getId());
		if(accountEntity != null){
			accountEntity.setSolde(accountDto.getSolde());
			accountRepo.save(accountEntity);
			return 1;
		}
		return -1;
	}


}
