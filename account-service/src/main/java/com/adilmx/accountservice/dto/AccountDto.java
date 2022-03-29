package com.adilmx.accountservice.dto;

import java.util.Date;


import com.adilmx.accountservice.entity.StatusAccount;
import com.adilmx.accountservice.entity.TypeAccount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {
	private Long id;
	private double solde;
	private TypeAccount type;
}
