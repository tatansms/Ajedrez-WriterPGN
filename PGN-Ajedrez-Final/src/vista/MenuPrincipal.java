package vista;

import javax.swing.*;
import java.awt.*;
import controlador.ControladorAjedrez;
import modelo.Tablero;

public class MenuPrincipal extends JFrame {
    public MenuPrincipal() {
        // Configuración básica de la ventana
        setTitle("Menú Principal");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centra la ventana en la pantalla

        // Crear los botones del menú
        JButton btnNuevoJuego = new JButton("Nuevo Juego");
        JButton btnSalir = new JButton("Salir");

        // Acción del botón "Nuevo Juego"
        btnNuevoJuego.addActionListener(e -> iniciarNuevoJuego());

        // Acción del botón "Salir"
        btnSalir.addActionListener(e -> System.exit(0));

        // Panel para agregar los botones
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));
        panel.add(btnNuevoJuego);
        panel.add(btnSalir);

        // Agregar el panel a la ventana
        add(panel);

        // Hacer visible la ventana
        setVisible(true);
    }

    /**
     * Inicia una nueva partida de ajedrez.
     */
    private void iniciarNuevoJuego() {
        // Crear las instancias necesarias para la nueva partida
        Tablero modelo = new Tablero();            // Modelo
        VistaTablero vista = new VistaTablero();   // Vista
        ControladorAjedrez controlador = new ControladorAjedrez(modelo, vista); // Controlador

        // Cerrar el menú principal (opcional)
        this.dispose(); // Esto cierra la ventana del menú principal
    }

    public static void main(String[] args) {
        // Crear una instancia del menú principal al iniciar el programa
        new MenuPrincipal();
    }
}

