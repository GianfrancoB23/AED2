package dominio.Jugadores;

import interfaz.Categoria;

public class ABBJugadores<T extends Comparable<T>> {
    private Jugador raiz;
    private Categoria categoria;

    public ABBJugadores() {
        this.raiz = null;
    }

    // Constructor para los ABBCategorias
    public ABBJugadores(Categoria categoria) {
        this.raiz = null;
        this.categoria = categoria;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void insertar(Jugador nuevoJugador) {
        if (this.raiz == null) {
            this.raiz = nuevoJugador;
        } else {
            insertarRec(this.raiz, nuevoJugador);
        }
    }

    // Inserto en arbol principal
    private void insertarRec(Jugador nodo, Jugador nuevoJugador) {
        if (nuevoJugador.getAlias().compareTo(nodo.getAlias()) > 0) {
            if (nodo.getDer() == null) {
                nodo.setDer(nuevoJugador);
            } else {
                insertarRec(nodo.getDer(), nuevoJugador);
            }
        } else if (nuevoJugador.getAlias().compareTo(nodo.getAlias()) < 0) {
            if (nodo.getIzq() == null) {
                nodo.setIzq(nuevoJugador);
            } else {
                insertarRec(nodo.getIzq(), nuevoJugador);
            }
        }
    }

    public ResultadoBusquedaJugador obtenerJugador(String alias) {
        return obtenerJugadorRec(this.raiz, alias, 0);
    }

    private ResultadoBusquedaJugador obtenerJugadorRec(Jugador nodo, String alias, int nodosRecorridos) {
        if (nodo == null) {
            return new ResultadoBusquedaJugador(null, nodosRecorridos);
        }

        nodosRecorridos++;

        if (nodo.getAlias().equals(alias)) {
            return new ResultadoBusquedaJugador(nodo, nodosRecorridos); // Se crea clase de ResultadoBusquedaJugador para pasar el nodo y la cantidad recorridos
        }

        if (alias.compareTo(nodo.getAlias()) < 0) {
            return obtenerJugadorRec(nodo.getIzq(), alias, nodosRecorridos); // Buscar en subarbol izquierdo
        } else {
            return obtenerJugadorRec(nodo.getDer(), alias, nodosRecorridos); // Buscar en subarbol derecho
        }
    }

    public boolean existe(String alias) {
        return existeRec(this.raiz, alias);
    }

    private boolean existeRec(Jugador nodo, String alias) {
        if (nodo == null) {
            return false;
        }

        if (nodo.getAlias().equals(alias)) {
            return true;
        }

        if (alias.compareTo(nodo.getAlias()) < 0) {
            return existeRec(nodo.getIzq(), alias); // Existe en subarbol izquierdo
        } else {
            return existeRec(nodo.getDer(), alias); // Existe en subarbol derecho
        }
    }

    public String inOrden() {
        String ret = inOrden(this.raiz);
        if (!ret.isEmpty()) {
            return ret.substring(0, ret.length() - 1);
        } else {
            return ret;
        }
    }

    //Como esta insertado ABB por alias, ya esta ordenado
    private String inOrden(Jugador nodo) { // izq - nodo - der
        String ret = "";
        if (nodo == null) {
            return ret;
        }

        if (nodo != null) {
            ret += inOrden(nodo.getIzq());
            ret += nodo.getAlias() + ";" + nodo.getNombre() + ";" + nodo.getApellido() + ";" + nodo.getCategoria().toString() + "|";
            ret += inOrden(nodo.getDer());
        }
        return ret;
    }
    public String listarJugadorEquipo(String nombreEquipo) {
        String ret = listarJugadorEquipo(this.raiz, nombreEquipo);
        if(!ret.isEmpty()) {
            return ret.substring(0, ret.length()-1);
        } else {
            return ret;
        }
    }

    //Como esta insertado ABB por alias, ya esta ordenado
    private String listarJugadorEquipo(Jugador nodo, String nombreEquipo) { // izq - nodo - der
        String ret = "";
        if (nodo == null) {
            return ret;
        }

        if (nodo != null && nodo.getEquipo().getNombre() == nombreEquipo) {
            ret += listarJugadorEquipo(nodo.getIzq(), nombreEquipo);
            ret += nodo.getAlias() + ";" + nodo.getNombre() + ";" + nodo.getApellido() + ";" + nodo.getCategoria().toString() + "|";
            ret += listarJugadorEquipo(nodo.getDer(), nombreEquipo);
        }
        return ret;
    }
    private String buscarXCatRec(Jugador nodo) {
        String ret = "";
        if (nodo != null) {

            ret += buscarXCatRec(nodo.getIzq());
            ret += nodo.getAlias() + ";" + nodo.getNombre() + ";" + nodo.getApellido() + ";" + nodo.getCategoria().toString() + "|";
            ret += buscarXCatRec(nodo.getDer());

        }
        return ret;
    }


}
