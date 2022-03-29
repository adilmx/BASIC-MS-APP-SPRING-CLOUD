package com.adilmx.operationservice.service;

import com.adilmx.operationservice.dto.OperationDto;
import com.adilmx.operationservice.entity.Operation;

import java.util.List;

public interface OperationService {
    public int deposit(OperationDto operationDto);
    public int withdrawal(OperationDto operationDto);
    public int transfer(OperationDto operationDto);
    public List<Operation> findAll();
}
