package inicio;

import ab.AB;
import ab.Nodo;

public class MainAB {
    public static void main(String[] args) {
        Nodo subArbolDer = new Nodo(10, null,new Nodo(4) );
        Nodo raizAux = new Nodo(8, new Nodo(2), subArbolDer);
        AB arbol = new AB(raizAux);

        Nodo subArbolDer1 = new Nodo(10, null,new Nodo(4) );
        Nodo raizAux2 = new Nodo(8, new Nodo(2), subArbolDer1);
        AB arbol2 = new AB(raizAux2);

        int cantNodos = arbol.cantNodos();
        System.out.println("cantNodos = " + cantNodos);

        int cantHojas = arbol.cantHojas();
        System.out.println("cantHojas = " + cantHojas);

        int altura = arbol.altura();
        System.out.println("altura = " + altura);

        boolean pares = arbol.todosParesV2();
        System.out.println("pares = " + pares);

        boolean iguales = arbol.iguales(arbol2);
        System.out.println("iguales = " + iguales);

        int ctdMayores = arbol.RetornoCtd(5);
        System.out.println("mayores = " + ctdMayores);
    }
}
