package pila;

public interface Pila {

    //pre !estaLlena
    void push(int dato);

    //pre !estaVacia
    int top();

    //pre !estaVacia
    int pop();

    boolean estaVacia();

    boolean estaLlena();

}
