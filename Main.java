/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daw;
import javax.swing.JOptionPane;

public class Main {
    public static void main(String[] args) {
        boolean continuar = true;
        
        while (continuar) {
            String[] opciones = {"Nueva Partida", "Cargar Partida", "Salir"};
            int opcion = JOptionPane.showOptionDialog(
                null, 
                "Bienvenido al Juego de la Vida\nSeleccione una opción:", 
                "Juego de la Vida", 
                JOptionPane.DEFAULT_OPTION, 
                JOptionPane.INFORMATION_MESSAGE, 
                null, 
                opciones, 
                opciones[0]
            );

            switch (opcion) {
                case 0 -> {
                    // Nueva Partida
                    String tamañoStr = JOptionPane.showInputDialog("Introduce el tamaño del tablero (máximo " + DimensionesTablero.MAX_DIMENSION + "):");
                    if (tamañoStr == null) continue; // Si el usuario cancela
                    try {
                        int tamaño = Integer.parseInt(tamañoStr);
                        if (!DimensionesTablero.esDimensionValida(tamaño)) {
                            JOptionPane.showMessageDialog(null, "Error: Tamaño invalido. Debe ser entre 1 y " + DimensionesTablero.MAX_DIMENSION, "Error", JOptionPane.ERROR_MESSAGE);
                            continue;
                        }
                        ElJuegoDeLaVida juego = new ElJuegoDeLaVida(tamaño);
                        juego.iniciarJuego();
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Por favor, introduce un numero valido.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                case 1 -> // No creado aun
                    JOptionPane.showMessageDialog(null, "Esta funcion aun no ha sido agregada, perdone las molestias", "Información", JOptionPane.INFORMATION_MESSAGE);
                default -> {
                    // Salir
                    JOptionPane.showMessageDialog(null, "Gracias por jugar. ¡Hasta la próxima!", "Adiós", JOptionPane.INFORMATION_MESSAGE);
                    continuar = false;
                }
            }
        }
    }
}