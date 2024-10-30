package dominio;

public class ResultadoBusquedaJugador {
    private Jugador jugador;
    private int nodosRecorridos;

    public ResultadoBusquedaJugador(Jugador jugador, int nodosRecorridos) {
        this.jugador = jugador;
        this.nodosRecorridos = nodosRecorridos;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public int getNodosRecorridos() {
        return nodosRecorridos;
    }
}
