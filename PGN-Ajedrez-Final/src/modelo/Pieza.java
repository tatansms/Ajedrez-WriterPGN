package modelo;

import javax.swing.ImageIcon;

public abstract class Pieza {
    protected String color; // "Blanco" o "Negro"
    protected String rutaImagen; // Ruta para las imágenes de cada pieza
    protected ImageIcon imagen; // Imagen cargada

    public Pieza(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
        cargarImagen(); // Cargar la imagen al establecer la ruta
    }

    public ImageIcon getImagen() {
        return imagen;
    }

    private void cargarImagen() {
        try {
            this.imagen = new ImageIcon(getClass().getResource(rutaImagen));
        } catch (Exception e) {
            System.err.println("Error al cargar la imagen: " + rutaImagen);
            e.printStackTrace();
        }
    }

    public abstract boolean movimientoValido(int filaInicio, int colInicio, int filaFin, int colFin, Tablero tablero);
}
