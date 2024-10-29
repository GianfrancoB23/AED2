package inicio;

import ab.AB;
import ab.Nodo;

public class MainAB {
    public static void main(String[] args) {
        Nodo subArbolDer = new Nodo(10, null,new Nodo(4) );
        Nodo raizAux = new Nodo(8, new Nodo(2), subArbolDer);
        AB arbol = new AB(raizAux);
        int cantNodos = arbol.cantNodos();
        System.out.println("cantNodos = " + cantNodos);
        int cantHojas = arbol.cantHojas();
        System.out.println("cantHojas = " + cantHojas);
        int altura = arbol.altura();
        System.out.println("altura = " + altura);
        boolean pares = arbol.todosPares();
        System.out.println("pares = " + pares);

    }
}
