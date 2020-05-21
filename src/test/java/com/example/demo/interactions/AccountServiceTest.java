package com.example.demo.interactions;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.example.demo.entities.Account;
import com.example.demo.exceptions.DuplicatedAccountException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.repositories.AccountRepository;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {

	private static final String DOCUMENT = "123.456.789-00";
	private static final Long ID = 10L;
	private static final Account ACCOUNT = Account.builder().id( ID ).documentNumber( DOCUMENT ).build();

	@InjectMocks
	private AccountService service;

	@Mock
	private AccountRepository repository;

	@Test
	public void create() {
		given( repository.alreadyExists( DOCUMENT ) ).willReturn( false );
		service.save( ACCOUNT );

		verify( repository ).save( ACCOUNT );
	}

	@Test(expected = DuplicatedAccountException.class)
	public void createDuplicated() {
		given( repository.alreadyExists( DOCUMENT ) ).willReturn( true );

		try {
			service.save( ACCOUNT );
		} catch (Exception e) {
			verify( repository, never() ).save( ACCOUNT );
			throw e;
		}
	}

	@Test
	public void update() {
		Account accountEdited = Account.builder().documentNumber( "789.456.123-00").build();
		given(repository.findById( ID ) ).willReturn( Optional.of( new Account( ID, DOCUMENT ) ) );

		service.update( ID, accountEdited );

		assertEquals( ID, accountEdited.getId() );
		verify( repository ).save( accountEdited );
	}

	@Test(expected = NotFoundException.class)
	public void updateNotFound() {
		try {
			service.update( ID, new Account() );
		} catch (Exception e) {
			verify( repository, never() ).save( any( Account.class ) );
			throw e;
		}
	}

	@Test
	public void find() {
		service.find( ID );
		verify( repository ).findById( ID );
	}
}
