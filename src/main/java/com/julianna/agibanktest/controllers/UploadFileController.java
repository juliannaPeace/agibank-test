package com.julianna.agibanktest.controllers;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.julianna.agibanktest.entities.Cliente;
import com.julianna.agibanktest.entities.Item;
import com.julianna.agibanktest.entities.Vendedor;
import com.julianna.agibanktest.interfaces.TipoDado;
import com.julianna.agibanktest.utils.Constantes;
import com.julianna.agibanktest.utils.Util;

@Controller
public class UploadFileController {

	@GetMapping("/")
	public String index() {
		return "upload";
	}

	@PostMapping("/upload")
	public String singleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {

		if (file.isEmpty()) {
			redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
			return "redirect:uploadStatus";
		}

		try {

			byte[] bytes = file.getBytes();

			Path path = Paths.get(Constantes.HOMEPATH_IN + file.getOriginalFilename());

			BufferedReader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(bytes)));

			String linha = null;

			List<Cliente>  listaClientes = new ArrayList<Cliente>();
			List<Vendedor> listaVendedores = new ArrayList<Vendedor>();
			List<Item> listaItens = new ArrayList<Item>();

			while ((linha = reader.readLine()) != null) {
				String[] lines = linha.split(System.getProperty("line.separator"));

				for (int i = 0; i < lines.length; i++) {

					String[] values = lines[i].split("ç");

					Map<String, TipoDado> mapEntities = Util.getMapEntities();

					TipoDado tipoDado = mapEntities.get(values[0]);

					if (tipoDado == null) {
						continue;
					}

					Object object = Util.gerarEntidades(tipoDado, values[1], values[2], values[3]);

					if(object instanceof Cliente) {
						listaClientes.add((Cliente) object);
					}
					
					if(object instanceof Vendedor) {
						listaVendedores.add((Vendedor) object);
					}
					
					if(object instanceof Item) {
						listaItens.add((Item) object);
					}
				}

			}

			Files.write(path, bytes);

			redirectAttributes.addFlashAttribute("message",
					"You successfully uploaded '" + file.getOriginalFilename() + "'");

		} catch (IOException e) {
			e.printStackTrace();
		}

		return "redirect:/uploadStatus";
	}

	@GetMapping("/uploadStatus")
	public String uploadStatus() {
		return "uploadStatus";
	}
}
