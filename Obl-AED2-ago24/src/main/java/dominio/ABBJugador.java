package dominio;

public class ABBJugador<T extends Comparable<T>> {
    private Jugador raiz;

    public void insertar(Jugador dato) {
        if (this.raiz == null) {
            this.raiz = new Jugador(dato.getAlias(),dato.getNombre(),dato.getApellido(),dato.getCategoria());
        } else {
            insertarRec(raiz, dato);
        }
    }

    //pre nodo != null
    private void insertarRec(Jugador nodo, T dato) {
        if (dato.compareTo(nodo.getDato()) > 0) {
            if (nodo.getDer() == null) {
                nodo.setDer(new Nodo<T>(dato));
            } else {
                insertarRec(nodo.getDer(), dato);
            }
        } else {
            if (nodo.getIzq() == null) {
                nodo.setIzq(new Nodo<T>(dato));
            } else {
                insertarRec(nodo.getIzq(), dato);
            }
        }
    }
}
