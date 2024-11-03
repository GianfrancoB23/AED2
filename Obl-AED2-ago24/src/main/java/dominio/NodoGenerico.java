package dominio;

public class NodoGenerico<T extends Comparable<T>> {
    private T nodo;
    private NodoGenerico<T> izquierdo;
    private NodoGenerico<T> derecho;

    public NodoGenerico(T nodo) {
        this.nodo = nodo;
        this.izquierdo = null;
        this.derecho = null;
    }

    // Getters y setters necesarios
    public T getNodo() {
        return nodo;
    }

    public NodoGenerico<T> getIzquierdo() {
        return izquierdo;
    }

    public void setIzquierdo(NodoGenerico<T> izquierdo) {
        this.izquierdo = izquierdo;
    }

    public NodoGenerico<T> getDerecho() {
        return derecho;
    }

    public void setDerecho(NodoGenerico<T> derecho) {
        this.derecho = derecho;
    }
}
