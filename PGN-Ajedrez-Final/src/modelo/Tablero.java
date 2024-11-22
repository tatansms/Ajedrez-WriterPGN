package modelo;

/**
 * Clase que representa el tablero de ajedrez.
 */
public class Tablero {
    private Pieza[][] tablero;

    public Tablero() {
        tablero = new Pieza[8][8];
        inicializarPiezas();
    }

    /**
     * Inicializa el tablero con las piezas en sus posiciones iniciales.
     */
    private void inicializarPiezas() {
        // Peones
        for (int col = 0; col < 8; col++) {
            tablero[1][col] = new Peon("Negro");
            tablero[6][col] = new Peon("Blanco");
        }

        // Torres
        tablero[0][0] = new Torre("Negro");
        tablero[0][7] = new Torre("Negro");
        tablero[7][0] = new Torre("Blanco");
        tablero[7][7] = new Torre("Blanco");

        // Caballos
        tablero[0][1] = new Caballo("Negro");
        tablero[0][6] = new Caballo("Negro");
        tablero[7][1] = new Caballo("Blanco");
        tablero[7][6] = new Caballo("Blanco");

        // Alfiles
        tablero[0][2] = new Alfil("Negro");
        tablero[0][5] = new Alfil("Negro");
        tablero[7][2] = new Alfil("Blanco");
        tablero[7][5] = new Alfil("Blanco");

        // Reyes
        tablero[0][4] = new Rey("Negro");
        tablero[7][4] = new Rey("Blanco");

        // Damas
        tablero[0][3] = new Dama("Negro");
        tablero[7][3] = new Dama("Blanco");
    }

    /**
     * Devuelve la pieza en la posición dada.
     *
     * @param fila    Fila del tablero.
     * @param columna Columna del tablero.
     * @return La pieza en la posición (fila, columna), o null si no hay ninguna.
     */
    public Pieza obtenerPieza(int fila, int columna) {
        return tablero[fila][columna];
    }

    /**
     * Mueve una pieza de una posición a otra si el movimiento es válido.
     *
     * @param filaInicio Fila inicial.
     * @param colInicio  Columna inicial.
     * @param filaFin    Fila final.
     * @param colFin     Columna final.
     * @return true si el movimiento se realizó con éxito, false si no.
     */
    public boolean moverPieza(int filaInicio, int colInicio, int filaFin, int colFin) {
        Pieza pieza = obtenerPieza(filaInicio, colInicio);
        if (pieza != null && pieza.movimientoValido(filaInicio, colInicio, filaFin, colFin, this)) {
            tablero[filaFin][colFin] = pieza;
            tablero[filaInicio][colInicio] = null;
            return true;
        }
        return false;
    }

    /**
     * Verifica si hay al menos un rey en el tablero.
     *
     * @param color Color del rey ("Blanco" o "Negro").
     * @return true si el rey está en el tablero, false si fue capturado.
     */
    public boolean reyPresente(String color) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Pieza pieza = tablero[i][j];
                if (pieza instanceof Rey && pieza.getColor().equals(color)) {
                    return true;
                }
            }
        }
        return false;
    }

            /**
             * Muestra el estado actual del tablero en consola (opcional para depuración).
             */
            public void imprimirTablero() {
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                Pieza pieza = tablero[i][j];
                System.out.print((pieza != null ? pieza.getClass().getSimpleName().charAt(0) : ".") + " ");
            }
            System.out.println();
        }
    }
}


