package dominio;

public class NodoGenerico<T extends Comparable<T>> {
    private T dato;
    private NodoGenerico<T> izquierdo;
    private NodoGenerico<T> derecho;

    public NodoGenerico(T dato) {
        this.dato = dato;
        this.izquierdo = null;
        this.derecho = null;
    }

    // Getters y setters necesarios
    public T getDato() {
        return dato;
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
