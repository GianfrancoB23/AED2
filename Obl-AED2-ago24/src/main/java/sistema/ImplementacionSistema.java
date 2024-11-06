package sistema;

import dominio.Equipos.ABBEquipos;
import dominio.Grafo.GrafoSucursales;
import dominio.Equipos.Equipo;
import dominio.Jugadores.ABBJugadores;
import dominio.Jugadores.Jugador;
import dominio.Jugadores.ResultadoBusquedaJugador;
import dominio.Sucursales.RetornoSucursales;
import dominio.Sucursales.Sucursal;
import interfaz.*;

public class ImplementacionSistema implements Sistema {

    // Gianfranco Bonanni - Numero de estudiante: 274029
    // Gabriel Moreno - Numero de estudiante: 274029

    private int maxSucursales;

    private ABBJugadores abbJugadores;
    private ABBEquipos abbEquipos;
    private GrafoSucursales sucursales;

    @Override
    public Retorno inicializarSistema(int maxSucursales) {
        if(maxSucursales<=3){
            return Retorno.error1("La cantidad de sucursales maxima es igual o menor a 3");
        }

        this.maxSucursales = maxSucursales;

        this.abbJugadores = new ABBJugadores();
        this.abbEquipos = new ABBEquipos();
        this.sucursales = new GrafoSucursales(maxSucursales);

        return Retorno.ok();
    }

    @Override
    public Retorno registrarJugador(String alias, String nombre, String apellido, Categoria categoria) {
        if (alias == null || alias.isEmpty() || nombre == null || nombre.isEmpty() ||
                apellido == null || apellido.isEmpty() || categoria == null) {
            return Retorno.error1("Parametro vacio o null");
        }
        if (abbJugadores.existe(alias)) {
            return Retorno.error2("Ya existe un jugador con ese alias");
        }

        Jugador nuevoJugador = new Jugador(alias, nombre, apellido, categoria);
        abbJugadores.insertar(nuevoJugador);

        return Retorno.ok();
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
        if(jugador.getCategoria().toString() != "PROFESIONAL"){
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

        if (sucursales.esLleno()) {
            return Retorno.error1("Máximo de sucursales alcanzadas");
        }
        if (codigo == null || codigo.isEmpty() || nombre == null || nombre.isEmpty()) {
            return Retorno.error2("Debe especificar código y nombre");
        }
        if (sucursales.existeSucursal(codigo)) {
            return Retorno.error3("Ya existe una sucursal de mismas caracteristicas.");
        }
        Sucursal sucursalNueva = new Sucursal(codigo, nombre);
        sucursales.agregarSucursal(sucursalNueva);
        return Retorno.ok("Se ha regitrado exitosamente.");
    }

    @Override
    public Retorno registrarConexion(String codigoSucursal1, String codigoSucursal2, int latencia) {
        if (latencia < 0) {
            return Retorno.error1("Latencia no puede ser menor a 0");
        }
        if(codigoSucursal1==null || codigoSucursal1.isEmpty() || codigoSucursal2==null || codigoSucursal2.isEmpty()){
            return Retorno.error2("Los parametros no pueden estar vacios o ser nulos.");
        }
        if (!sucursales.existeSucursal(codigoSucursal1) || !sucursales.existeSucursal(codigoSucursal2)) {
            return Retorno.error3("No existe sucursal registrada para uno de los códigos ingresados"); // sucursal no existe
        }
        if (sucursales.existeConexion(codigoSucursal1,codigoSucursal2)) {
            return Retorno.error4("Conexion ya existente entre ambas sucursales");
        }

        sucursales.agregarConexion(codigoSucursal1,codigoSucursal2,latencia);

        return Retorno.ok("Conexion registrada correctamente.");
    }

    @Override
    public Retorno actualizarConexion(String codigoSucursal1, String codigoSucursal2, int latencia) {
        if (latencia < 0) {
            return Retorno.error1("Latencia no puede ser menor a 0");
        }
        if(codigoSucursal1==null || codigoSucursal1.isEmpty() || codigoSucursal2==null || codigoSucursal2.isEmpty()){
            return Retorno.error2("Los parametros no pueden estar vacios o ser nulos.");
        }
        if (!sucursales.existeSucursal(codigoSucursal1) || !sucursales.existeSucursal(codigoSucursal2)) {
            return Retorno.error3("No existe sucursal registrada para uno de los códigos ingresados"); // sucursal no existe
        }

        if (!sucursales.existeConexion(codigoSucursal1,codigoSucursal2)) {
            return Retorno.error4("No existe conexion entre ambas sucursales");
        }

        sucursales.actualizarConexion(codigoSucursal1,codigoSucursal2,latencia);

        return Retorno.ok("Conexion actualizada correctamente");
    }

    @Override
    public Retorno analizarSucursal(String codigoSucursal) {
        if(codigoSucursal==null || codigoSucursal.isEmpty()){
            return Retorno.error1("El codigo de sucursal no puede estar vacio o ser nulo.");
        }
        if (!sucursales.existeSucursal(codigoSucursal)) {
            return Retorno.error2("La sucursal no existe.");
        }

        String resultado = sucursales.dfsSucursalCritica(codigoSucursal);

        return Retorno.ok(resultado);
    }


    @Override
    public Retorno sucursalesParaTorneo(String codigoSucursalAnfitriona, int latenciaLimite) {
        if(codigoSucursalAnfitriona==null || codigoSucursalAnfitriona.isEmpty()){
            return Retorno.error1("El codigo de sucursal no puede estar vacio o ser nulo.");
        }
        if (!sucursales.existeSucursal(codigoSucursalAnfitriona)) {
            return Retorno.error2("La sucursal no existe.");
        }
        if (latenciaLimite <= 0) {
            return Retorno.error3("Latencia debe ser mayor a 0");
        }

        RetornoSucursales resultado = sucursales.dijkstra(codigoSucursalAnfitriona, latenciaLimite);

        return Retorno.ok(resultado.getLatenciaMaxima(),resultado.getSucursales());
    }
}
