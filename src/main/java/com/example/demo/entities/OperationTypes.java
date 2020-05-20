package com.example.demo.entities;

import java.util.Arrays;

import lombok.AllArgsConstructor;

import com.example.demo.exceptions.NotFoundException;

@AllArgsConstructor
public enum OperationTypes {
	COMPRA_A_VISTA(1),
	COMPRA_PARCELADA(2),
	SAQUE(3),
	PAGAMENTO(4);

	private int id;

	public static OperationTypes getByValue(int value) {
		return Arrays.stream(values()).filter(op -> value == op.id).findFirst().orElseThrow(
				NotFoundException::new );
	}
}
