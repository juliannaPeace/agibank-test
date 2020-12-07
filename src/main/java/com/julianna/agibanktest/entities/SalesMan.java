package com.julianna.agibanktest.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import com.julianna.agibanktest.interfaces.DataGeneric;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data @NoArgsConstructor
@EqualsAndHashCode(exclude={"cpf", "salary"})
public class SalesMan implements Serializable,DataGeneric{

	private static final long serialVersionUID = 1L;
	
	private String cpf;
	private String name;
	private BigDecimal salary;
	
	@Override
	public SalesMan generateEntity(String cpf, String name, String salary) {
		
		this.cpf = cpf;
		this.name = name;
		this.salary = new BigDecimal(salary);
		
		return this;
	}
	
}
