package com.challenge.alura;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CalcularConversion {

    private String direccion= "http://www.omdbapi.com/?t=The%20Matrix&apikey=d4d0bf92";
    CalcularConversion() {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(direccion))
                    .build();

            HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());

            System.out.println(response.body());

            String json = response.body();

            System.out.println(json);


            //Titulo miTitulo = gson.fromJson(json, Titulo.class);
         /*   TituloOmdb miTituloOmdb = gson.fromJson(json, TituloOmdb.class);
            System.out.println(miTituloOmdb);

            Titulo miTitulo = new Titulo(miTituloOmdb);
            System.out.println("Titulo ya convertido: " + miTitulo);

            listaTitulos.add(miTitulo);*/



        } catch (NumberFormatException e) {
            System.out.println("Ocurrio un error al convertir el dato");
            System.out.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Error en la URI, verifique la direccion");
        }/* catch (ErrorEnConversionDeDuracionException e) {
            System.out.println(e.getMensaje());
        }*/ catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
