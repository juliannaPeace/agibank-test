package com.julianna.agibanktest.entities;

import java.io.Serializable;

import com.julianna.agibanktest.interfaces.TipoDado;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Cliente implements Serializable, TipoDado {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String cnpj;
	private String name;
	private String businessArea;

	@Override
	public Cliente gerarEntidade(String cnpj, String name, String businessArea) {
		this.cnpj = cnpj;
		this.name = name;
		this.businessArea = businessArea;

		return this;
	}

}
