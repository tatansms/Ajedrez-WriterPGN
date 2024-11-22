package modelo;

/**
 * Clase que representa un Caballo.
 */
public class Caballo extends Pieza {

    public Caballo(String color) {
        super(color);
        setRutaImagen("/Utils/imagenes/caballo_" + color.toLowerCase() + ".png");

    }

    @Override
    public boolean movimientoValido(int filaInicio, int colInicio, int filaFin, int colFin, Tablero tablero) {
        int difFila = Math.abs(filaFin - filaInicio);
        int difCol = Math.abs(colFin - colInicio);
        return (difFila == 2 && difCol == 1) || (difFila == 1 && difCol == 2);
    }
}
