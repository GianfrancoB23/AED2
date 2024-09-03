package pilaGenerica;

public interface PilaG<T> {

    //pre !estaLlena
    void push(T dato);

    //pre !estaVacia
    T top();

    //pre !estaVacia
    T pop();

    boolean estaVacia();

    boolean estaLlena();
}
