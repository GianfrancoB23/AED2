package grafo;

import cola.Cola;
import lista.*;

import java.util.Arrays;

public class Grafo {

    private int tope;
    private int cantidad;

    private String[] vertices;

    private Arista[][] matAdy;
    public Grafo(int cantMaxDeVertices, boolean esDirigido) {
        this.tope = cantMaxDeVertices;
        this.cantidad = 0;
        this.vertices = new String[tope];
        this.matAdy = new Arista[tope][tope];
        if(esDirigido){
            for (int i = 0; i < tope; i++) {
                for (int j = 0; j < tope; j++) {
                    matAdy[i][j] = new Arista();
                }
            }
        } else{
            for (int i = 0; i < tope; i++) {
                for (int j = i; j < tope; j++) {
                    Arista nueva = new Arista();
                    matAdy[i][j] = nueva;
                    matAdy[j][i] = nueva;
                }
            }
        }

    }

    public boolean esLleno() {
        return tope == cantidad;
    }

    public boolean esVacio() {
        return cantidad == 0;
    }

    // PRE: !esLleno()
    private int obtenerPosLibre() {
        for (int i = 0; i < tope; i++) {
            if(vertices[i] == null){
                return i;
            }
        }
        return -1;
    }

    private int obtenerPos(String vert) {
        for (int i = 0; i < tope; i++) {
            if(vert.equals(vertices[i])){
                return i;
            }
        }
        return -1;
    }

    // PRE: !esLleno && !existeVertice
    public void agregarVertice(String vert) {
        int pos = obtenerPosLibre();
        vertices[pos] = vert;
        cantidad++;
    }

    // PRE: existeVertice
    public void borrarVertice(String vert) {
        int pos = obtenerPos(vert);
        vertices[pos]=null;
        for (int k = 0; k < tope; k++) {
            matAdy[pos][k].setExiste(false);
            matAdy[k][pos].setExiste(false);
        }
        cantidad--;
    }

    public boolean existeVertice(String vert) {
        return obtenerPos(vert) != -1;
    }

    // existeVertice(origen) && existeVertice(destino) && !existeArista
    public void agregarArista(String origen, String destino, int peso) {
        int posOrigen = obtenerPos(origen); // "A" --> 0
        int posDestino =  obtenerPos(destino); // "B" --> 1
        matAdy[posOrigen][posDestino].setExiste(true);
        matAdy[posOrigen][posDestino].setPeso(peso);
    }

    // existeVertice(origen) && existeVertice(destino)
    public boolean existerArista(String origen, String destino) {
        int posOrigen = obtenerPos(origen);
        int posDestino =  obtenerPos(destino);
        return  matAdy[posOrigen][posDestino].isExiste();
    }

    // existeVertice(origen) && existeVertice(destino) && existeArista
    public void borrarArista(String origen, String destino) {
        int posOrigen = obtenerPos(origen);
        int posDestino =  obtenerPos(destino);
        matAdy[posOrigen][posDestino].setExiste(false);
        //matAdy[posOrigen][posDestino].setPeso(0);
    }

    public Lista<String> verticesAdyacentes(String vert) {
        Lista<String> lista = new ListaImp<>();
        int pos = obtenerPos(vert);
        for (int j = 0; j < tope; j++) {
            if(matAdy[pos][j].isExiste()){
                lista.insertar( vertices[j] );
            }
        }
        return lista;
    }

    // Pre: existeVertice(vert)
    public Lista<String> verticesIncidentes(String vert) {
        Lista<String> lista = new ListaImp<>();
        int pos = obtenerPos(vert);
        for (int i = 0; i < tope; i++) {
            if(matAdy[i][pos].isExiste()){
                lista.insertar( vertices[i] );
            }
        }
        return lista;
    }

    // Pre: existeVertice(vert)
    public void dfs(String vert){
        System.out.println("Inicio DFS en " + vert);
        int pos = obtenerPos(vert);
        boolean[] visitados = new boolean[tope]; // Todo en false
        dfsRec(pos, visitados);
    }

    private void dfsRec(int pos, boolean[] visitados){
        System.out.println( vertices[pos] );
        visitados[pos] = true;
        for (int j = 0; j < tope; j++) {
            if(matAdy[pos][j].isExiste() && !visitados[j]){
                dfsRec(j, visitados);
            }
        }
    }

    public void bfs(String vert){
        System.out.println("Inicio BFS en " + vert);
        int inicio = obtenerPos(vert);
        boolean[] visitados = new boolean[tope]; // Todo en false
        Cola<Integer> cola = new Cola<>();
        cola.encolar(inicio);
        visitados[inicio] = true;
        while (!cola.esVacia()){
            int pos = cola.desencolar();
            System.out.println( vertices[pos] );
            for (int j = 0; j < tope; j++) {
                if(matAdy[pos][j].isExiste() && !visitados[j]){
                    cola.encolar(j);
                    visitados[j] =true;
                }
            }
        }
    }

    public void bfsV2(String vert){
        System.out.println("Inicio BFS en " + vert);
        int inicio = obtenerPos(vert);
        boolean[] visitados = new boolean[tope]; // Todo en false
        Cola<Tupla> cola = new Cola<>();
        cola.encolar(new Tupla(inicio, 0));
        visitados[inicio] = true;
        while (!cola.esVacia()){
            Tupla tupla = cola.desencolar();
            int pos = tupla.getPos();
            int nivel = tupla.getNivel();
            System.out.println( vertices[pos]  + " - " + nivel);
            for (int j = 0; j < tope; j++) {
                if(matAdy[pos][j].isExiste() && !visitados[j]){
                    cola.encolar( new Tupla(j , nivel+1)  );
                    visitados[j] =true;
                }
            }
        }
    }


    /*
 *
 *
 * 	//inicializar estructuras

Marcar el origen con distancia cero.

Loop (cantidad de vertices):
1) Obtener el v�rtice no visitado de menor costo (si hay varios cualquiera)
2) Visitarlo
3) Evaluar si tengo que actualizar el costo de los adyacentes NO VISITADOS.
 */
    public int dijkstra(String vOrigen, String vDestino) {
        int posOrigen = obtenerPos(vOrigen);
        int posDestino = obtenerPos(vDestino);

        //inicializar estructuras
        boolean[] visitados = new boolean[tope];
        int[] costos = new int[tope];
        String[] anterior = new String[tope];
        for (int i = 0; i < tope; i++) {
            costos[i] = Integer.MAX_VALUE;
            anterior[i] = "-";
        }

        //Marcar el origen con distancia cero.
        costos[posOrigen] = 0;

        for (int v = 0; v < cantidad; v++) {
            //1) Obtener el v�rtice no visitado de menor costo (si hay varios cualquiera)
            int pos = obtenerVerticeNoVisitadoDeMenorCosto(visitados, costos);
            if(pos != -1){
                //2) Visitarlo
                visitados[pos] = true;

                //3) Evaluar si tengo que actualizar el costo de los adyacentes NO VISITADOS.
                for (int j = 0; j < tope; j++) {
                    if(matAdy[pos][j].isExiste() && !visitados[j] ){
                        int costoNuevo = costos[pos] + matAdy[pos][j].getPeso();
                        if(costoNuevo < costos[j]){
                            costos[j] = costoNuevo;
                            anterior[j] = vertices[pos];
                        }
                    }
                }
            }

        }
//        System.out.println(Arrays.toString(costos));
//        System.out.println(Arrays.toString(anterior));
        return costos[posDestino];
    }

    private int obtenerVerticeNoVisitadoDeMenorCosto(boolean[] visitados, int[] costos) {
        int pos = -1;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < tope; i++) {
            if( !visitados[i] && costos[i] < min ){
                min = costos[i];
                pos = i;
            }
        }
        return pos;
    }


}
