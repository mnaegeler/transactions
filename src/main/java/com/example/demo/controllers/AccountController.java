package com.example.demo.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dtos.AccountDTO;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.interactions.AccountService;

@RestController
@RequestMapping(value = "/accounts", produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountController {

	@Autowired
	private AccountService service;

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public AccountDTO create(@Valid @RequestBody AccountDTO accountDTO) {
		return new AccountDTO( service.save( accountDTO.toEntity() ) );
	}

	@GetMapping(value = "/{id}")
	public AccountDTO get(@PathVariable Long id) {
		return service.find( id )
				.map( AccountDTO::new )
				.orElseThrow( NotFoundException::new );
	}

	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public AccountDTO update(@PathVariable Long id, @Valid @RequestBody AccountDTO accountDto) {
		return new AccountDTO( service.update( id, accountDto.toEntity() ) );
	}
}
