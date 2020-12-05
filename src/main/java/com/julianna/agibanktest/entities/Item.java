package com.julianna.agibanktest.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import com.julianna.agibanktest.interfaces.TipoDado;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class Item implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;
	private String itens;
	private String vendedor;
	private Venda venda;
	
}
