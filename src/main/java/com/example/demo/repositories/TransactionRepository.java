package com.example.demo.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entities.Transaction;

public interface TransactionRepository extends CrudRepository<Transaction, String> {
}
