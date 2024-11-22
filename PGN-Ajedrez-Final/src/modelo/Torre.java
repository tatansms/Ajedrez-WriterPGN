package modelo;

/**
 * Clase que representa una Torre.
 */
public class Torre extends Pieza {

    public Torre(String color) {
        super(color);
        setRutaImagen("/Utils/imagenes/torre_" + color.toLowerCase() + ".png");
    }

    @Override
    public boolean movimientoValido(int filaInicio, int colInicio, int filaFin, int colFin, Tablero tablero) {
        if (filaInicio == filaFin) { // Movimiento horizontal
            for (int i = Math.min(colInicio, colFin) + 1; i < Math.max(colInicio, colFin); i++) {
                if (tablero.obtenerPieza(filaInicio, i) != null) return false;
            }
            return true;
        } else if (colInicio == colFin) { // Movimiento vertical
            for (int i = Math.min(filaInicio, filaFin) + 1; i < Math.max(filaInicio, filaFin); i++) {
                if (tablero.obtenerPieza(i, colInicio) != null) return false;
            }
            return true;
        }
        return false;
    }
}
