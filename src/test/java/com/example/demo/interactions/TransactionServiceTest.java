package com.example.demo.interactions;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;


import java.math.BigDecimal;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.example.demo.dtos.TransactionRequestBodyDTO;
import com.example.demo.entities.Account;
import com.example.demo.entities.OperationTypes;
import com.example.demo.entities.Transaction;
import com.example.demo.exceptions.InvalidOperationException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.repositories.TransactionRepository;

@RunWith(MockitoJUnitRunner.class)
public class TransactionServiceTest {

	private static final Long ID = 1L;
	private static final Account ACCOUNT = Account.builder()
			.id( ID )
			.documentNumber( "123.456.789-00" )
			.availableCreditLimit( BigDecimal.valueOf( 300 ) ).build();
	private static final TransactionRequestBodyDTO BODY_PAYMENT =
			new TransactionRequestBodyDTO(ID, 3, new BigDecimal( 250.35 ));
	private static final TransactionRequestBodyDTO BODY_RECEIVING =
			new TransactionRequestBodyDTO(ID, 4, new BigDecimal( 250.35 ));

	@InjectMocks
	private TransactionService service;

	@Mock
	private AccountService accountService;

	@Mock
	private TransactionRepository repository;

	@Test
	public void shouldSaveWhenHaveEnoughLimitWhenPaying(){
		service.save( BODY_PAYMENT );

		verify( repository ).save( any( Transaction.class ) );
	}

	@Test
	public void shouldSaveWithoutCreditAnalyzeWhenReceiving(){

		service.save( BODY_RECEIVING );

		verify( repository ).save( any( Transaction.class ) );
	}

	@Test(expected = InvalidOperationException.class)
	public void shouldNotSaveWhenPayingAndHaveNoLimit(){
		TransactionRequestBodyDTO body =
				new TransactionRequestBodyDTO(ID, 3, new BigDecimal( 500.35 ));
		given( accountService.updateCreditLimitByTransaction( anyLong(), any(), any(OperationTypes.class) ) ).willThrow( new InvalidOperationException());

		try {
			service.save( body );
		} catch (Exception e) {
			verify( repository, never() ).save( any( Transaction.class ) );
			throw e;
		}
	}

	@Test(expected = NotFoundException.class)
	public void saveWithAnInvalidAccount() {
		given( accountService.updateCreditLimitByTransaction( anyLong(), any(), any(OperationTypes.class) ) ).willThrow( NotFoundException.class );
		try {
			service.save( BODY_PAYMENT );
		} catch (Exception e) {
			verify( repository, never() ).save( any( Transaction.class ) );
			throw e;
		}
	}
}
