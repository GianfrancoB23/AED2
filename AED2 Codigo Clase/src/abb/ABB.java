package abb;


import java.util.Comparator;

public class ABB<T extends Comparable<T>> {
    private Nodo<T> raiz;
    private Comparator<T> comparador;

    public ABB() {
        this.raiz = null;
    }

    public ABB(Comparator<T> comp) {
        this.raiz = null;
    }

    public void agregar(T dato) {
        if (this.raiz == null) {
            this.raiz = new Nodo<>(dato);
        } else {
            agregar(this.raiz, dato);
        }
    }

    private void agregar(Nodo<T> nodo, T dato) {
        if (dato.compareTo(nodo.getDato()) > 0) {
            //derecha
            if (nodo.getDer() == null) {
                nodo.setDer(new Nodo<>(dato));
            } else {
                agregar(nodo.getDer(), dato);
            }
        } else {
            //izquierda
            if (nodo.getIzq() == null) {
                nodo.setIzq(new Nodo<>(dato));
            } else {
                agregar(nodo.getIzq(), dato);
            }
        }
    }

    public T obtener(T dato){
        return obtener(this.raiz, dato);
    }

    private T obtener(Nodo<T> nodo, T dato) {
        if(nodo == null){
            return null;
        }
        if(nodo.getDato().equals(dato)){
            return nodo.getDato();
        }
        if(dato.compareTo(nodo.getDato()) > 0){
            return obtener(nodo.getDer(), dato);
        }else{
            return obtener(nodo.getIzq (), dato);
        }
    }

    public boolean pertenece(T dato) {
        return pertenece(this.raiz, dato);
    }

    private boolean pertenece(Nodo<T> nodo, T dato) {
        if (nodo == null) {
            return false;
        }
        if (nodo.getDato().equals(dato)) {
            return true;
        }
        if(dato.compareTo(nodo.getDato()) > 0){
            return pertenece(nodo.getDer(), dato);
        }else{
            return pertenece(nodo.getIzq(), dato);
        }
    }

    public void mostrarElementos(int nivel){
        mostrarElementos(this.raiz, nivel, 0);
    }

    private void mostrarElementos(Nodo<T> nodo, int nivel, int nivelActual) {
        if(nodo != null){
            if(nivel == nivelActual){
                System.out.println(nodo.getDato());
            }
            if(nivel > nivelActual){
                mostrarElementos(nodo.getDer(), nivel, nivelActual+1);
                mostrarElementos(nodo.getIzq(), nivel, nivelActual+1);
            }
        }
    }

    public void inOrden() {
        inOrden(this.raiz);
        System.out.println();
    }

    private void inOrden(Nodo<T> nodo) { //izq - nodo - der
        if (nodo != null) {
            inOrden(nodo.getIzq());
            System.out.print(nodo.getDato() + " ");
            inOrden(nodo.getDer());
        }
    }






}
