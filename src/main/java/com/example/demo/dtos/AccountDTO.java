package com.example.demo.dtos;

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
