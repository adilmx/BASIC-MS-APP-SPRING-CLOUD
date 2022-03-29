package com.adilmx.operationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OperationDto {
    private double montant;
    private long accountSrcId;
    private long accountDestId;

    public OperationDto(double montant, long accountSrcId) {
        this.montant = montant;
        this.accountSrcId = accountSrcId;
    }
}
