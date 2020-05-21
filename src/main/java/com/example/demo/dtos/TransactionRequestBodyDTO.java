package com.example.demo.dtos;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequestBodyDTO {

	private Long accountId;
	private int operationTypeId;
	private BigDecimal amount;
}
