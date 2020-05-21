package com.example.demo.dtos;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import com.example.demo.entities.Account;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountDTO {
	private Long id;
	private String documentNumber;
	private BigDecimal availableCreditLimit;

	public AccountDTO(Account account) {
		id = account.getId();
		documentNumber = account.getDocumentNumber();
		availableCreditLimit = account.getAvailableCreditLimit();
	}

	public Account toEntity() {
		return Account.builder()
				.id( id )
				.documentNumber( documentNumber )
				.availableCreditLimit( availableCreditLimit )
				.build();
	}
}
