package com.julianna.agibanktest.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FileOut {

	private String name;
	private String absolutePath;
	private String context;
	
}
