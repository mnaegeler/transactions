package com.example.demo.interactions;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Account;
import com.example.demo.entities.OperationTypes;
import com.example.demo.exceptions.DuplicatedAccountException;
import com.example.demo.exceptions.InvalidOperationException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.repositories.AccountRepository;

@Service
public class AccountService {

	@Autowired
	private AccountRepository repository;

	public Account save(Account account) {
		if (repository.alreadyExists( account.getDocumentNumber() ) )
			throw new DuplicatedAccountException( account.getDocumentNumber());

		if(account.getAvailableCreditLimit() == null)
			account.setAvailableCreditLimit( BigDecimal.valueOf( 100 ) );
		return repository.save( account );
	}

	public Account update(Long id, Account account) {
		Account accountEntity = find( id ).orElseThrow( NotFoundException::new );
		account.setId( accountEntity.getId() );
		return repository.save( account );
	}

	public Optional<Account> find(Long id) {
		return repository.findById( id );
	}

	public Account updateCreditLimitByTransaction(Long accountId, BigDecimal value, OperationTypes operation) {
		Account acc =  find( accountId ).orElseThrow( NotFoundException::new);
		BigDecimal newLimit = OperationTypes.PAGAMENTO.equals( operation ) ?
				acc.getAvailableCreditLimit().add( value ):
				acc.getAvailableCreditLimit().subtract( value );

		if(newLimit.compareTo( BigDecimal.ZERO ) < 0
				&& !OperationTypes.PAGAMENTO.equals( operation ))
			throw new InvalidOperationException();

		acc.setAvailableCreditLimit( newLimit );

		return update( accountId, acc );

	}
}
