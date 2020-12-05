package com.julianna.agibanktest.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.julianna.agibanktest.utils.Constantes;

@Controller
public class UploadFileController {

	@GetMapping("/")
	public String index() {
		return "upload";
	}

	@PostMapping("/upload")
	public String multipleFileUpload(@RequestParam("files") MultipartFile[] files,
			RedirectAttributes redirectAttributes) {

		StringBuilder filesUpload = new StringBuilder();
		
		if (Arrays.asList(files).isEmpty()) {
			redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
			return "redirect:uploadStatus";
		}

		Arrays.asList(files).stream().forEach(file -> {
			try {

				byte[] bytes = file.getBytes();

				Path path = Paths.get(Constantes.HOMEPATH_IN + file.getOriginalFilename());

				Files.write(path, bytes);
				
				filesUpload.append(file.getOriginalFilename());
				filesUpload.append(System.getProperty("line.separator"));

			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		
		redirectAttributes.addFlashAttribute("message",
				"You successfully uploaded '" + filesUpload.toString() + "'");

		return "redirect:/uploadStatus";
	}

	@GetMapping("/uploadStatus")
	public String uploadStatus() {
		return "uploadStatus";
	}
}
