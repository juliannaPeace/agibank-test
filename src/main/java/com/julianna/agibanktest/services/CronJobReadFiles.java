package com.julianna.agibanktest.services;

import java.io.File;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.logging.Level;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.julianna.agibanktest.utils.Constantes;

import lombok.extern.java.Log;

@Log
@Service
public class CronJobReadFiles {

	@Autowired
	private ProcessFileService processFileService;

	@Scheduled(cron = "0 0/1 * 1/1 * ?")
	public void readFilesSalesForMinute() {
		try {
			Optional<File> file = Optional.ofNullable(new File(Constantes.HOMEPATH_IN));
			processFileService.fileProcessSalesLote(file);
		} catch (NoSuchElementException ne) {
			log.getLogger(CronJobReadFiles.class.getName()).log(Level.SEVERE, null, ne);
		}
	}

}
