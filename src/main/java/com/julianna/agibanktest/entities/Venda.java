package com.julianna.agibanktest.entities;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class Venda implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private List<Item> itens;
	
	private Vendedor vendedor;

}
