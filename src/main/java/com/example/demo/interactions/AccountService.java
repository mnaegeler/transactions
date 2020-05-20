package com.example.demo.interactions;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Account;
import com.example.demo.exceptions.DuplicatedAccountException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.repositories.AccountRepository;

@Service
public class AccountService {

	@Autowired
	private AccountRepository repository;

	public Account save(Account account) {
		if (repository.alreadyExists( account.getDocumentNumber() ) )
			throw new DuplicatedAccountException( account.getDocumentNumber());

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
}
