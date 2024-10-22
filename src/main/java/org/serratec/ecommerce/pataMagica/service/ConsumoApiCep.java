package org.serratec.ecommerce.pataMagica.service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsumoApiCep {

	public static String obterDados(String cep) {

		URI endereco = URI.create("https://viacep.com.br/ws/" + cep + "/json/");

		HttpRequest request = HttpRequest.newBuilder().uri(endereco).build();

		try {
			HttpResponse<String> response = HttpClient.newHttpClient().send(request,
					HttpResponse.BodyHandlers.ofString());
			String json = response.body();
			return json;
		} catch (Exception e) {
			throw new RuntimeException("Não consegui obter o endereço a partir desse CEP.");
		}
	}
}
