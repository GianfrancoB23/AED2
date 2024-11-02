package dominio.Sucursales;

import dominio.ABBgenerico;
import dominio.Conexion;
import dominio.Equipo;
import dominio.Jugadores.Jugador;
import interfaz.Categoria;

public class Sucursal implements Comparable<Sucursal> {
    private String codigo;
    private String nombre;
    private Sucursal izq;
    private Sucursal der;
    private ABBgenerico<Conexion> conexiones;


    public Sucursal(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.conexiones = new ABBgenerico<>();
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Sucursal getIzq() {
        return izq;
    }

    public void setIzq(Sucursal izq) {
        this.izq = izq;
    }

    public Sucursal getDer() {
        return der;
    }

    public void setDer(Sucursal der) {
        this.der = der;
    }

    public ABBgenerico<Conexion> getConexiones() {
        return conexiones;
    }


    @Override
    public int compareTo(Sucursal otraSucursal) {
        return this.codigo.compareTo(otraSucursal.codigo);
    }
}
