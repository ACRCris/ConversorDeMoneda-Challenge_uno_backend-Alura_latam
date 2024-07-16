package com.challenge.alura.pricipal;

import com.challenge.alura.convesor.ConnectApi;
import com.challenge.alura.convesor.ConvertirMoneda;
import com.challenge.alura.menuconversores.MenuConversorMoneda;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.annotations.JsonAdapter;

import javax.swing.*;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class Menu {

    private static List<String> monedas = List.of(
            "1. Dólar", "2. Euro", "3. Peso Mexicano", "4. Peso Colombiano", "5. Peso Argentino",
            "6. Libra Esterlina", "7. Yen Japonés", "8. Yuan Chino");



    public static void mostrarMenu() {

        JOptionPane optionPane = new JOptionPane();
        optionPane.setSelectionValues(
                new String[]{"1. Conversor de Moneda", "2. Conversor de Temperatura"}
        );
        optionPane.setMessage("Seleccione una opción:");
        optionPane.createDialog("Menu").setVisible(true);

        String opcion = (String) optionPane.getInputValue();

        if (opcion.equals("1. Conversor de Moneda")) {
            MenuConversorMoneda.mostrarMenuMonedaDePartida(monedas);
            String monedaDePartida  = MenuConversorMoneda.getMonedaDePartida();
            monedas.stream()
                    .map(s ->s.toLowerCase().split("\\. ")[1])
                    .filter(s -> s.equals(monedaDePartida))
                    .forEach(MenuConversorMoneda::mostrarMenuCantidad);


            MenuConversorMoneda.mostrarMenuCantidad(monedaDePartida);

            double cantidad = MenuConversorMoneda.getCantidad();


            MenuConversorMoneda.MostrarMenuMonedaDeDestino(monedas);

            String monedaDeDestino = MenuConversorMoneda.getMonedaDeDestino();

            ConnectApi connectApi = new ConnectApi();
            Future<HttpResponse<String>> responseFuture = connectApi.getApiResponse(monedaDePartida, monedaDeDestino);


            try {
                HttpResponse<String> response = responseFuture.get();

                System.out.println(response.body());

                JsonObject jsonObject = JsonParser.parseString(response.body()).getAsJsonObject();
                double conversionRate = jsonObject.get("conversion_rate").getAsDouble();
                System.out.println("Conversion rate: " + conversionRate);

                MenuConversorMoneda.mostrarResultadoDeConversion(ConvertirMoneda.convertir(cantidad, conversionRate));

            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }

        } else if (opcion.equals("2. Conversor de Temperatura")) {
           // ConversorTemperatura.mostrarConversorTemperatura();
        } else {
            JOptionPane.showMessageDialog(null, "Opción no válida");
        }


    }

}
