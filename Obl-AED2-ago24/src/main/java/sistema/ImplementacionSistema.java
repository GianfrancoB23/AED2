package sistema;

import dominio.ABBEquipos;
import dominio.ABBgenerico;
import dominio.Conexion;
import dominio.Equipo;
import dominio.Jugadores.ABBJugadores;
import dominio.Jugadores.Jugador;
import dominio.Jugadores.ResultadoBusquedaJugador;
import dominio.Sucursales.Sucursal;
import interfaz.*;

public class ImplementacionSistema implements Sistema {

    private String[] sucursales;
    private boolean[][] conexiones;
    private int maxSucursales;

    private ABBgenerico<Sucursal> abbSucursales;
    //private ABBgenerico<Conexion> abbConexiones;
    private ABBJugadores abbJugadores;
    private ABBEquipos abbEquipos;

    @Override
    public Retorno inicializarSistema(int maxSucursales) {
        if(maxSucursales<=3){
            return Retorno.error1("La cantidad de sucursales maxima es igual o menor a 3");
        }

        this.sucursales = new String[maxSucursales];
        this.conexiones = new boolean[maxSucursales][maxSucursales];

        this.maxSucursales = maxSucursales;

        this.abbJugadores = new ABBJugadores();
        this.abbEquipos = new ABBEquipos();
        this.abbSucursales = new ABBgenerico<>();

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
        abbJugadores.insertar(nuevoJugador);

        return Retorno.noImplementada();
    }

    @Override
    public Retorno buscarJugador(String alias) {
        if (alias == null || alias.isEmpty()) {
            return Retorno.error1("El alias es nulo o vacio");
        }

        // Busca el jugador en el arbol
        ResultadoBusquedaJugador resultado = abbJugadores.obtenerJugador(alias);
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
        return Retorno.ok(abbJugadores.inOrden());
    }

    @Override
    public Retorno listarJugadoresPorCategoria(Categoria unaCategoria) {
        return Retorno.ok(abbJugadores.buscarXCat(unaCategoria));
    }

    @Override
    public Retorno registrarEquipo(String nombre, String manager) {
        if(nombre == null || manager == null || nombre.isEmpty() || manager.isEmpty()){
            return Retorno.error1("Uno de los parametros es nulo o esta vacio.");
        }
        if(abbEquipos.existe(nombre)){
            return Retorno.error2("El equipo ya se encuentra registrado.");
        }
        Equipo equipoNuevo = new Equipo(nombre, manager);
        abbEquipos.insertar(equipoNuevo);

        return Retorno.ok("Se ha ingresado el equipo " + equipoNuevo.getNombre());
    }

    @Override
    public Retorno agregarJugadorAEquipo(String nombreEquipo, String aliasJugador) {
        if(nombreEquipo == null || aliasJugador == null ||nombreEquipo.isEmpty() || aliasJugador.isEmpty()){
            return Retorno.error1("Uno de los parametros esta vacio o es nulo.");
        }
        if(!abbEquipos.existe(nombreEquipo)){
            return Retorno.error2("No existe equipo con ese nombre.");
        }
        if(!abbJugadores.existe(aliasJugador)){
            return Retorno.error3("No existe jugador con ese alias.");
        }
        Equipo equipo = abbEquipos.obtenerEquipo(nombreEquipo);
        if(equipo.getCtdIntegrantes()==5){
            return Retorno.error4("El equipo ya tiene 5 integrantes.");
        }
        Jugador jugador = abbJugadores.obtenerJugador(aliasJugador).getJugador();
        if(jugador.getCategoria().toString() != "Profesional"){
            return Retorno.error5("El jugador no tiene categoria 'Profesional'");
        }
        if(abbJugadores.obtenerJugador(aliasJugador).getJugador().getEquipo() != null){
            return Retorno.error6("El jugador ya pertenece a otro equipo");
        }

        // Seteo el equipo en el jugador
        jugador.setEquipo(abbEquipos.obtenerEquipo(nombreEquipo));

        // Agrego el jugador al equipo
        equipo.jugadores.insertar(abbJugadores.obtenerJugador(aliasJugador).getJugador());
        equipo.sumarIntegrante();

        return Retorno.ok("El jugador " + jugador.getAlias() + " ha sido agregado al equipo " + equipo.getNombre() + " correctamente.");
    }

    @Override
    public Retorno listarJugadoresDeEquipo(String nombreEquipo) {
        if(nombreEquipo == null || nombreEquipo.isEmpty()){
            return Retorno.error1("El nombre del equipo esta vacio o es nulo.");
        }
        if(!abbEquipos.existe(nombreEquipo)){
            return Retorno.error2("No existe un equipo con ese nombre");
        }

        String jugadores = abbEquipos.obtenerEquipo(nombreEquipo).jugadores.inOrden();

        return Retorno.ok(jugadores);
    }

    @Override
    public Retorno listarEquiposDescendente() {
        String equipos = abbEquipos.inOrden();
        return Retorno.ok(equipos);
    }

    @Override
    public Retorno registrarSucursal(String codigo, String nombre) {

        if (abbSucursales.contarNodos() >= maxSucursales) {
            return Retorno.error1("Máximo de sucursales alcanzadas");
        }
        if (codigo == null || codigo.isEmpty() || nombre == null || nombre.isEmpty()) {
            return Retorno.error2("Debe especificar código y nombre");
        }
        Sucursal nuevaSucursal = new Sucursal(codigo, nombre);
        if (abbSucursales.buscar(nuevaSucursal) != null) {
            return Retorno.error3("Ya existe una sucursal de mismas caracteristicas.");
        }
        abbSucursales.insertar(nuevaSucursal);
        return Retorno.ok();
    }

    @Override
    public Retorno registrarConexion(String codigoSucursal1, String codigoSucursal2, int latencia) {
        if (latencia < 0) {
            return Retorno.error1("Latencia no puede ser menor a 0");
        }

        Sucursal sucursal1 = abbSucursales.buscar(new Sucursal(codigoSucursal1, ""));
        Sucursal sucursal2 = abbSucursales.buscar(new Sucursal(codigoSucursal2, ""));
        if (sucursal1 == null || sucursal2 == null) {
            return Retorno.error3("No existe sucursal registrada para uno de los códigos ingresados"); // sucursal no existe
        }

        Conexion conexion1 = new Conexion(codigoSucursal2, latencia);
        Conexion conexion2 = new Conexion(codigoSucursal1, latencia);

        if (sucursal1.getConexiones().buscar(conexion1) != null) {
            return Retorno.error4("Conexion ya existente entre ambos puntos"); // conexión ya existe
        }

        sucursal1.getConexiones().insertar(conexion1);
        sucursal2.getConexiones().insertar(conexion2);

        return Retorno.ok();
    }

    @Override
    public Retorno actualizarConexion(String codigoSucursal1, String codigoSucursal2, int latencia) {
        if (latencia < 0) {
            return Retorno.error1("Latencia no puede ser menor a 0");
        }
        Sucursal sucursal1 = abbSucursales.buscar(new Sucursal(codigoSucursal1, ""));
        Sucursal sucursal2 = abbSucursales.buscar(new Sucursal(codigoSucursal2, ""));
        if (sucursal1 == null || sucursal2 == null) {
            return Retorno.error3("Una de las sucursales ingreasadas no existe");
        }

        Conexion conexion1 = sucursal1.getConexiones().buscar(new Conexion(codigoSucursal2, 0));
        Conexion conexion2 = sucursal2.getConexiones().buscar(new Conexion(codigoSucursal1, 0));
        if (conexion1 == null || conexion2 == null) {
            return Retorno.error4("No existe una conexion entre las dos sucursales"); // conexión no existe
        }
        conexion1.setLatencia(latencia);
        conexion2.setLatencia(latencia);
        return Retorno.ok();
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
