package com.example.demo.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dtos.TransactionDTO;
import com.example.demo.dtos.TransactionRequestBodyDTO;
import com.example.demo.interactions.TransactionService;

@RestController
@RequestMapping(value = "/transactions", produces = MediaType.APPLICATION_JSON_VALUE)
public class TransactionController {

	@Autowired
	private TransactionService service;

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public TransactionDTO create(@Valid @RequestBody TransactionRequestBodyDTO body) {
		return new TransactionDTO( service.save( body ) );
	}

}
