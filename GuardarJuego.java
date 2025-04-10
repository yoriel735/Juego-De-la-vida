/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daw;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author yoriel
 * 
 * 
 */


public class GuardarJuego {
    public static void guardarEstadoJuego(Tablero tablero, int numeroGeneracion, List<Integer> celulasVivasRondas) {
        String nombreArchivo = JOptionPane.showInputDialog("Introduce el nombre del archivo para guardar (sin extensión):");
        if (nombreArchivo == null || nombreArchivo.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "No se ha proporcionado un nombre válido.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Esta línea abre un archivo de texto para escritura
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo + ".txt"))) {
            int n = tablero.obtenerDimension();  // Suponiendo que tienes un método para obtener la dimensión del tablero

            // Guardar dimensiones del tablero
            writer.write(n + " " + n);
            writer.newLine();

            // Guardar numero de generacion
            writer.write(String.valueOf(numeroGeneracion));
            writer.newLine();

            // Guardar estado de la matriz (asumiendo que el tablero es una matriz de booleanos)
            boolean[][] matriz = tablero.getTablero();  // Obtienes la matriz de celdas

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    writer.write(matriz[i][j] ? "1 " : "0 ");  // Guardamos 1 si está viva, 0 si está muerta
                }
                writer.newLine();
            }

            // Guardar células vivas por generación
            for (int celulasVivas : celulasVivasRondas) {
                writer.write(celulasVivas + " ");
            }
            writer.newLine();

            // Asegura que todo lo escrito se guarde en el archivo
            writer.flush();
            JOptionPane.showMessageDialog(null, "Partida guardada exitosamente en " + nombreArchivo + ".txt", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar la partida: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}