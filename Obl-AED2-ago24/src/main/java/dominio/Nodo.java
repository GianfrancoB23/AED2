package dominio;

public class Nodo<T extends Comparable<T>> {
    private T nodo;
    private Nodo<T> izquierdo;
    private Nodo<T> derecho;

    public Nodo(T nodo) {
        this.nodo = nodo;
        this.izquierdo = null;
        this.derecho = null;
    }

    // Getters y setters necesarios
    public T getNodo() {
        return nodo;
    }

    public Nodo<T> getIzquierdo() {
        return izquierdo;
    }

    public void setIzquierdo(Nodo<T> izquierdo) {
        this.izquierdo = izquierdo;
    }

    public Nodo<T> getDerecho() {
        return derecho;
    }

    public void setDerecho(Nodo<T> derecho) {
        this.derecho = derecho;
    }
}