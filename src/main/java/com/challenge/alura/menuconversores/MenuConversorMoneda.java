package com.challenge.alura.menuconversores;

import javax.swing.*;
import java.util.List;


public class MenuConversorMoneda {
    private static double cantidad;

    private static String monedaDePartida;
    private static String monedaDeDestino;

    public static void mostrarMenuMonedaDePartida(List<String> monedas) {



        JOptionPane optionPane = new JOptionPane();
        optionPane.setSelectionValues(monedas.toArray());
        optionPane.setMessage("Seleccione la moneda de partida:");
        optionPane.createDialog("Conversor de Moneda").setVisible(true);

        monedaDePartida = (String) optionPane.getInputValue();



    }

    public static void mostrarMenuCantidad(String s) {

        while (true) {
            try {
                 cantidad = Double.parseDouble(JOptionPane.showInputDialog(
                        "Ingrese la cantidad de la moneda " + s + " que desea convertir"));
                System.out.println("Cantidad: " + cantidad);
                break;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Debe ingresar un número");
            }catch (NullPointerException e){
                JOptionPane.showMessageDialog(null, "Has cancelado la operación");
                break;
            }

        }

    }

    public static void MostrarMenuMonedaDeDestino(List<String> monedas) {
        do {
            JOptionPane optionPane = new JOptionPane();
            optionPane.setSelectionValues(monedas.toArray());
            optionPane.setMessage("Seleccione la moneda de destino:");
            optionPane.createDialog("Conversor de Moneda").setVisible(true);
            monedaDeDestino = (String) optionPane.getInputValue();
            if (monedaDeDestino.equalsIgnoreCase(monedaDePartida)) {
                JOptionPane.showMessageDialog(null,
                        "La moneda de destino no puede ser la misma que la moneda de partida");
            }
        } while (monedaDeDestino.equalsIgnoreCase(monedaDePartida));

    }




    public static double getCantidad(){
        return cantidad;
    }

    public static String getMonedaDePartida(){
        return monedaDePartida;
    }


    public static String getMonedaDeDestino() {
        return monedaDeDestino;
    }
}

