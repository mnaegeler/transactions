package com.example.demo.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

import com.example.demo.entities.Account;

@Getter
@AllArgsConstructor
public class AccountDTO {
	private final Long id;
	private final String documentNumber;

	public AccountDTO(Account account) {
		id = account.getId();
		documentNumber = account.getDocumentNumber();
	}

	public Account toEntity() {
		return Account.builder()
				.id( id )
				.documentNumber( documentNumber )
				.build();
	}
}
