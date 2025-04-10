/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daw;

import javax.swing.JOptionPane;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        // Definir el nombre del archivo a guardar, según lo elija el usuario
        JOptionPane.showMessageDialog(null, "Bienvenido al Juego de la Vida", "Bienvenida", JOptionPane.INFORMATION_MESSAGE);
       
        String tamañoStr = "";
        boolean valorValido = false;
        
        
         while (!valorValido) {
            tamañoStr = JOptionPane.showInputDialog("Introduce el tamaño del tablero (máximo " + DimensionesTablero.MAX_DIMENSION + "):");

            // Si el usuario cancela, mostrar mensaje de despedida
            if (tamañoStr == null) {
                JOptionPane.showMessageDialog(null, "Gracias por jugar. ¡Hasta la próxima!", "Despedida", JOptionPane.INFORMATION_MESSAGE);
                return;  // Salir del programa
            }

            // Si el usuario no ingresa ningún valor, mostrar un mensaje y volver a pedir el número
            if (tamañoStr.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "No se ha ingresado ningún valor. Por favor, ingresa un número.", "Error", JOptionPane.ERROR_MESSAGE);
                continue;  // Volver a pedir el número
            }
        
        int tamaño;
        try {
            tamaño = Integer.parseInt(tamañoStr);
            if (!DimensionesTablero.esDimensionValida(tamaño)) {
                JOptionPane.showMessageDialog(null, "Tamaño inválido. Debe ser entre 1 y " + DimensionesTablero.MAX_DIMENSION, "Error", JOptionPane.ERROR_MESSAGE);
                continue;
            }

            ElJuegoDeLaVida juego = new ElJuegoDeLaVida(tamaño);
            juego.iniciarJuego();
            valorValido = true;

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Por favor, introduce un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
         }
    }
}

        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
      /*  
        // guardar archivo
        String rutaArchivo = "C:/home/yoriel/Documentos/" + nombreArchivo.trim() + ".txt";

        try (BufferedWriter fichero = new BufferedWriter(new FileWriter(rutaArchivo))) {
            // Simulación de inicio de juego
            String tamañoStr = JOptionPane.showInputDialog("Introduce el tamaño del tablero (máximo " + DimensionesTablero.MAX_DIMENSION + "):");
            if (tamañoStr == null) {
                return; // Si el usuario cancela
            }
            int tamaño;
            try {
                tamaño = Integer.parseInt(tamañoStr);
                if (!DimensionesTablero.esDimensionValida(tamaño)) {
                    JOptionPane.showMessageDialog(null, "Error: Tamaño invalido. Debe ser entre 1 y " + DimensionesTablero.MAX_DIMENSION, "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
//            } catch (NumberFormatException e) {
//                JOptionPane.showMessageDialog(null, "Por favor, introduce un numero valido.", "Error", JOptionPane.ERROR_MESSAGE);
//                return;
//            }
//        }

        FileWriter fichero = new FileWriter("C:/home/yoriel/Documentos/juegoVida.txt");
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
                    if (tamañoStr == null) {
                        continue; // Si el usuario cancela
                    }
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
            fichero.write(opcion);
        }
    }
}
*/