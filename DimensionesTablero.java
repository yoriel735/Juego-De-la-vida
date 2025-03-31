/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daw;

/**
 *
 * @author contrasenainvitado
 */
public class DimensionesTablero {
    
      public static final int MAX_DIMENSION = 25;  // Dimensión que admite la matriz como maximo
    private int N;  // Tamaño que tendra la matriz (NxN)

    // Constructor que inicializa el tamaño N
    public DimensionesTablero(int N) {
        if (N > MAX_DIMENSION) {
            throw new IllegalArgumentException("El tamaño máximo permitido es " + MAX_DIMENSION);
        }
        this.N = N;
    }

    // Método para obtener el tamaño de la matriz (N)
    public int obtenerDimension() {
        return N;
    }

    // Método para mostrar las dimensiones del tablero
    public void mostrarDimension() {
        System.out.println("El tablero tiene un tamaño de: " + N + "x" + N);
    }

    // Método para validar si el tamaño N es válido
    public static boolean esDimensionValida(int N) {
        return N <= MAX_DIMENSION && N > 0;
    }
}

    
