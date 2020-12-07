package com.julianna.agibanktest.services;

import java.io.File;
import java.io.IOException;
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

import com.julianna.agibanktest.entities.Client;
import com.julianna.agibanktest.entities.FileOut;
import com.julianna.agibanktest.entities.Sale;
import com.julianna.agibanktest.entities.SalesMan;
import com.julianna.agibanktest.interfaces.DataGeneric;
import com.julianna.agibanktest.utils.Constantes;
import com.julianna.agibanktest.utils.Util;

@Service
public class ProcessFileService {

	public void fileProcessSalesLote(Optional<File> filesPathIn) {

		List<Client> clients = new ArrayList<Client>();
		List<SalesMan> salesMan = new ArrayList<SalesMan>();
		Set<Sale> sales = new HashSet<Sale>();

		Stream.of(filesPathIn.get().listFiles()).filter(file -> file.getName().endsWith(".dat")).forEach(file -> {

			try (Stream<String> stream = Files
					.lines(Paths.get(filesPathIn.get().getAbsolutePath() + "/" + file.getName()))) {
				stream.map(lines -> lines.split(System.getProperty("line.separator"))).forEach((String[] lines) -> {

					for (int i = 0; i < lines.length; i++) {

						String[] values = lines[i].split("ç");

						Map<String, DataGeneric> mapEntities = Util.getMapEntities();

						DataGeneric dataGeneric = mapEntities.get(values[0]);

						if (dataGeneric == null) {
							continue;
						}

						Object object = Util.gerarEntidades(dataGeneric, values[1], values[2], values[3]);

						if (object instanceof Client) {
							clients.add((Client) object);
						}

						if (object instanceof SalesMan) {
							salesMan.add((SalesMan) object);
						}

						if (object instanceof Sale) {
							sales.add((Sale) object);
						}
					}
				});
			} catch (IOException e) {
				e.printStackTrace();
			}

		});

		generateConsolidatedData(clients, salesMan, sales);
	}

	private void generateConsolidatedData(List<Client> clients, List<SalesMan> salesMan, Set<Sale> sales) {

		if (clients.isEmpty()) {
			return;
		}

		LocalDateTime today = LocalDateTime.now();
		StringBuilder stringBuilder = new StringBuilder();

		getAmountClient(clients, stringBuilder);
		getAmountSalesMan(salesMan, stringBuilder);
		getMostExpensiveSale(sales, stringBuilder);
		getWorseSalesMan(sales, stringBuilder);

		Util.createFile(stringBuilder, Constantes.HOMEPATH_OUT,
				"file_sales_" + today.format(Util.flatFileName()) + ".done.dat");
	}

	private void getAmountClient(List<Client> clients, StringBuilder stringBuilder) {
		stringBuilder.append("Quantidade de Clientes :");
		stringBuilder.append(clients.size());
		stringBuilder.append(System.getProperty("line.separator"));
	}

	private void getAmountSalesMan(List<SalesMan> salesMan, StringBuilder stringBuilder) {

		stringBuilder.append("Quantidade de Vendedores :");
		stringBuilder.append(salesMan.size());
		stringBuilder.append(System.getProperty("line.separator"));
	}

	private void getMostExpensiveSale(Set<Sale> sales, StringBuilder stringBuilder) {

		Sale bigSale = sales.stream().max(Comparator.comparing(Sale::getTotalSale))
				.orElseThrow(NoSuchElementException::new);

		stringBuilder.append("Id venda mais cara :");
		stringBuilder.append(bigSale.getSalesId());
		stringBuilder.append(", Valor da venda :");
		stringBuilder.append(bigSale.getTotalSale());
		stringBuilder.append(System.getProperty("line.separator"));
	}

	private void getWorseSalesMan(Set<Sale> sales, StringBuilder stringBuilder) {

		Sale worstSale = sales.stream().min(Comparator.comparing(Sale::getTotalSale))
				.orElseThrow(NoSuchElementException::new);

		stringBuilder.append("O pior Vendedor :");
		stringBuilder.append(worstSale.getSalesMan());
		stringBuilder.append(", Valor da venda :");
		stringBuilder.append(worstSale.getTotalSale());
		stringBuilder.append(System.getProperty("line.separator"));
	}

	public List<FileOut> listAllFilesPathOut(Optional<File> filesPathOut) {

		return Stream.of(filesPathOut.get().listFiles()).map(file -> {
			try {
				return new FileOut(file.getName(), file.getAbsolutePath(),
						new String(Files.readAllBytes(file.toPath())));
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}).collect(Collectors.toList());

	}
}
