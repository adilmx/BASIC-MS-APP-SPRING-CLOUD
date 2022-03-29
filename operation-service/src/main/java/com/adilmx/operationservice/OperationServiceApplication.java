package com.adilmx.operationservice;

import com.adilmx.operationservice.dao.OperationRepo;
import com.adilmx.operationservice.dto.AccountDto;
import com.adilmx.operationservice.dto.OperationDto;
import com.adilmx.operationservice.required.AccountProxyService;
import com.adilmx.operationservice.service.OperationService;

import java.util.Collection;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients
public class OperationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OperationServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(OperationService operationService,AccountProxyService accountProxyService){
		return args -> {
			operationService.deposit(new OperationDto(10,1L));
			operationService.withdrawal(new OperationDto(10,2L));
			operationService.transfer(new OperationDto(10,1L,2L));
			Collection<AccountDto> accounts =  accountProxyService.findAll().getContent();
			accounts.forEach(account -> {
				System.out.println(account.toString());
			});
            operationService.findAll().forEach(operation -> {
				System.out.println(operation.toString());
			});

		};
	}

}
