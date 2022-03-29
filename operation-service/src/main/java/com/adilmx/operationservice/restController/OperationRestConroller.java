package com.adilmx.operationservice.restController;

import com.adilmx.operationservice.dto.OperationDto;
import com.adilmx.operationservice.entity.Operation;
import com.adilmx.operationservice.service.OperationService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/operations")
public class OperationRestConroller {
    private OperationService operationService;

    public OperationRestConroller(OperationService operationService) {
        this.operationService = operationService;
    }
    @PutMapping("/deposit")
    public int deposit(@RequestBody OperationDto operationDto){
        return operationService.deposit(operationDto);
    }
    @PutMapping("/withdrawal")
    public int withdrawal(@RequestBody OperationDto operationDto){
        return operationService.withdrawal(operationDto);
    }
    @PutMapping("/transfer")
    public int transfer(@RequestBody OperationDto operationDto){
        return operationService.transfer(operationDto);
    }
}
