package modelo;

/**
 * Clase que representa un Alfil.
 */
public class Alfil extends Pieza {

    public Alfil(String color) {
        super(color);
        setRutaImagen("/Utils/imagenes/alfil_" + color.toLowerCase() + ".png");
    }




    @Override
    public boolean movimientoValido(int filaInicio, int colInicio, int filaFin, int colFin, Tablero tablero) {
        if (Math.abs(filaFin - filaInicio) == Math.abs(colFin - colInicio)) { // Movimiento diagonal
            int dirFila = filaFin > filaInicio ? 1 : -1;
            int dirCol = colFin > colInicio ? 1 : -1;

            int i = filaInicio + dirFila;
            int j = colInicio + dirCol;
            while (i != filaFin && j != colFin) {
                if (tablero.obtenerPieza(i, j) != null) return false;
                i += dirFila;
                j += dirCol;
            }
            return true;
        }
        return false;
    }
}
