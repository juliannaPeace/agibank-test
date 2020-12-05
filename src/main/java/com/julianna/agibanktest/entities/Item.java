package com.julianna.agibanktest.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import com.julianna.agibanktest.interfaces.TipoDado;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class Item implements Serializable, TipoDado{

	private static final long serialVersionUID = 1L;

	private Long id;
	private int quantity;
	private String itens;
	private String vendedor;
	private BigDecimal price;
	
	
	@Override
	public Item gerarEntidade(String id, String itens, String vendedor) {
		
		this.id = Long.parseLong(id);
		this.itens = itens;
		this.vendedor = vendedor;
		
		return this;
	}
}
