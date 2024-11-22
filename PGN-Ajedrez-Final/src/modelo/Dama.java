package modelo;

/**
 * Clase que representa una Dama (Reina).
 */
public class Dama extends Pieza {

    public Dama(String color) {
        super(color);
        setRutaImagen("/Utils/imagenes/reina_" + color.toLowerCase() + ".png");
    }

    @Override
    public boolean movimientoValido(int filaInicio, int colInicio, int filaFin, int colFin, Tablero tablero) {
        Torre torre = new Torre(color); // Hereda movimiento de Torre
        Alfil alfil = new Alfil(color); // Hereda movimiento de Alfil
        return torre.movimientoValido(filaInicio, colInicio, filaFin, colFin, tablero)
                || alfil.movimientoValido(filaInicio, colInicio, filaFin, colFin, tablero);
    }
}
