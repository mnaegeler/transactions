package com.example.demo.interactions;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dtos.TransactionRequestBodyDTO;
import com.example.demo.entities.OperationTypes;
import com.example.demo.entities.Transaction;
import com.example.demo.repositories.TransactionRepository;

@Service
public class TransactionService {

	@Autowired
	private TransactionRepository repository;

	@Autowired
	private AccountService accountService;

	public Transaction save(TransactionRequestBodyDTO body) {
		OperationTypes operation = OperationTypes.getByValue( body.getOperationTypeId() );
		Transaction transaction = Transaction.builder()
				.operation( operation )
				.amount( validateAmountByOperation(operation, body.getAmount()) )
				.eventDate( new Date(  ) )
				.build();

		transaction.setAccount(
				accountService.updateCreditLimitByTransaction( body.getAccountId(), body.getAmount(), transaction.getOperation() ) );

		return repository.save( transaction );
	}

	private BigDecimal validateAmountByOperation(OperationTypes operation, BigDecimal amount) {
		return OperationTypes.PAGAMENTO.equals( operation ) ? amount : ( amount.multiply(  new BigDecimal( -1 ) ) );
	}
}
