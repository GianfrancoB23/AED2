package pila;

public interface Pila {
    // pre: !estaLlena()
    public void push(int dato);
    public int top();
    public int pop();
    public boolean estaVacia();
    public boolean estaLlena();

}
