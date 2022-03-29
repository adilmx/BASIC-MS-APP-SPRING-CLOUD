package com.adilmx.accountservice.entity;

import java.util.Date;

import javax.persistence.*;

import com.adilmx.accountservice.dto.ClientDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String lib;
	private double solde;
	@Temporal(TemporalType.DATE)
	private Date dateCreation;
	@Enumerated(EnumType.STRING)
	private TypeAccount type;
	@Enumerated(EnumType.STRING)
	private StatusAccount status;
	private long idClient;
	@Transient
	private ClientDto client;
}
