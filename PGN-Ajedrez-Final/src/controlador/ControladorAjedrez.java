package controlador;

import modelo.Pieza;
import modelo.Tablero;
import vista.MenuPrincipal;
import vista.VistaTablero;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que actúa como controlador del sistema de ajedrez.
 */
public class ControladorAjedrez {
    private Tablero modelo;
    private VistaTablero vista;


    // Variables para manejar el movimiento
    private int filaSeleccionada = -1;
    private int colSeleccionada = -1;

    // Lista para almacenar los movimientos en formato PGN
    private List<String> movimientos;

    public ControladorAjedrez(Tablero modelo, VistaTablero vista) {
        this.modelo = modelo;
        this.vista = vista;
        this.movimientos = new ArrayList<>();
        inicializarControlador();
    }

    private void inicializarControlador() {
        JButton[][] botones = vista.getBotones();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                int fila = i;
                int col = j;

                botones[i][j].addActionListener(e -> manejarMovimiento(fila, col));
            }
        }

        // Inicializa la vista según el estado del tablero
        vista.actualizarVista(modelo);
    }

    /**
     * Maneja los movimientos de las piezas en el tablero.
     */
    private void manejarMovimiento(int fila, int col) {
        if (filaSeleccionada == -1 && colSeleccionada == -1) {
            // Primera selección: elegir una pieza
            if (modelo.obtenerPieza(fila, col) != null) {
                filaSeleccionada = fila;
                colSeleccionada = col;
                System.out.println("Pieza seleccionada en (" + fila + ", " + col + ")");
            }
        } else {
            // Segunda selección: mover la pieza
            if (modelo.moverPieza(filaSeleccionada, colSeleccionada, fila, col)) {
                System.out.println("Movimiento realizado a (" + fila + ", " + col + ")");
                vista.actualizarVista(modelo); // Actualiza la vista
                verificarEstadoPartida();     // Verifica si la partida terminó
            } else {
                System.out.println("Movimiento inválido");
            }
            filaSeleccionada = -1;
            colSeleccionada = -1; // Reinicia la selección
        }
    }


    private void verificarEstadoPartida() {
        boolean reyBlancoPresente = modelo.reyPresente("Blanco");
        boolean reyNegroPresente = modelo.reyPresente("Negro");

        if (!reyBlancoPresente || !reyNegroPresente) {
            String ganador = reyBlancoPresente ? "Jugador Blanco" : "Jugador Negro";
            mostrarOpcionesFinDePartida(ganador);
        }
    }
    private void reiniciarPartida() {
        modelo = new Tablero(); // Crea un nuevo tablero
        vista.actualizarVista(modelo); // Actualiza la vista con el nuevo tablero
        filaSeleccionada = -1;
        colSeleccionada = -1; // Reinicia la selección
        System.out.println("La partida ha sido reiniciada.");
    }

    private void mostrarMenuPrincipal() {
        vista.cerrarVentana(); // Cierra la ventana actual de la partida
        // Supongamos que tienes una clase `MenuPrincipal`
        new MenuPrincipal(); // Abre la ventana del menú principal
    }


    /**
     * Muestra un cuadro de diálogo con opciones al finalizar la partida.
     *
     * @param ganador El jugador que ganó (Blanco o Negro).
     */
    private void mostrarOpcionesFinDePartida(String ganador) {
        int opcion = JOptionPane.showOptionDialog(
                vista,
                "¡" + ganador + " ha ganado!\n¿Qué deseas hacer ahora?",
                "Fin de la partida",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                new String[]{"Guardar Partida", "Reiniciar", "Salir al Menú Principal"},
                "Guardar Partida"
        );

        switch (opcion) {
            case 0: // Guardar Partida
                guardarPartidaEnPGN("partida.pgn", "Jugador1", "Jugador2", ganador.equals("Jugador Blanco") ? "1-0" : "0-1");

                break;
            case 1: // Reiniciar
                reiniciarPartida();
                break;
            case 2: // Salir al Menú Principal
                mostrarMenuPrincipal();
                break;
            default:
                System.exit(0); // Cierra el programa si no seleccionan nada
        }
    }

    /**
     * Convierte las coordenadas del tablero a notación PGN.
     */
    private String convertirCoordenadasAPGN(int fila, int col) {
        char columna = (char) ('a' + col); // a, b, c, ...
        int filaPGN = 8 - fila; // Invertir la fila (1 en la parte inferior)
        return "" + columna + filaPGN;
    }

    /**
     * Guarda la partida en un archivo PGN.
     */
    public void guardarPartidaEnPGN(String nombreArchivo, String jugadorBlanco, String jugadorNegro, String resultado) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
            // Escribir encabezados del archivo PGN
            writer.write("[Event \"Partida de Ajedrez\"]\n");
            writer.write("[Site \"Local\"]\n");
            writer.write("[Date \"" + java.time.LocalDate.now() + "\"]\n");
            writer.write("[White \"" + jugadorBlanco + "\"]\n");
            writer.write("[Black \"" + jugadorNegro + "\"]\n");
            writer.write("[Result \"" + resultado + "\"]\n\n");

            // Escribir movimientos
            for (int i = 0; i < movimientos.size(); i++) {
                if (i % 2 == 0) {
                    writer.write(((i / 2) + 1) + ". "); // Número del turno
                }
                writer.write(movimientos.get(i) + " ");
            }

            writer.write("\n");
            System.out.println("Partida guardada en " + nombreArchivo);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error al guardar el archivo PGN.");
        }
    }
}
