package com.challenge.alura.convesor;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;
import java.util.concurrent.Future;

public class ConnectApi {

    private Future<HttpResponse<String>> response;
    private Double conversionRate;
    private final Map<String, String> moneda_isoCode = Map.of("Dólar", "USD", "Euro", "EUR", "Peso Mexicano", "MXN", "Peso Colombiano", "COP", "Peso Argentino",
            "ARS", "Libra Esterlina", "GBP", "Yen Japones", "JPY", "Yuan Chino", "CNY");

    public Future<HttpResponse<String>> getApiResponse(String monedaDePartida, String monedaDeDestino) {

        System.out.println("Moneda de partida: " + monedaDePartida + " Moneda de destino: " + monedaDeDestino);
        String url_str = "https://v6.exchangerate-api.com/v6/eb8f29e31a92e860b1454f9a/pair/";
        System.out.println("URL: " + url_str + moneda_isoCode.get(monedaDePartida) + "/" + moneda_isoCode.get(monedaDeDestino));
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url_str + moneda_isoCode.get(monedaDePartida) + "/" + moneda_isoCode.get(monedaDeDestino)))
                    .build();


            response = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());

            return response;

        } catch (NumberFormatException e) {
            System.out.println("Ocurrio un error al convertir el dato");
            System.out.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Error en la URI, verifique la direccion");


        }
        return null;
    }



}
