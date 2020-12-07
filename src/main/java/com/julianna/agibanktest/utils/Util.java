package com.julianna.agibanktest.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import com.julianna.agibanktest.entities.Client;
import com.julianna.agibanktest.entities.Sale;
import com.julianna.agibanktest.entities.SalesMan;
import com.julianna.agibanktest.interfaces.DataGeneric;

public class Util {
	
	
	public static Map<String, DataGeneric> getMapEntities() {

		Map<String, DataGeneric> mapEntities = new HashMap<>();

		mapEntities.put("001", new SalesMan());
		mapEntities.put("002", new Client());
		mapEntities.put("003", new Sale());

		return mapEntities;
	}

	public static Object gerarEntidades(DataGeneric tipoDado, String value1, String value2, String value3) {
		return tipoDado.generateEntity(value1, value2, value3);
	}

	public static void createFile(StringBuilder stringBuilder, String path, String fileName) {

		FileWriter file = null;
		try {
			file = new FileWriter(path + fileName);
			file.write(stringBuilder.toString());
			file.flush();
			file.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public static DateTimeFormatter flatFileName() {
		
		return DateTimeFormatter.ofPattern(("yyyy-MM-dd-HH_mm_ss"));
	}
}
