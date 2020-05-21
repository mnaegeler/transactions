package com.example.demo.dtos;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import com.example.demo.entities.Account;
import com.example.demo.entities.OperationTypes;
import com.example.demo.entities.Transaction;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDTO {

	private String id;
	private Account account;
	private OperationTypes operation;
	private BigDecimal amount;
	private Date eventDate;

	public TransactionDTO(Transaction transaction) {
		id = transaction.getId();
		account = transaction.getAccount();
		operation = transaction.getOperation();
		amount = transaction.getAmount();
		eventDate = transaction.getEventDate();
	}
}
