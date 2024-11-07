package dominio.Jugadores;

public class Resultado<T> {

    private T dato;
    private int nodosRecorridos;

    public Resultado(T busqueda, int nodosRecorridos) {
        this.dato = busqueda;
        this.nodosRecorridos = nodosRecorridos;
    }

    public T getDato() {
        return dato;
    }

    public int getNodosRecorridos() {
        return nodosRecorridos;
    }
}
