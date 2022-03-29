package com.adilmx.operationservice.dao;

import com.adilmx.operationservice.entity.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface OperationRepo extends JpaRepository<Operation,Long> {
}
