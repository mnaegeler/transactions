package com.example.demo.dtos;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TransactionRequestBodyDTO {

	private final Long accountId;
	private final int operationTypeId;
	private final BigDecimal amount;
}
