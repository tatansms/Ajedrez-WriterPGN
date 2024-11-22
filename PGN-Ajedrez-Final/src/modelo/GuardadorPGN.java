package modelo;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

public class GuardadorPGN {
    public void guardarPartida(String movimientos, String nombreArchivo) {
        try (FileWriter writer = new FileWriter(nombreArchivo)) {
            writer.write("[Event \"Partida de Ajedrez\"]\n");
            writer.write("[Date \"" + LocalDate.now() + "\"]\n");
            writer.write("[White \"Jugador 1\"]\n");
            writer.write("[Black \"Jugador 2\"]\n");
            writer.write("\n");
            writer.write(movimientos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
