package com.adilmx.operationservice.dto;

import com.adilmx.operationservice.enums.TypeAccount;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Data
@ToString
public class AccountDto {
    private Long id;
    private double solde;
    private TypeAccount type;
}
