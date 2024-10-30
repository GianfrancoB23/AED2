package dominio;

public class ABBJugadores<T extends Comparable<T>> {
    private Jugador raiz;

    public ABBJugadores() {
        this.raiz = null;
    }

    public void insertar(Jugador nuevoJugador) {
        if (this.raiz == null) {
            this.raiz = new Jugador(nuevoJugador.getAlias(),nuevoJugador.getNombre(),nuevoJugador.getApellido(),nuevoJugador.getCategoria());
        } else {
            insertarRec(this.raiz, nuevoJugador);
        }
    }

    private void insertarRec(Jugador jugador, Jugador nuevoJugador) {
        if (nuevoJugador.compareTo(jugador) > 0) {
            // Derecha
            if (jugador.getDer() == null) {
                jugador.setDer(nuevoJugador);
            } else {
                insertarRec(jugador.getDer(), nuevoJugador);
            }
        } else {
            // Izquierda
            if (jugador.getIzq() == null) {
                jugador.setIzq(nuevoJugador);
            } else {
                insertarRec(jugador.getIzq(), nuevoJugador);
            }
        }
    }

    public ResultadoBusquedaJugador buscar(String alias) {
        return buscarRec(this.raiz, alias, 0);
    }

    private ResultadoBusquedaJugador buscarRec(Jugador nodo, String alias, int nodosRecorridos) {
        if (nodo == null) {
            return new ResultadoBusquedaJugador(null, nodosRecorridos);
        }

        nodosRecorridos++;

        if (nodo.getAlias().equals(alias)) {
            return new ResultadoBusquedaJugador(nodo, nodosRecorridos); //Se crea objeto de ResultadoBusquedaJugador para pasar ambas cosas
        }

        if (alias.compareTo(nodo.getAlias()) < 0) {
            return buscarRec(nodo.getIzq(), alias, nodosRecorridos); //Buscar en subarbol izquierdo
        } else {
            return buscarRec(nodo.getDer(), alias, nodosRecorridos); //Buscar en subarbol derecho
        }
    }
}
