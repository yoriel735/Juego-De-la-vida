/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daw;

/**
 *
 * @author contrasenainvitado
 */


public class todolodechagpt {
}
    /*
    1. Definir la Estructura Principal del Juego

    Tablero de juego (matriz de celdas): El tablero será una matriz bidimensional de tamaño N x N. Cada celda puede estar viva o muerta, por lo tanto, puedes representarlo con valores como 1 para viva y 0 para muerta.
    Condiciones de la matriz: La matriz debe tener dimensiones de hasta 25x25, según lo especificado.

2. Leer la Entrada del Usuario

    Opción de iniciar un nuevo juego o cargar una partida existente:
        Si el usuario quiere cargar una partida desde un archivo, leer el archivo y cargar la configuración.
        Si el usuario quiere iniciar un nuevo juego, pedirle que ingrese el tamaño de la matriz y el porcentaje de células vivas.

3. Generación Aleatoria o Manual de Celdas Vivas

    Si el usuario elige modo aleatorio:
        Calcular cuántas células deben estar vivas según el porcentaje que el usuario ingresó.
        Colocar las células vivas en posiciones aleatorias de la matriz.
    Si el usuario elige modo manual:
        Permitir que el usuario ingrese manualmente las posiciones de las células vivas en el tablero.

4. Mostrar la Generación Inicial

    Mostrar el estado inicial del tablero después de haber colocado las células vivas, ya sea aleatoriamente o manualmente.

5. Simular el Juego

    Reglas de la vida: Implementar las reglas del juego de la vida para generar la siguiente generación de células:
        Una célula muerta con exactamente 3 células vecinas vivas "nace".
        Una célula viva con 2 ó 3 células vecinas vivas sigue viva.
        Una célula viva que tenga 0 o 1 células vecinas muere por "soledad".
        Una célula viva que tenga más de 3 células vecinas muere por "sobrepoblación".
    Para cada célula, contar las células vecinas vivas (incluir las 8 vecinas en todas las direcciones).
    Generar la siguiente generación basándote en las reglas anteriores.

6. Interacción con el Usuario durante el Juego

    Después de cada generación, mostrar el estado de la matriz (tanto la generación actual como la anterior).
    Preguntar al usuario si desea:
        Ver la siguiente generación.
        Terminar el juego.
        Guardar el estado del juego.

7. Detectar Finalización Automática

    El juego debe terminar automáticamente si no hay cambios en 3 generaciones consecutivas. Puedes hacer un contador de generaciones donde compares la generación actual con la anterior para ver si hubo cambios. Si no hubo cambios en 3 generaciones seguidas, el juego finaliza automáticamente.

8. Guardar el Estado del Juego

    Si el usuario decide guardar el juego:
        Guardar la matriz con el estado de las celdas, la cantidad de células vivas y el número de generación en un archivo de texto.
        El archivo debe contener:
            Las dimensiones de la matriz.
            El número de células vivas.
            El estado de cada celda en la matriz.
            El número de células vivas en cada generación.

9. Cargar una Partida desde un Archivo

    Si el usuario elige cargar una partida, leer el archivo de texto y restaurar el estado de la matriz de acuerdo con los datos almacenados en el archivo.
    
    import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class JuegoDeLaVida {

    private DimensionesTablero dimensiones;
    private int[][] tablero;  // Matriz que representa el tablero
    private int[][] tableroAnterior;  // Matriz para guardar el tablero anterior

    public JuegoDeLaVida(DimensionesTablero dimensiones) {
        this.dimensiones = dimensiones;
        int N = dimensiones.obtenerDimension();
        this.tablero = new int[N][N];
        this.tableroAnterior = new int[N][N];
    }

    // Método para mostrar la matriz
    public void mostrarTablero() {
        for (int i = 0; i < dimensiones.obtenerDimension(); i++) {
            for (int j = 0; j < dimensiones.obtenerDimension(); j++) {
                System.out.print(tablero[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Método para llenar el tablero con células aleatorias
    public void inicializarTableroAleatorio(int porcentajeVivo) {
        int N = dimensiones.obtenerDimension();
        int totalCeldas = N * N;
        int celdasVivas = (totalCeldas * porcentajeVivo) / 100;

        // Llenamos el tablero con células muertas (0)
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                tablero[i][j] = 0;
            }
        }

        // Colocamos las células vivas de manera aleatoria
        int celdasColocadas = 0;
        while (celdasColocadas < celdasVivas) {
            int i = (int) (Math.random() * N);
            int j = (int) (Math.random() * N);
            if (tablero[i][j] == 0) {
                tablero[i][j] = 1;
                celdasColocadas++;
            }
        }
    }

    // Método para cargar el tablero desde un archivo
    public void cargarTableroDesdeArchivo(String nombreArchivo) {
        try {
            File archivo = new File(nombreArchivo);
            Scanner scanner = new Scanner(archivo);

            // Leer las dimensiones del tablero
            int N = scanner.nextInt();
            dimensiones = new DimensionesTablero(N);
            tablero = new int[N][N];
            tableroAnterior = new int[N][N];

            // Leer el estado del tablero
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    tablero[i][j] = scanner.nextInt();
                }
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("El archivo no fue encontrado.");
        }
    }

    // Método para preguntar al usuario si quiere cargar o crear un nuevo juego
    public static int obtenerOpcionMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("¿Qué deseas hacer?");
        System.out.println("1. Iniciar un nuevo juego");
        System.out.println("2. Cargar un juego desde un archivo");
        return scanner.nextInt();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcionMenu = obtenerOpcionMenu();

        JuegoDeLaVida juego = null;

        if (opcionMenu == 1) {
            // Iniciar un nuevo juego
            System.out.println("Introduce el tamaño del tablero (máximo " + DimensionesTablero.MAX_DIMENSION + "): ");
            int N = scanner.nextInt();
            if (!DimensionesTablero.esDimensionValida(N)) {
                System.out.println("El tamaño máximo permitido es " + DimensionesTablero.MAX_DIMENSION);
                return;
            }

            System.out.println("Introduce el porcentaje de células vivas (0-100): ");
            int porcentajeVivo = scanner.nextInt();

            DimensionesTablero dimensiones = new DimensionesTablero(N);
            juego = new JuegoDeLaVida(dimensiones);
            juego.inicializarTableroAleatorio(porcentajeVivo);

            System.out.println("Generación inicial:");
            juego.mostrarTablero();

        } else if (opcionMenu == 2) {
            // Cargar un juego desde un archivo
            System.out.println("Introduce el nombre del archivo para cargar el juego: ");
            String archivo = scanner.next();
            juego = new JuegoDeLaVida(new DimensionesTablero(0)); // Se crea una instancia temporal
            juego.cargarTableroDesdeArchivo(archivo);

            System.out.println("Tablero cargado:");
            juego.mostrarTablero();

        } else {
            System.out.println("Opción no válida.");
        }

        scanner.close();
    }
}

}
*/