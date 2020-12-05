package com.julianna.agibanktest.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class Item implements Serializable{

	private Long id;
	private int quantity;
	private BigDecimal price;
}
