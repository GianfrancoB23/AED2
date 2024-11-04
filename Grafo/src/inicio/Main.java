package inicio;

import cola.*;
import grafo.*;
import lista.Lista;

public class Main {

	public static void main(String[] args) {
		
		Grafo g = new Grafo(7, false);
		
		g.agregarVertice("A");
		g.agregarVertice("B");
		g.agregarVertice("C");
		g.agregarVertice("D");
		g.agregarVertice("E");
		g.agregarVertice("F");
		g.agregarVertice("G");

		g.agregarArista("A", "B", 1);
		g.agregarArista("A", "E", 1);
		g.agregarArista("A", "F", 1);
		g.agregarArista("B", "C", 1);
		g.agregarArista("E", "C", 1);
		g.agregarArista("E", "G", 1);
		g.agregarArista("C", "D", 1);
		g.agregarArista("G", "D", 1);

		g.dfs("D");
		//g.bfsV2("A");




	}

}
