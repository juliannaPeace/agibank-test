package com.julianna.agibanktest.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import com.julianna.agibanktest.interfaces.TipoDado;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data @NoArgsConstructor
@EqualsAndHashCode(exclude={"cpf", "salary"})
public class Vendedor implements Serializable,TipoDado{

	private static final long serialVersionUID = 1L;
	
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
