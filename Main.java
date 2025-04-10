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

        String[] opciones = {"Continuar", "Cargar Partida"};
        int opcion = JOptionPane.showOptionDialog(null, "¿Qué te gustaría hacer?", "Seleccionar opción",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]);

        // Verificamos la opción seleccionada
        if (opcion == 0) {  // Continuar
            continuarJuego();  // Llamamos a la función que maneja el inicio del juego
        } else if (opcion == 1) {  // Cargar partida
            cargarPartida();  // Llamamos a la función que maneja la carga de partida
        }
    }

    public static void continuarJuego() {
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

    public static void cargarPartida() {
        // Aquí creamos el objeto de ElJuegoDeLaVida, pero llamamos directamente a cargarPartida
        String directorio = "/home/yoriel/Documentos/pogamacion"; // O el tamaño por defecto o que pida el tamaño
        CargarJuego.JuegoCargado juegoCargado = CargarJuego.cargarJuegoDesdeDirectorio(directorio);
  if (juegoCargado != null) {
        
        int tamañoTablero = juegoCargado.tablero.getDimensiones().obtenerDimension();  // Usamos obtenerDimension() en lugar de getTamano()
        ElJuegoDeLaVida juego = new ElJuegoDeLaVida(tamañoTablero);
        
        
        juego.setGeneracion(juegoCargado.numeroGeneracion);
        juego.setCelulasVivasRondas(juegoCargado.celulasVivasRondas);
        juego.iniciarJuego();
    } else {
        JOptionPane.showMessageDialog(null, "No se pudo cargar la partida.", "Error", JOptionPane.ERROR_MESSAGE);
    }
}
}   