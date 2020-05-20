package com.example.demo.interactions;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.example.demo.dtos.TransactionRequestBodyDTO;
import com.example.demo.entities.Account;
import com.example.demo.entities.Transaction;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.repositories.TransactionRepository;

@RunWith(MockitoJUnitRunner.class)
public class TransactionServiceTest {

	private static final Long ID = 1L;
	private static final Account ACCOUNT = Account.builder().id( ID ).documentNumber( "123.456.789-00" ).build();
	private static final TransactionRequestBodyDTO BODY = new TransactionRequestBodyDTO(ID, 4, new BigDecimal( 250.35 ));

	@InjectMocks
	private TransactionService service;

	@Mock
	private AccountService accountService;

	@Mock
	private TransactionRepository repository;

	@Test
	public void save(){
		given(accountService.find( ID ) ).willReturn( Optional.of( ACCOUNT ) );

		service.save( BODY );

		verify( repository ).save( any( Transaction.class ) );
	}

	@Test(expected = NotFoundException.class)
	public void saveWithAnInvalidAccount() {
		try {
			service.save( BODY );
		} catch (Exception e) {
			verify( repository, never() ).save( any( Transaction.class ) );
			throw e;
		}
	}
}
