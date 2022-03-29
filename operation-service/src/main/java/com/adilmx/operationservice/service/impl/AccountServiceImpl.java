package com.adilmx.operationservice.service.impl;

import com.adilmx.operationservice.dao.OperationRepo;
import com.adilmx.operationservice.dto.AccountDto;
import com.adilmx.operationservice.dto.OperationDto;
import com.adilmx.operationservice.entity.Operation;
import com.adilmx.operationservice.enums.TypeOperation;
import com.adilmx.operationservice.required.AccountProxyService;
import com.adilmx.operationservice.service.OperationService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class AccountServiceImpl implements OperationService {
    private OperationRepo operationRepo;
    private AccountProxyService accountProxyService;

    public AccountServiceImpl(OperationRepo operationRepo, AccountProxyService accountProxyService) {
        this.operationRepo = operationRepo;
        this.accountProxyService = accountProxyService;
    }

    @Override
    public int deposit(OperationDto operationDto) {
        Map<String, AccountDto> accountMap = new HashMap<>();
        AccountDto account = accountProxyService.findById(operationDto.getAccountSrcId());

        if (account != null) {

            accountMap.put("src", account);
            Operation operation = initOperation(accountMap, operationDto.getMontant(), TypeOperation.DEPOSIT);
            if (operation != null) {
                operationRepo.save(operation);
                accountProxyService.updateSolde(account);
                return 1;
            }
            return -1;
        }
        return 0;
    }

    @Override
    public int withdrawal(OperationDto operationDto) {
        Map<String, AccountDto> accountMap = new HashMap<>();
        AccountDto account = accountProxyService.findById(operationDto.getAccountSrcId());
        if (account != null) {
            accountMap.put("src", account);
            Operation operation = initOperation(accountMap, operationDto.getMontant(), TypeOperation.WITHDRAWAL);
            if (operation != null) {
                operationRepo.save(operation);
                accountProxyService.updateSolde(account);
                return 1;
            }
            return -1;
        }
        return 0;
    }

    @Override
    public int transfer(OperationDto operationDto) {
        Map<String, AccountDto> accountMap = new HashMap<>();
        AccountDto accountSrc = accountProxyService.findById(operationDto.getAccountSrcId());
        AccountDto accountDest = accountProxyService.findById(operationDto.getAccountDestId());
        if (accountSrc != null && accountDest != null) {
            accountMap.put("src", accountSrc);
            accountMap.put("dest", accountDest);
            Operation operation = initOperation(accountMap, operationDto.getMontant(), TypeOperation.TRANSFER);
            if (operation != null) {
                operationRepo.save(operation);
                accountProxyService.updateSolde(accountSrc);
                accountProxyService.updateSolde(accountDest);
                return 1;
            }
            return -1;
        }
        return 0;
    }

    @Override
    public List<Operation> findAll() {
        return operationRepo.findAll();
    }

    public Operation initOperation(Map<String, AccountDto> accountMap, double mt, TypeOperation typeOperation) {
        double soldeSrc, soldeDest = 0;
        Operation operation = new Operation();
        switch (typeOperation) {
            case DEPOSIT: {
                soldeSrc = accountMap.get("src").getSolde() + mt;
                accountMap.get("src").setSolde(soldeSrc);
                operation.setAccountSrc(accountMap.get("src"));
                operation.setAccountSrcId(accountMap.get("src").getId());
                break;
            }
            case WITHDRAWAL: {
                soldeSrc = accountMap.get("src").getSolde() - mt;
                accountMap.get("src").setSolde(soldeSrc);
                operation.setAccountSrc(accountMap.get("src"));
                operation.setAccountSrcId(accountMap.get("src").getId());
                break;
            }
            case TRANSFER: {
                soldeSrc = accountMap.get("src").getSolde() - mt;
                soldeDest = accountMap.get("dest").getSolde() + mt;
                accountMap.get("src").setSolde(soldeSrc);
                accountMap.get("dest").setSolde(soldeDest);
                operation.setAccountSrc(accountMap.get("src"));
                operation.setAccountDest(accountMap.get("dest"));
                operation.setAccountSrcId(accountMap.get("src").getId());
                operation.setAccountDestId(accountMap.get("dest").getId());
                break;
            }
        }
        operation.setDateOperation(new Date());
        operation.setTypeOperation(typeOperation);
        operation.setMontant(mt);
        return operation;
    }
}
