package com.adilmx.operationservice.entity;

import com.adilmx.operationservice.dto.AccountDto;
import com.adilmx.operationservice.enums.TypeOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Temporal(TemporalType.DATE)
    private Date dateOperation;
    private double montant;
    @Enumerated(EnumType.STRING)
    private TypeOperation typeOperation;
    private long accountSrcId;
    @Transient
    private AccountDto accountSrc;
    private long accountDestId;
    @Transient
    private AccountDto accountDest;
}
