package com.julianna.agibanktest.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.julianna.agibanktest.entities.FileOut;
import com.julianna.agibanktest.services.ProcessFileService;
import com.julianna.agibanktest.utils.Constantes;

@Controller
public class UploadFileController {

	@Autowired
	private ProcessFileService processFileService;

	@GetMapping("/")
	public String index(Model model) {

		listAllFilesPathOut(model);

		return "upload";
	}

	@PostMapping("/upload")
	public String multipleFileUpload(@RequestParam("files") MultipartFile[] files,
			RedirectAttributes redirectAttributes) {

		StringBuilder filesUpload = new StringBuilder();

		if (Arrays.asList(files).isEmpty()) {
			redirectAttributes.addFlashAttribute("message", "Por favor selecione arquivo para upload");
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
				"Upload de arquivo feito com sucesso '" + filesUpload.toString() + "'");

		return "redirect:/uploadStatus";
	}

	private void listAllFilesPathOut(Model model) {
		Optional<File> file = Optional.ofNullable(new File(Constantes.HOMEPATH_OUT));

		List<FileOut> listFileOut = processFileService.listAllFilesPathOut(file);

		model.addAttribute("files", listFileOut);
	}

	@GetMapping("/uploadStatus")
	public String uploadStatus() {
		return "uploadStatus";
	}
}
