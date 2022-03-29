package com.adilmx.accountservice.required;

import com.adilmx.accountservice.dto.ClientDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="client-service")
public interface ClientProxyService {
    //!we can name this method whatever we want
    @GetMapping("/clients/{id}")
    ClientDto findById(@PathVariable(name = "id") Long id);

    //!we used here PageModel to be able to map between the Json in format HATEOAS and a class java
    @GetMapping("/clients")
    PagedModel<ClientDto> findAll();
}
