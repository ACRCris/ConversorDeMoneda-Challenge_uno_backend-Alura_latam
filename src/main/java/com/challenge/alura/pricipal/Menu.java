package com.challenge.alura.pricipal;

import com.challenge.alura.menuconversores.MenuConversorMoneda;

import javax.swing.*;
import java.util.List;

public class Menu {

    private static List<String> monedas = List.of(
            "1. Dólar", "2. Euro", "3. Peso Mexicano", "4. Peso Colombiano", "5. Peso Argentino",
            "6. Libra Esterlina", "7. Yen Japonés", "8. Yuan Chino", "9. Bitcoin");



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
            String seleccion  = MenuConversorMoneda.getMonedaDePartida();
            monedas.stream()
                    .filter(s -> s.equals(seleccion))
                    .map(s ->s.toLowerCase().split(" ")[1])
                    .forEach(MenuConversorMoneda::mostrarMenuCantidad);

            double cantidad = MenuConversorMoneda.getCantidad();

            MenuConversorMoneda.MostrarMenuMonedaDeDestino(monedas);

            String monedaDeDestino = MenuConversorMoneda.getMonedaDeDestino();


        } else if (opcion.equals("2. Conversor de Temperatura")) {
           // ConversorTemperatura.mostrarConversorTemperatura();
        } else {
            JOptionPane.showMessageDialog(null, "Opción no válida");
        }


    }

}
