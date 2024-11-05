package dominio.Grafo;

import dominio.Lista.ListaImp;
import dominio.Sucursales.Conexion;
import dominio.Sucursales.Sucursal;
import interfaz.Lista;

public class GrafoSucursales {
    private int tope;
    private int cantidad;

    private Sucursal[] sucursales;
    private Conexion[][] matAdy;

    public GrafoSucursales(int cantMaxDeSucursales) {
        this.tope = cantMaxDeSucursales;
        this.cantidad = 0;
        this.sucursales = new Sucursal[tope];
        this.matAdy = new Conexion[tope][tope];
        for (int i = 0; i < tope; i++) {
            for (int j = i; j < tope; j++) {
                Conexion nueva = new Conexion();
                matAdy[i][j] = nueva;
                matAdy[j][i] = nueva;
            }
        }
    }

    public boolean esLleno() {
        return tope == cantidad;
    }

    public boolean esVacio() {
        return cantidad == 0;
    }

    private int obtenerPosLibre() {
        for (int i = 0; i < tope; i++) {
            if(sucursales[i] == null){
                return i;
            }
        }
        return -1;
    }

    private int obtenerPos(String codSucu) {
        for (int i = 0; i < tope; i++) {
            if(codSucu.equals(sucursales[i].getCodigo())){
                return i;
            }
        }
        return -1;
    }

    public void agregarSucursal(Sucursal sucursal) {
        int pos = obtenerPosLibre();
        sucursal.setPos(pos);
        sucursales[pos] = sucursal;
        cantidad++;
    }

    public void reingresarSucursal(Sucursal sucursal) {
        sucursales[sucursal.getPos()] = sucursal;
        cantidad++;
    }

    // PRE: existeSucursal
    public void borrarSucursal(String codSucu) {
        int pos = obtenerPos(codSucu);
        sucursales[pos]=null;
        for (int k = 0; k < tope; k++) {
            matAdy[pos][k].setExiste(false);
            matAdy[k][pos].setExiste(false);
        }
        cantidad--;
    }

    public boolean existeSucursal(String codSucu) {
        return obtenerPos(codSucu) != -1;
    }

    public Sucursal obtenerSucursal(String codSucu) {
        for (int i = 0; i < tope; i++) {
            if(codSucu.equals(sucursales[i].getCodigo())){
                return sucursales[i];
            }
        }
        return null;
    }

    // existeSucursal(origen) && existeSucursal(destino) && !existeConexion
    public void agregarConexion(String origen, String destino, int peso) {
        int posOrigen = obtenerPos(origen); // "A" --> 0
        int posDestino =  obtenerPos(destino); // "B" --> 1
        matAdy[posOrigen][posDestino].setExiste(true);
        matAdy[posDestino][posOrigen].setExiste(true);
        matAdy[posOrigen][posDestino].setLatencia(peso);
        matAdy[posDestino][posOrigen].setLatencia(peso);
    }

    // existeSucursal(origen) && existeSucursal(destino) && existeConexion
    public void actualizarConexion(String origen, String destino, int peso) {
        int posOrigen = obtenerPos(origen); // "A" --> 0
        int posDestino =  obtenerPos(destino); // "B" --> 1
        matAdy[posOrigen][posDestino].setLatencia(peso);
        matAdy[posDestino][posOrigen].setLatencia(peso);
    }

    // existeSucursal(origen) && existeSucursal(destino)
    public boolean existeConexion(String origen, String destino) {
        int posOrigen = obtenerPos(origen);
        int posDestino =  obtenerPos(destino);
        return  matAdy[posOrigen][posDestino].isExiste();
    }

    public Lista<String> sucursalesAdyacentes(String codSucu) {
        Lista<String> lista = new ListaImp<>();
        int pos = obtenerPos(codSucu);
        for (int j = 0; j < tope; j++) {
            if(matAdy[pos][j].isExiste()){
                lista.insertar(sucursales[j].getCodigo());
            }
        }
        return lista;
    }

    // Pre: existeSucursal(vert)
    public Lista<String> SucursalesIncidentes(String vert) {
        Lista<String> lista = new ListaImp<>();
        int pos = obtenerPos(vert);
        for (int i = 0; i < tope; i++) {
            if(matAdy[i][pos].isExiste()){
                lista.insertar(sucursales[i].getCodigo());
            }
        }
        return lista;
    }

    // Tengo que pasarle la sucu
    // Recorrer desde la sucu DFS
    // Ver la cantidad en array de visitados
    // Recorrer sucu desde otro lugar pero marcando ya codSucursal como visitado
    // Ver si la cantidad de visitados es < que visitados-1

    public String dfsSucursalCritica(String codSucursal) {
        boolean[] visitados = new boolean[tope];
        boolean[] visitados2 = new boolean[tope];

        for (int i = 0; i < tope; i++) {
            if (sucursales[i] != null && !visitados[i]) {
                dfsRec(i, visitados);
            }
        }

        int pos = obtenerPos(codSucursal);
        visitados2[pos] = true;
        for (int i = 0; i < tope; i++) {
            if (sucursales[i] != null && !visitados2[i]) {
                dfsRec(i, visitados2);
            }
        }

        if(visitados.length!=visitados2.length){
            return "SI";
        }else{
            return "NO";
        }

    }

    private void dfsRec(int pos, boolean[] visitados) {
        visitados[pos] = true;
        for (int j = 0; j < tope; j++) {
            if (matAdy[pos][j].isExiste() && !visitados[j]) {
                dfsRec(j, visitados);
            }
        }
    }

    public String dijkstra(String vOrigen, int latenciaLimite) {
        int posOrigen = obtenerPos(vOrigen);
        ListaImp<Sucursal> listaSucursalesConLatenciaValida = new ListaImp<>();

        // Inicializar estructuras
        boolean[] visitados = new boolean[tope];
        int[] costos = new int[tope];

        for (int i = 0; i < tope; i++) {
            costos[i] = Integer.MAX_VALUE;
        }
        costos[posOrigen] = 0;

        for (int v = 0; v < tope - 1; v++) {
            //1) Obtener el vertice no visitado de menor costo (si hay varios cualquiera)
            int pos = obtenerVerticeNoVisitadoDeMenorCosto(visitados, costos);
            if (pos != -1) {
                // Visitarlo
                visitados[pos] = true;

                // Actualizar los costos de los adyacentes no visitados
                for (int j = 0; j < tope; j++) {
                    if (matAdy[pos][j].isExiste() && !visitados[j]) {
                        int costoNuevo = costos[pos] + matAdy[pos][j].getLatencia();
                        if (costoNuevo < costos[j]) {
                            costos[j] = costoNuevo;
                        }
                    }
                }
            }
        }

        // Filtrar las sucursales que cumplen con la latencia y armo el String
        for (int i = 0; i < tope; i++) {
            if (costos[i] <= latenciaLimite && i != posOrigen) { // i != excluye a si mismo
                Sucursal sucursal = sucursales[i];
                listaSucursalesConLatenciaValida.insertar(sucursal);
            }
        }

        listaSucursalesConLatenciaValida.ordenar();

        return listaSucursalesConLatenciaValida.convertirListaAString();
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
