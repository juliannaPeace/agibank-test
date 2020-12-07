package com.julianna.agibanktest.entities;

import java.io.Serializable;

import com.julianna.agibanktest.interfaces.DataGeneric;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
public class Client implements Serializable, DataGeneric {

	private static final long serialVersionUID = 1L;

	private String cnpj;
	private String name;
	private String businessArea;

	@Override
	public Client generateEntity(String cnpj, String name, String businessArea) {
		this.cnpj = cnpj;
		this.name = name;
		this.businessArea = businessArea;

		return this;
	}

}
