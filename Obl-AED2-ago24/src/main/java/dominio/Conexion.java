package dominio;

import dominio.Sucursales.Sucursal;

public class Conexion implements Comparable<Conexion> {
    private String codigoDestino;
    private int latencia;


    public Conexion(String codigoDestino, int latencia) {
        this.codigoDestino = codigoDestino;
        this.latencia = latencia;
    }

    public String getCodigoDestino() {
        return codigoDestino;
    }

    public int getLatencia() {
        return latencia;
    }

    @Override
    public int compareTo(Conexion otraConexion) {
        return this.codigoDestino.compareTo(otraConexion.codigoDestino);
    }

}
