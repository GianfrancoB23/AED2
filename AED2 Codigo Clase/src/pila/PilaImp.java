package pila;

public class PilaImp implements Pila{

    private Nodo inicio;

    public PilaImp() {
        this.inicio = null;
    }

    @Override
    public void push(int dato) {
      /*  Nodo nuevo = new Nodo(dato);
        nuevo.setSig(inicio);
        inicio = nuevo; */
        inicio = new Nodo(dato, inicio);
    }

    @Override
    public int top() {
        return inicio.getDato();
    }

    @Override
    public int pop() {
        int dato = inicio.getDato();
        inicio = inicio.getSig();
        return dato;
    }

    @Override
    public boolean estaVacia() {
        return inicio == null;
    }

    @Override
    public boolean estaLlena() {
        return false;
    }


}
