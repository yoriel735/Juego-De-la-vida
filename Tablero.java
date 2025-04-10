/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daw;

import java.util.Random;
import javax.swing.JOptionPane;

/**
 *
 * @author contrasenainvitado
 */
public class Tablero {

    private boolean[][] tablero;
    private DimensionesTablero dimensiones;

    //Constructor para inicializar el tablero con el tamaño dado por Dimensiones tablero
    public Tablero(DimensionesTablero dimensiones) {
        this.dimensiones = dimensiones;
        int tamaño = dimensiones.obtenerDimension();
        this.tablero = new boolean[tamaño][tamaño];
    }

    public void inicializarAleatorio(int porcentajeVivos) {
        Random aleatorio = new Random();
        int totalCeldas = dimensiones.obtenerDimension() * dimensiones.obtenerDimension();
        int celdasVivas = (totalCeldas * porcentajeVivos) / 100;

        while (celdasVivas > 0) {
            int fila = aleatorio.nextInt(dimensiones.obtenerDimension());
            int columna = aleatorio.nextInt(dimensiones.obtenerDimension());
            if (!tablero[fila][columna]) {
                tablero[fila][columna] = true;
                celdasVivas--;
            }
        }
    }

    public boolean[][] getTablero() {
        return tablero;
    }

    public DimensionesTablero getDimensiones() {
        return dimensiones;
    }
        // Método para obtener la dimensión del tablero
    public int obtenerDimension() {
        return dimensiones.obtenerDimension();  // Esto devuelve el tamaño del tablero
    }

    public void inicializarManual() {

        //cantidad de fichas que estaran vivas
        //Pedirle al usuario que ingrese una cantidad de fichas
        String inicio = JOptionPane.showInputDialog("¿Cuantas casillas quieres poner vivas?");
        int celdasVivas = Integer.parseInt(inicio); //Para pasarlo a int

        for (int i = 0; i < celdasVivas; i++) {

            String fila = JOptionPane.showInputDialog("Añada la fila entre 0 y " + (dimensiones.obtenerDimension() - 1) + "): ");
            int numFila = Integer.parseInt(fila);

            String columna = JOptionPane.showInputDialog("Añada la columna entre 0 y " + (dimensiones.obtenerDimension() - 1) + "): ");
            int numColumna = Integer.parseInt(columna);

            if (numFila >= 0 && numFila < dimensiones.obtenerDimension() && numColumna >= 0 && numColumna < dimensiones.obtenerDimension()) {
                tablero[numFila][numColumna] = true;

            } else {
                JOptionPane.showMessageDialog(null, "La posicion que has introducido esta fuera de los limites, prueba de nuevo");
                i--;

            }

        }
        mostrarTablero();
    }

    public void mostrarTablero() {
        for (int i = 0; i < dimensiones.obtenerDimension(); i++) {
            for (int j = 0; j < dimensiones.obtenerDimension(); j++) {
                System.out.print(tablero[i][j] ? " 1 " : " 0 ");

            }
            System.out.println(" ");

        }
    }

    public int contarCeldasVivas() {
        int contador = 0;
        for (int i = 0; i < dimensiones.obtenerDimension(); i++) {
            for (int j = 0; j < dimensiones.obtenerDimension(); j++) {
                if (tablero[i][j]) {
                    contador++;
                }

            }

        }
        return contador;
    }

    public int contarVecinosVivos(int fila, int columna) {
        int vivos = 0;
        int[] x = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] y = {-1, 0, 1, -1, 1, -1, 0, 1};
        for (int d = 0; d < 8; d++) {
            int nf = fila + x[d], nc = columna + y[d];
            if (nf >= 0 && nf < dimensiones.obtenerDimension() && nc >= 0 && nc < dimensiones.obtenerDimension()) {
                if (tablero[nf][nc]) {
                    vivos++;
                }
            }
        }
        return vivos;
    }

    public void setTablero(boolean[][] nuevoTablero) {
        this.tablero = nuevoTablero;
    }

}
