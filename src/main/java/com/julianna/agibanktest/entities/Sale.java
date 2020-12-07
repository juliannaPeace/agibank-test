package com.julianna.agibanktest.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.julianna.agibanktest.interfaces.DataGeneric;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude={"listItens", "salesMan","totalSale"})
public class Sale implements Serializable, DataGeneric {

	private static final long serialVersionUID = 1L;

	private String salesId;

	private String listItens;

	private String salesMan;

	private BigDecimal totalSale = new BigDecimal(0);

	@Override
	public Sale generateEntity(String salesId, String listItens, String salesMan) {

		this.salesId = salesId;
		this.listItens = listItens.replace("[", "").replaceAll("]", "");
		this.salesMan = salesMan;
		setTotalSale(this.listItens);

		return this;
	}

	public void setTotalSale(String listItens) {

		String[] itensVenda = listItens.split(",");

		for (int i = 0; i < itensVenda.length; i++) {
			
			String[] quantifys = itensVenda[i].split("-");
			
			BigDecimal valueItemSale = new BigDecimal(quantifys[2]).multiply(new BigDecimal(quantifys[1])); 
			
			totalSale = totalSale.add(valueItemSale);
		}
	}

}
