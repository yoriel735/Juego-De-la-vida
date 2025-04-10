/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package daw;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author contrasenainvitado
 */
public class ElJuegoDeLaVida {

    private List<Integer> celulasVivasRondas = new ArrayList<>();
    private Tablero tablero;
    private DimensionesTablero dimensiones;
    private int generacion;

    //Resuemn de porque se crea un constructor algo diferente
    //Es para crear todo desde esta clase, esto da como ventaja legilibilidad y 
    //poder crear el tablero con todas las restrcciones ya tomadas en cuenta
    public ElJuegoDeLaVida(int tamaño) {
        this.dimensiones = new DimensionesTablero(tamaño);
        //con esta clase validamos el tamaño
        this.tablero = new Tablero(dimensiones);
        //con esta clase iniciamos el tablero ya con el tamaño validado
        this.generacion = 1;//Con esto siempre iniciamos la generacion en 1
    }

//         public static int opcionesMenu(){
//           Scanner teclado = new Scanner(System.in);
//            System.out.println("¿Que desea hacer?");
//            System.out.println("1. - Empezar una nueva partida");
//            System.out.println("2. - Cargar una partida anterior");
//            System.out.println("3. - Salir");
//            
//            return teclado.nextInt();   
//            
//        }

    public void setCelulasVivasRondas(List<Integer> celulasVivasRondas) {
        this.celulasVivasRondas = celulasVivasRondas;
    }

    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }

    public void setDimensiones(DimensionesTablero dimensiones) {
        this.dimensiones = dimensiones;
    }

    public void setGeneracion(int generacion) {
        this.generacion = generacion;
    }
    
    
    public void iniciarJuego() {
        String opcionStr = JOptionPane.showInputDialog("¿Cómo quieres inicializar el tablero?\n1. Aleatorio\n2. Manual");
        int opcion = Integer.parseInt(opcionStr);

        if (opcion == 1) {
            String porcentajeStr = JOptionPane.showInputDialog("Introduce el porcentaje de células vivas (0-100): ");
            int porcentajeVivas = Integer.parseInt(porcentajeStr);
            tablero.inicializarAleatorio(porcentajeVivas);
        } else {
            tablero.inicializarManual();
        }

        while (true) {
            tablero.mostrarTablero();
            System.out.println("Generación: " + generacion);
            System.out.println("Células vivas: " + tablero.contarCeldasVivas());

            String opcionUsuarioStr = JOptionPane.showInputDialog("¿Quieres continuar?\n1. Siguiente generación\n2. Terminar.\n3. Guardar.");
            int opcionUsuario = Integer.parseInt(opcionUsuarioStr);

            if (opcionUsuario == 2) {
                JOptionPane.showMessageDialog(null, "Gracias por jugar. ¡Hasta la próxima!", "Despedida", JOptionPane.INFORMATION_MESSAGE);
                break;
            }
            if (opcionUsuario == 3) {
                guardarJuego();
            } else {
                actualizarGeneracion();
            }
        }
    }

    public void actualizarGeneracion() {
        boolean[][] nuevoEstado = new boolean[dimensiones.obtenerDimension()][dimensiones.obtenerDimension()];
        for (int i = 0; i < dimensiones.obtenerDimension(); i++) {
            for (int j = 0; j < dimensiones.obtenerDimension(); j++) {
                int vecinosVivos = tablero.contarVecinosVivos(i, j);
                if (tablero.getTablero()[i][j]) {
                    nuevoEstado[i][j] = vecinosVivos == 2 || vecinosVivos == 3;
                } else {
                    nuevoEstado[i][j] = vecinosVivos == 3;
                }
            }
        }
        tablero.setTablero(nuevoEstado);
        generacion++;
        celulasVivasRondas.add(tablero.contarCeldasVivas());
    }

    public void guardarJuego() {
        String nombreArchivo = JOptionPane.showInputDialog("Introduce el nombre del archivo para guardar (sin extensión):");
        if (nombreArchivo != null && !nombreArchivo.trim().isEmpty()) {
            try {
                GuardarJuego.guardarEstadoJuego(tablero, generacion, celulasVivasRondas);
                JOptionPane.showMessageDialog(null, "El archivo ha sido guardado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al guardar el archivo.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
    