package com.adilmx.operationservice.required;

import com.adilmx.operationservice.dto.AccountDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "account-service")
public interface AccountProxyService {
    @GetMapping("/accounts/{id}")
    public AccountDto findById(@PathVariable(name = "id") Long id);

    @PutMapping("/accounts/")
    public int updateSolde(@RequestBody AccountDto account);

    @GetMapping("/accounts")
    PagedModel<AccountDto> findAll();
}
