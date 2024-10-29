package pila;

public class PilaImpArray implements Pila{

    private int[] datos;
    private int indice;

    public PilaImpArray(int cantMax) {
        datos = new int[cantMax];
        indice = -1;
    }

    @Override
    public void push(int dato) {
        //indice++;
        datos[++indice] = dato;  //Primero se incrementa el indice
    }

    @Override
    public int top() {
        return datos[indice];
    }

    @Override
    public int pop() {

        return datos[indice--];
    }

    @Override
    public boolean estaVacia() {
        return indice == -1 ;
    }

    @Override
    public boolean estaLlena() {
        return indice == datos.length-1;
    }
}
