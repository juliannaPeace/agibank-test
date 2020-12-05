package com.julianna.agibanktest.entities;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String cnpj;
	private String name;
	private String businessArea;

}
