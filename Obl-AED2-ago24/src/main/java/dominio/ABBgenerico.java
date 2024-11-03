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
        if (dato.compareTo(nodo.getNodo()) < 0) {
            nodo.setIzquierdo(insertarRecursivo(nodo.getIzquierdo(), dato));
        } else if (dato.compareTo(nodo.getNodo()) > 0) {
            nodo.setDerecho(insertarRecursivo(nodo.getDerecho(), dato));
        }
        return nodo;
    }

    public int contarNodos() {
        return contarNodosRecursivo(raiz);
    }

    private int contarNodosRecursivo(NodoGenerico<T> nodo) {
        if (nodo == null) {
            return 0;
        }
        return 1 + contarNodosRecursivo(nodo.getIzquierdo()) + contarNodosRecursivo(nodo.getDerecho());
    }

    public T buscar(T dato) {
        return buscarRecursivo(raiz, dato);
    }

    /*private NodoGenerico<T> buscarRecursivo(NodoGenerico<T> nodo, T dato) {
        if (nodo == null || dato.equals(nodo.getNodo())) {
            return nodo;
        }
        if (dato.compareTo(nodo.getNodo()) < 0) {
            return buscarRecursivo(nodo.getIzquierdo(), dato);
        }
        return buscarRecursivo(nodo.getDerecho(), dato);
    }*/
    private T buscarRecursivo(NodoGenerico<T> nodo, T dato) {
        if (nodo == null) {
            return null; // No se encontró
        }
        int comparacion = dato.compareTo(nodo.getNodo());
        if (comparacion == 0) {
            return nodo.getNodo(); // Encontrado
        } else if (comparacion < 0) {
            return buscarRecursivo(nodo.getIzquierdo(), dato); // Buscar en subárbol izquierdo
        } else {
            return buscarRecursivo(nodo.getDerecho(), dato); // Buscar en subárbol derecho
        }
    }
}
