package com.example.demo.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.entities.Account;

public interface AccountRepository extends CrudRepository<Account, Long> {

	@Query(
			"select (count(s) > 0) from Account a where a.documentNumber = :documentNumber")
	boolean alreadyExists(String documentNumber);
}
