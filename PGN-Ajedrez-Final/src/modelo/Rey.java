package modelo;

/**
 * Clase que representa un Rey.
 */
public class Rey extends Pieza {

    public Rey(String color) {
        super(color);
        setRutaImagen("/Utils/imagenes/Rey_" + color.toLowerCase() + ".png");
    }

    @Override
    public boolean movimientoValido(int filaInicio, int colInicio, int filaFin, int colFin, Tablero tablero) {
        return Math.abs(filaFin - filaInicio) <= 1 && Math.abs(colFin - colInicio) <= 1;
    }
}
