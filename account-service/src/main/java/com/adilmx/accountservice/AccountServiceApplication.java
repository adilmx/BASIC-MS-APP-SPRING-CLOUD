package com.adilmx.accountservice;

import com.adilmx.accountservice.dto.ClientDto;
import com.adilmx.accountservice.entity.Account;
import com.adilmx.accountservice.entity.StatusAccount;
import com.adilmx.accountservice.entity.TypeAccount;
import com.adilmx.accountservice.required.ClientProxyService;
import com.adilmx.accountservice.service.AccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.util.Date;

@SpringBootApplication
@EnableFeignClients
public class AccountServiceApplication  {


    public static void main(String[] args) {
        SpringApplication.run(AccountServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(AccountService accountService,ClientProxyService clientProxyService,RepositoryRestConfiguration repositoryRestConfiguration){
        return args -> {
        	//expose ID of Account in the responses
            repositoryRestConfiguration.exposeIdsFor(Account.class);
        	ClientDto client1 = clientProxyService.findById(1L);
            if(client1!= null){
                System.out.println(client1);
            }
            ClientDto client2 = clientProxyService.findById(2L);
            if(client2!= null){
                System.out.println(client2);
            }
            ClientDto client3 = clientProxyService.findById(3L);
            if(client3!= null){
                System.out.println(client3);
            }
            accountService.save(new Account(null, "ac-1",1000, new Date(), TypeAccount.COURANT, StatusAccount.OPEN,client1.getId(),null));
            accountService.save(new Account(null, "ac-2",2000, new Date(), TypeAccount.COURANT, StatusAccount.CLOSED,client2.getId(),null));
            accountService.save(new Account(null, "ac-3",3000, new Date(), TypeAccount.EPARGNE, StatusAccount.OPEN,client3.getId(),null));
            accountService.findAll().forEach(account -> {
                System.out.println(account);
            });
        };
    }
  

}
