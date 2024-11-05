package dominio.Lista;

import dominio.Sucursales.Sucursal;
import interfaz.Lista;

import java.util.Iterator;

public class ListaImp<T extends Comparable<T>> implements Lista<T> {

    protected NodoLista<T> inicio;
    protected int largo;

    public ListaImp() {
        this.inicio = null;
        this.largo = 0;
    }

    @Override
    public void insertar(T dato) {
        inicio = new NodoLista<T>(dato, inicio);
        largo++;
    }

    public NodoLista<T> getNodo() {
        return inicio;
    }

    @Override
    public int largo() {
        return largo;
    }

    @Override
    public boolean esVacia() {
        return largo == 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            private NodoLista<T> aux = inicio;

            @Override
            public boolean hasNext() {
                return aux != null;
            }

            @Override
            public T next() {
                T dato = aux.getDato();
                aux = aux.getSig();
                return dato;
            }

            @Override
            public void remove(){
            }

        };
    }

    private class NodoLista<T> {

        private T dato;
        private NodoLista<T> sig;

        public NodoLista(T dato) {
            this.dato = dato;
            this.sig = null;
        }

        public NodoLista(T dato, NodoLista<T> sig) {
            this.dato = dato;
            this.sig = sig;
        }

        public T getDato() {
            return dato;
        }

        public void setDato(T dato) {
            this.dato = dato;
        }

        public NodoLista<T> getSig() {
            return sig;
        }

        public void setSig(NodoLista<T> sig) {
            this.sig = sig;
        }


        @Override
        public String toString() {
            return dato.toString();
        }

    }

    public void ordenar() {
        if (inicio == null || inicio.getSig() == null) {
            // La lista es vacia o tiene un solo elemento
            return;
        }

        boolean huboIntercambios;
        do {
            NodoLista<T> actual = (NodoLista<T>) inicio;
            NodoLista<T> siguiente = (NodoLista<T>) inicio.getSig();
            huboIntercambios = false;

            while (siguiente != null) {
                // Comparar los elementos si T es una instancia de Comparable
                if (actual.getDato().compareTo(siguiente.getDato()) > 0) {
                    // Intercambiar los datos de los nodos si están en el orden incorrecto
                    T temp = actual.getDato();
                    actual.setDato(siguiente.getDato());
                    siguiente.setDato(temp);
                    huboIntercambios = true;
                }
                actual = siguiente;
                siguiente = siguiente.getSig();
            }
        } while (huboIntercambios);
    }

    public String convertirListaAString() {
        String resultado = "";
        NodoLista<T> actual = inicio;

        while (actual != null) {
            // Asumimos que T es Sucursal, así que podemos hacer un cast
            Sucursal sucursal = (Sucursal) actual.getDato();
            resultado += sucursal.getCodigo()+";"+sucursal.getNombre()+"|";
            actual = actual.getSig();
        }

        return resultado;
    }

}
