package sistema;

import dominio.ABBJugadores;
import dominio.Jugador;
import dominio.ResultadoBusquedaJugador;
import interfaz.*;

public class ImplementacionSistema implements Sistema {

    private String[] sucursales;
    private boolean[][] conexiones;
    private int maxSucursales;

    private ABBJugadores raizJugadores;

    @Override
    public Retorno inicializarSistema(int maxSucursales) {
        if(maxSucursales<=3){
            return Retorno.error1("La cantidad de sucursales maxima es igual o menor a 3");
        }

        this.sucursales = new String[maxSucursales];
        this.conexiones = new boolean[maxSucursales][maxSucursales];

        this.maxSucursales = maxSucursales;

        return Retorno.ok();
    }

    @Override
    public Retorno registrarJugador(String alias, String nombre, String apellido, Categoria categoria) {
        if (alias == null || alias.isEmpty() || nombre == null || nombre.isEmpty() ||
                apellido == null || apellido.isEmpty() || categoria == null) {
            return Retorno.error1("Parametro vacio o null");
        }
        if (buscarJugador(alias) != null) {
            return Retorno.error2("Ya existe un jugador con ese alias");
        }

        Jugador nuevoJugador = new Jugador(alias, nombre, apellido, categoria);
        raizJugadores.insertar(nuevoJugador);

        return Retorno.noImplementada();
    }

    @Override
    public Retorno buscarJugador(String alias) {
        if (alias == null || alias.isEmpty()) {
            return Retorno.error1("El alias es nulo o vacio");
        }
        // Inicializar contador de nodos recorridos
        int[] contador = {0};

        // Busca el jugador en el árbol
        ResultadoBusquedaJugador resultado = raizJugadores.buscar(alias);
        Jugador jugadorEncontrado = resultado.getJugador();
        int nodosRecorridos = resultado.getNodosRecorridos();

        if (jugadorEncontrado == null) {
            return Retorno.error2("No se encontro jugador con ese alias, se recorrieron " + nodosRecorridos + " nodos.");
        }

        // Formateo la informacion para mostrar
        String valorString = jugadorEncontrado.getAlias() + ";" +
                jugadorEncontrado.getNombre() + ";" +
                jugadorEncontrado.getApellido() + ";" +
                jugadorEncontrado.getCategoria().toString();

        return Retorno.ok(nodosRecorridos, valorString);
    }


    @Override
    public Retorno listarJugadoresAscendente() {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno listarJugadoresPorCategoria(Categoria unaCategoria) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno registrarEquipo(String nombre, String manager) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno agregarJugadorAEquipo(String nombreEquipo, String aliasJugador) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno listarJugadoresDeEquipo(String nombreEquipo) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno listarEquiposDescendente() {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno registrarSucursal(String codigo, String nombre) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno registrarConexion(String codigoSucursal1, String codigoSucursal2, int latencia) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno actualizarConexion(String codigoSucursal1, String codigoSucursal2, int latencia) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno analizarSucursal(String codigoSucursal) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno sucursalesParaTorneo(String codigoSucursalAnfitriona, int latenciaLimite) {
        return Retorno.noImplementada();
    }
}
