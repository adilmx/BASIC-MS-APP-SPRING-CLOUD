package com.adilmx.accountservice.controller;

import com.adilmx.accountservice.dto.AccountDto;
import com.adilmx.accountservice.dto.ClientDto;
import com.adilmx.accountservice.entity.Account;
import com.adilmx.accountservice.required.ClientProxyService;
import com.adilmx.accountservice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


@RestController
@RequestMapping("/accounts")
public class AccountRest {


    private AccountService accountService;
    private ClientProxyService clientProxyService;

    public AccountRest(AccountService accountService, ClientProxyService clientProxyService) {
        this.accountService = accountService;
        this.clientProxyService = clientProxyService;
    }

    //all of this rest primary(CRUD) methods are generated automatically using spring data Rest
    //THE JSON RETURNED IS BASED ON JSON HATEOAS (Hypermedia As The Engine of Application State) FORMAT
    /*
     * @GetMapping("/{id}") public Account findById(@PathVariable(name = "id") Long
     * id) { return accountService.findById(id); }
     *
     * @GetMapping("/") public List<Account> findAll() { return
     * accountService.findAll(); }
     *
     * @PostMapping("/") public Account save(@RequestBody Account account) { return
     * accountService.save(account); }
     */
    @GetMapping("/allInfos/{id}")
    public Account findById(@PathVariable(name = "id") Long id) {
        Account account = accountService.findById(id);
        if(account != null){
            ClientDto clientDto = clientProxyService.findById(account.getIdClient());
            if(clientDto != null){
                account.setClient(clientDto);
                return account;
            }
        }
        return null ;
    }
    @GetMapping(path = "/allClients",produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<ClientDto> findAll() {
        return clientProxyService.findAll().getContent() ;
    }

    @PutMapping("/")
    public int updateSolde(@RequestBody AccountDto account){
         return accountService.updateSolde(account);
    }
}
