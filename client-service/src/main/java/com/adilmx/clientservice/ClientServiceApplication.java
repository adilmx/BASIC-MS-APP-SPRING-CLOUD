package com.adilmx.clientservice;

import com.adilmx.clientservice.dao.ClientRepository;
import com.adilmx.clientservice.entity.Client;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class ClientServiceApplication {



    public static void main(String[] args) {
        SpringApplication.run(ClientServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(ClientRepository clientRepository, RepositoryRestConfiguration repositoryRestConfiguration){
        return args -> {
            //expose ID of client in the responses
            repositoryRestConfiguration.exposeIdsFor(Client.class);
            clientRepository.save(new Client(null,"adil","xadil@m.com"));
            clientRepository.save(new Client(null,"dmx","dmx@m.com"));
            clientRepository.save(new Client(null,"xrt","xrt@m.com"));
            clientRepository.findAll().forEach(client -> {
                System.out.println(client.toString());
            });
        };
    }
}
