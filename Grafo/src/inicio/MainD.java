package inicio;

import grafo.Grafo;

public class MainD {
    public static void main(String[] args) {
        Grafo g = new Grafo(8, false);

        g.agregarVertice("A");
        g.agregarVertice("B");
        g.agregarVertice("C");
        g.agregarVertice("D");
        g.agregarVertice("E");
        g.agregarVertice("F");
        g.agregarVertice("G");
        g.agregarVertice("H");

        g.agregarArista("A", "D", 2);
        g.agregarArista("A", "C", 2);
        g.agregarArista("C", "F", 4);
        g.agregarArista("D", "F", 9);
        g.agregarArista("D", "H", 8);
        g.agregarArista("F", "G", 3);
        g.agregarArista("G", "H", 7);
        g.agregarArista("G", "E", 2);
        g.agregarArista("H", "B", 1);
        g.agregarArista("E", "B", 4);

        System.out.println(g.dijkstra("D", "E"));


    }
}
