package dominio;

import dominio.Jugadores.Jugador;
import dominio.Jugadores.ResultadoBusquedaJugador;
import interfaz.Categoria;

public class ABBEquipos <T extends Comparable<T>>{
    private Equipo raiz;

    public ABBEquipos() {
        this.raiz = null;
    }

    public boolean existe(String nombre) {
        return existeRec(this.raiz, nombre);
    }

    private boolean existeRec(Equipo nodo, String nombre) {
        if (nodo == null) {
            return false;
        }

        if (nodo.getNombre().equals(nombre)) {
            return true; // Se crea clase de ResultadoBusquedaJugador para pasar el nodo y la cantidad recorridos
        }

        if (nombre.compareTo(nodo.getNombre()) < 0) {
            return existeRec(nodo.getIzq(), nombre); // existe en subarbol izquierdo
        } else {
            return existeRec(nodo.getDer(), nombre); // existe en subarbol derecho
        }
    }

    public void insertar(Equipo nuevoEquipo) {
        if (this.raiz == null) {
            this.raiz = nuevoEquipo;
        } else {
            insertarRec(this.raiz, nuevoEquipo);
        }
    }

    // Inserto en arbol principal
    private void insertarRec(Equipo nodo, Equipo nuevoEquipo) {
        if (nuevoEquipo.getNombre().compareTo(nodo.getNombre()) > 0) {
            if (nodo.getDer() == null) {
                nodo.setDer(nuevoEquipo);
            } else {
                insertarRec(nodo.getDer(), nuevoEquipo);
            }
        } else {
            if (nodo.getIzq() == null) {
                nodo.setIzq(nuevoEquipo);
            } else {
                insertarRec(nodo.getIzq(), nuevoEquipo);
            }
        }
    }

    public Equipo obtenerEquipo(String nombre) {
        return obtenerEquipoRec(this.raiz, nombre);
    }

    private Equipo obtenerEquipoRec(Equipo nodo, String nombre) {
        if (nodo == null) {
            return null;
        }

        if (nodo.getNombre().equals(nombre)) {
            return nodo;
        }

        if (nombre.compareTo(nodo.getNombre()) < 0) {
            return obtenerEquipoRec(nodo.getIzq(), nombre); // Buscar en subarbol izquierdo
        } else {
            return obtenerEquipoRec(nodo.getDer(), nombre); // Buscar en subarbol derecho
        }
    }

}
