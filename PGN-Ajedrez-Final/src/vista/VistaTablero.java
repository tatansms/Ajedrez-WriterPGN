package vista;
import modelo.Pieza;
import modelo.Tablero;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Clase que maneja la interfaz gráfica del tablero.
 */
public class VistaTablero extends JFrame {
    private JButton[][] botones;

    public VistaTablero() {
        botones = new JButton[8][8];
        inicializarInterfaz();
    }

    private void inicializarInterfaz() {
        setTitle("Ajedrez");
        setLayout(new GridLayout(8, 8));

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                botones[i][j] = new JButton();
                botones[i][j].setBackground((i + j) % 2 == 0 ? Color.WHITE : Color.GRAY);
                botones[i][j].setFocusPainted(false); // Quita el borde de enfoque
                add(botones[i][j]);
            }
        }

        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private ImageIcon cargarIcono(String ruta) {
        java.net.URL recurso = getClass().getResource(ruta);
        if (recurso != null) {
            return new ImageIcon(recurso);
        } else {
            System.err.println("No se encontró la imagen en la ruta: " + ruta);
            return null; // Puedes usar una imagen por defecto o dejarlo vacío
        }
    }

    // En VistaTablero
    public void cerrarVentana() {
        JFrame ventana = (JFrame) SwingUtilities.getWindowAncestor(this);
        if (ventana != null) {
            ventana.dispose(); // Cierra la ventana
        }
    }


    /**
     * Actualiza los botones con las imágenes correspondientes según el tablero.
     */
    public void actualizarVista(Tablero tablero) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Pieza pieza = tablero.obtenerPieza(i, j);
                if (pieza != null) {
                    // Usa el nuevo método para cargar la imagen
                    botones[i][j].setIcon(cargarIcono(pieza.getRutaImagen()));
                } else {
                    botones[i][j].setIcon(null);
                }
            }
        }
    }


    public JButton[][] getBotones() {
        return botones;
    }
}
