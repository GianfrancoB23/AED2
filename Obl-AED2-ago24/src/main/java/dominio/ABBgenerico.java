package dominio;

public class ABBgenerico<T extends Comparable<T>> {
    private NodoGenerico<T> raiz;

    public ABBgenerico() {
        this.raiz = null;
    }

    public void insertar(T dato) {
        raiz = insertarRecursivo(raiz, dato);
    }

    private NodoGenerico<T> insertarRecursivo(NodoGenerico<T> nodo, T dato) {
        if (nodo == null) {
            return new NodoGenerico<>(dato);
        }
        if (dato.compareTo(nodo.getDato()) < 0) {
            nodo.setIzquierdo(insertarRecursivo(nodo.getIzquierdo(), dato));
        } else if (dato.compareTo(nodo.getDato()) > 0) {
            nodo.setDerecho(insertarRecursivo(nodo.getDerecho(), dato));
        }
        return nodo;
    }

    public NodoGenerico<T> buscar(T dato) {
        return buscarRecursivo(raiz, dato);
    }

    private NodoGenerico<T> buscarRecursivo(NodoGenerico<T> nodo, T dato) {
        if (nodo == null || dato.equals(nodo.getDato())) {
            return nodo;
        }
        if (dato.compareTo(nodo.getDato()) < 0) {
            return buscarRecursivo(nodo.getIzquierdo(), dato);
        }
        return buscarRecursivo(nodo.getDerecho(), dato);
    }
}
