package modelo;

/**
 * Clase que representa un Peón.
 */
public class Peon extends Pieza {

    public Peon(String color) {
        super(color);
        setRutaImagen("/Utils/imagenes/peon_" + color.toLowerCase() + ".png");
    }

    @Override
    public boolean movimientoValido(int filaInicio, int colInicio, int filaFin, int colFin, Tablero tablero) {
        int direccion = color.equals("Blanco") ? -1 : 1; // Blancos suben, negros bajan
        if (colInicio == colFin) {
            // Movimiento hacia adelante
            return (filaFin == filaInicio + direccion) && tablero.obtenerPieza(filaFin, colFin) == null;
        } else if (Math.abs(colFin - colInicio) == 1) {
            // Movimiento en diagonal para capturar
            return (filaFin == filaInicio + direccion) && tablero.obtenerPieza(filaFin, colFin) != null;
        }
        return false;
    }
}
