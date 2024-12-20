package dominio.ABB;

import dominio.Jugadores.Resultado;

public class ABB<T extends Comparable<T>> {
    private Nodo<T> raiz;

    public ABB() {
        this.raiz = null;
    }

    public void insertar(T dato) {
        raiz = insertarRecursivo(raiz, dato);
    }

    private Nodo<T> insertarRecursivo(Nodo<T> nodo, T dato) {
        if (nodo == null) {
            return new Nodo<>(dato);
        }
        if (dato.compareTo(nodo.getNodo()) < 0) {
            nodo.setIzquierdo(insertarRecursivo(nodo.getIzquierdo(), dato));
        } else if (dato.compareTo(nodo.getNodo()) > 0) {
            nodo.setDerecho(insertarRecursivo(nodo.getDerecho(), dato));
        }
        return nodo;
    }

    public Resultado<T> buscar(T dato) {
        return buscarRecursivo(raiz, dato, 0);
    }

    private Resultado<T> buscarRecursivo(Nodo<T> nodo, T dato, int countNodos) {
        if (nodo == null) {
            return new Resultado<T>(null, countNodos); // No se encontró
        }
        countNodos++;
        int comparacion = dato.compareTo(nodo.getNodo());
        if (comparacion == 0) {
            return new Resultado<T>(nodo.getNodo(), countNodos); // Encontrado
        } else if (comparacion < 0) {
            return buscarRecursivo(nodo.getIzquierdo(), dato, countNodos); // Buscar en subárbol izquierdo
        } else {
            return buscarRecursivo(nodo.getDerecho(), dato, countNodos); // Buscar en subárbol derecho
        }
    }
    public String inOrden() {
        String resultado = inOrdenRec(raiz);
        if (!resultado.isEmpty() && resultado.endsWith("|")) {
            resultado = resultado.substring(0, resultado.length() - 1);
        }
        return resultado;
    }
    private String inOrdenRec(Nodo<T> nodo) {
        if (nodo == null) {
            return "";
        }
        String izquierdo = inOrdenRec(nodo.getIzquierdo());
        String valorNodo = nodo.getNodo().toString();
        String derecho = inOrdenRec(nodo.getDerecho());

        // Concatenar los valores con el separador "|"
        String resultado = "";
        if (!izquierdo.isEmpty()) {
            resultado += izquierdo + "|";
        }
        resultado += valorNodo;
        if (!derecho.isEmpty()) {
            resultado += "|" + derecho;
        }
        return resultado;
    }

    public String inOrdenDesc() {
        String resultado = inOrdenDescRec(raiz);
        if (!resultado.isEmpty() && resultado.endsWith("|")) {
            resultado = resultado.substring(0, resultado.length() - 1);
        }
        return resultado;
    }
    private String inOrdenDescRec(Nodo<T> nodo) {
        if (nodo == null) {
            return "";
        }
        String derecho = inOrdenDescRec(nodo.getDerecho());
        String valorNodo = nodo.getNodo().toString();
        String izquierdo = inOrdenDescRec(nodo.getIzquierdo());

        // Concatenar los valores con el separador "|"
        String resultado = "";
        if (!derecho.isEmpty()) {
            resultado += derecho + "|";
        }

        resultado += valorNodo;

        if (!izquierdo.isEmpty()) {
            resultado +="|" + izquierdo  ;
        }
        return resultado;
    }


}