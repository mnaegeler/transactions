package com.example.demo.entities;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import org.junit.Test;

public class TransactionTest {

	@Test
	public void shouldEqual() {
		String uuid = UUID.randomUUID().toString();
		var transaction = Transaction.builder().id(uuid).build();
		var transactionDuplicated = Transaction.builder().id(uuid).build();

		boolean isEqual = transaction.equals(transactionDuplicated);

		assertThat(isEqual).isTrue();
	}

	@Test
	public void shouldBuild() {
		String uuid = UUID.randomUUID().toString();
		Account account = Account.builder().documentNumber( "123.456.789-00" ).build();
		Date now = new Date();
		var transaction =
				Transaction.builder()
						.id(uuid)
						.account( account )
						.operation( OperationTypes.PAGAMENTO )
						.amount( BigDecimal.TEN )
						.eventDate( now )
						.build();

		assertThat(transaction)
				.extracting("id", "account", "operation", "amount", "eventDate")
				.containsExactly(uuid, account, OperationTypes.PAGAMENTO, BigDecimal.TEN, now);
	}
}
