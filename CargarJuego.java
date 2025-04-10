/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daw;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author yoriel
 */

public class CargarJuego {

    // Clase interna que representa el estado de un juego cargado
    public static class JuegoCargado {

        public Tablero tablero;
        public int numeroGeneracion;
        public List<Integer> celulasVivasRondas;

        // Constructor
        public JuegoCargado(Tablero tablero, int numeroGeneracion, List<Integer> celulasVivasRondas) {
            this.tablero = tablero;
            this.numeroGeneracion = numeroGeneracion;
            this.celulasVivasRondas = celulasVivasRondas;
        }
    }

    // Método que maneja la carga del juego desde un directorio
    public static JuegoCargado cargarJuegoDesdeDirectorio(String directorio) {
        // Verificamos si el directorio existe
        File dir = new File(directorio);

        if (!dir.exists() || !dir.isDirectory()) {
            JOptionPane.showMessageDialog(null, "El directorio de partidas no existe o no es un directorio válido.", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        // Listamos los archivos en el directorio
        String[] archivos = dir.list((d, name) -> name.endsWith(".txt"));  // Filtramos solo los archivos .txt

        if (archivos == null || archivos.length == 0) {
            JOptionPane.showMessageDialog(null, "No hay archivos de partida en el directorio.", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        // Mostramos los archivos disponibles para cargar
        String archivoSeleccionado = (String) JOptionPane.showInputDialog(
                null,
                "Selecciona un archivo para cargar:",
                "Seleccionar archivo de partida",
                JOptionPane.PLAIN_MESSAGE,
                null,
                archivos,
                archivos[0] // Valor por defecto: el primer archivo
        );

        // Si el usuario cancela, no hacemos nada
        if (archivoSeleccionado == null) {
            return null;
        }

        // Crear la ruta completa con el directorio y el archivo seleccionado
        String rutaArchivo = directorio + "/" + archivoSeleccionado;

        // Intentamos cargar el archivo seleccionado
        try {
            return cargarPartida(rutaArchivo);  // Llamamos a cargarPartida con la ruta completa
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar la partida: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    // Método para cargar la partida desde un archivo dado
    public static JuegoCargado cargarPartida(String nombreArchivo) {
        File archivo = new File(nombreArchivo);

        if (!archivo.exists()) {
            JOptionPane.showMessageDialog(null, "El archivo no existe.", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            // Leer dimensiones
            String[] dimensiones = reader.readLine().split(" ");
            int n = Integer.parseInt(dimensiones[0]);

            // Leer numero de generacion
            int numeroGeneracion = Integer.parseInt(reader.readLine());

            // Leer estado de la matriz
            boolean[][] matriz = new boolean[n][n];  // Usamos un array de booleanos para el tablero
            for (int i = 0; i < n; i++) {
                String[] fila = reader.readLine().split(" ");
                for (int j = 0; j < n; j++) {
                    matriz[i][j] = fila[j].equals("1");  // Si es 1, está viva
                }
            }

            // Leer lista de células vivas por ronda
            List<Integer> celulasVivasRondas = new ArrayList<>();
            String[] celulasVivas = reader.readLine().split(" ");
            for (String celula : celulasVivas) {
                celulasVivasRondas.add(Integer.parseInt(celula));
            }

            // Crear el tablero y copiar el estado de las células
            Tablero tablero = new Tablero(new DimensionesTablero(n));  // Crea un tablero con tamaño n
            tablero.setTablero(matriz);  // Establece el estado del tablero con los datos leídos

            // Devolver el objeto JuegoCargado con todos los datos actualizados
            return new JuegoCargado(tablero, numeroGeneracion, celulasVivasRondas);

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar la partida: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error al leer el archivo. Asegúrate de que el archivo esté en el formato correcto.", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

}
