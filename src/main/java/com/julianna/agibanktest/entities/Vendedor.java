package com.julianna.agibanktest.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import com.julianna.agibanktest.interfaces.TipoDado;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
@EqualsAndHashCode(exclude={"id", "cpf", "salary"})
public class Vendedor implements Serializable,TipoDado{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String cpf;
	private String name;
	private BigDecimal salary;
	
	@Override
	public Vendedor gerarEntidade(String cpf, String name, String salary) {
		
		this.cpf = cpf;
		this.name = name;
		this.salary = new BigDecimal(salary);
		
		return this;
	}
	
}
