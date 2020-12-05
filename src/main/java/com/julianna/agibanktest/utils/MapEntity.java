package com.julianna.agibanktest.utils;

import java.util.HashMap;
import java.util.Map;

import com.julianna.agibanktest.entities.Cliente;
import com.julianna.agibanktest.entities.Item;
import com.julianna.agibanktest.entities.Vendedor;
import com.julianna.agibanktest.interfaces.TipoDado;

public abstract class MapEntity {

	public static Map<String, TipoDado> getMapEntities() {

		Map<String, TipoDado> mapEntities = new HashMap<>();

		mapEntities.put("001", new Vendedor());
		mapEntities.put("002", new Cliente());
		mapEntities.put("003", new Item());

		return mapEntities;
	}

	public static Object gerarEntidades(TipoDado tipoDado, String value1, String value2, String value3) {
		return tipoDado.gerarEntidade(value1, value2, value3);
	}
}
