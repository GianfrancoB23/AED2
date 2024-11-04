package dominio.Sucursales;

import dominio.ABBgenerico;

public class Sucursal implements Comparable<Sucursal> {
    private String codigo;
    private String nombre;
    private int pos;


    public Sucursal(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
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

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    @Override
    public int compareTo(Sucursal otraSucursal) {
        return this.codigo.compareTo(otraSucursal.codigo);
    }
}
