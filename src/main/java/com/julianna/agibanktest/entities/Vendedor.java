package com.julianna.agibanktest.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
@EqualsAndHashCode(exclude={"id", "cpf", "salary"})
public class Vendedor implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String cpf;
	private String name;
	private BigDecimal salary;

}
