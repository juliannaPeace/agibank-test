package com.julianna.agibanktest;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.julianna.agibanktest.services.ProcessFileService;
import com.julianna.agibanktest.utils.Constantes;

@SpringBootApplication
public class AgibankTestApplication implements CommandLineRunner {
	
	@Autowired
	private ProcessFileService processFileService;

	public static void main(String[] args) {
		SpringApplication.run(AgibankTestApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		createWithNotExistHomePathIn();
		createWithNotExistHomePathOut();

		File file = new File(Constantes.HOMEPATH_IN);
		processFileService.FileUploadLote(file);

	}

	private void createWithNotExistHomePathIn() {
		File file = new File(Constantes.HOMEPATH_IN);
		file.mkdirs();
	}

	private void createWithNotExistHomePathOut() {
		File file = new File(Constantes.HOMEPATH_OUT);
		file.mkdirs();
	}

}
