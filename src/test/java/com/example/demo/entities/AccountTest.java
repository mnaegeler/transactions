package com.example.demo.entities;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.Test;

public class AccountTest {

	private static final Long ID = 1L;

	@Test
	public void shouldEqual() {
		var account = Account.builder().id(ID).build();
		var accountDuplicated = Account.builder().id(ID).build();

		boolean isEqual = account.equals(accountDuplicated);

		assertThat(isEqual).isTrue();
	}

	@Test
	public void shouldBuild() {
		String documentNumber = "123.456.789-00";
		var account =
				Account.builder()
						.id(ID)
						.documentNumber(documentNumber)
						.availableCreditLimit( BigDecimal.TEN )
						.build();

		assertThat(account)
				.extracting("id", "documentNumber", "availableCreditLimit")
				.containsExactly(ID, documentNumber, BigDecimal.TEN);
	}
}
