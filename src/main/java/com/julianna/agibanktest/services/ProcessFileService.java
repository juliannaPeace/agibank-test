package com.julianna.agibanktest.services;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.julianna.agibanktest.entities.Cliente;
import com.julianna.agibanktest.entities.FileOut;
import com.julianna.agibanktest.entities.Venda;
import com.julianna.agibanktest.entities.Vendedor;
import com.julianna.agibanktest.interfaces.TipoDado;
import com.julianna.agibanktest.utils.Constantes;
import com.julianna.agibanktest.utils.Util;

@Service
public class ProcessFileService {

	public void fileProcessSalesLote(Optional<File> filesPathIn) {

		List<Cliente> clientes = new ArrayList<Cliente>();
		List<Vendedor> vendedores = new ArrayList<Vendedor>();
		Set<Venda> vendas = new HashSet<Venda>();

		Stream.of(filesPathIn.get().listFiles()).filter(file -> file.getName().endsWith(".dat")).forEach(file -> {

			try {

				byte[] bytes = Files
						.readAllBytes(Paths.get(filesPathIn.get().getAbsolutePath() + "/" + file.getName()));

				BufferedReader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(bytes)));

				String linha = null;

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

						if (object instanceof Cliente) {
							clientes.add((Cliente) object);
						}

						if (object instanceof Vendedor) {
							vendedores.add((Vendedor) object);
						}

						if (object instanceof Venda) {
							vendas.add((Venda) object);
						}
					}

				}

			} catch (IOException e) {
				e.printStackTrace();
			}

		});

		gerarDadosConsolidados(clientes, vendedores, vendas);
	}

	private void gerarDadosConsolidados(List<Cliente> clientes, List<Vendedor> vendedores, Set<Venda> vendas) {

		if (clientes.isEmpty()) {
			return;
		}

		LocalDateTime today = LocalDateTime.now();
		StringBuilder stringBuilder = new StringBuilder();

		getQuantidadeCliente(clientes, stringBuilder);
		getQuantidadeVendedor(vendedores, stringBuilder);
		getVendaMaisCara(vendas, stringBuilder);
		getPiorVendedor(vendas, stringBuilder);

		Util.createFile(stringBuilder, Constantes.HOMEPATH_OUT,
				"file_sales_" + today.format(Util.flatFileName()) + ".done.dat");

		// vendas.forEach(venda -> {
//			stringBuilder.append("Id Venda:");
//			stringBuilder.append(venda.getSalesId());
//			stringBuilder.append(", ");
//			stringBuilder.append(venda.getSalesMan());
//			stringBuilder.append(", ");
//
//			stringBuilder.append("Total:");
//			stringBuilder.append(venda.getTotalSale());
//			stringBuilder.append(System.getProperty("line.separator"));
//		});

	}

	private void getQuantidadeCliente(List<Cliente> clientes, StringBuilder stringBuilder) {
		stringBuilder.append("Quantidade de Clientes :");
		stringBuilder.append(clientes.size());
		stringBuilder.append(System.getProperty("line.separator"));
	}

	private void getQuantidadeVendedor(List<Vendedor> vendedores, StringBuilder stringBuilder) {

		stringBuilder.append("Quantidade de Vendedores :");
		stringBuilder.append(vendedores.size());
		stringBuilder.append(System.getProperty("line.separator"));
	}

	private void getVendaMaisCara(Set<Venda> vendas, StringBuilder stringBuilder) {

		Venda bigSale = vendas.stream().max(Comparator.comparing(Venda::getTotalSale))
				.orElseThrow(NoSuchElementException::new);

		stringBuilder.append("Id venda mais cara :");
		stringBuilder.append(bigSale.getSalesId());
		stringBuilder.append(", Valor da venda :");
		stringBuilder.append(bigSale.getTotalSale());
		stringBuilder.append(System.getProperty("line.separator"));
	}

	private void getPiorVendedor(Set<Venda> vendas, StringBuilder stringBuilder) {

		Venda worstSale = vendas.stream().min(Comparator.comparing(Venda::getTotalSale))
				.orElseThrow(NoSuchElementException::new);

		stringBuilder.append("O pior Vendedor :");
		stringBuilder.append(worstSale.getSalesMan());
		stringBuilder.append(", Valor da venda :");
		stringBuilder.append(worstSale.getTotalSale());
		stringBuilder.append(System.getProperty("line.separator"));
	}

	public List<FileOut> listAllFilesPathOut(Optional<File> filesPathOut) {

		return Stream.of(filesPathOut.get().listFiles())
				.map(file -> new FileOut(file.getName(), file.getAbsolutePath())).collect(Collectors.toList());

	}
}
