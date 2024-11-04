package dominio.Sucursales;

public class Conexion implements Comparable<Conexion> {
    private String codigoDestino;
    private int latencia;
    private boolean existe;


    public Conexion(String codigoDestino, int latencia) {
        this.codigoDestino = codigoDestino;
        this.latencia = latencia;
        this.existe = true;
    }
    
    public Conexion(){
        this.codigoDestino = null;
        this.latencia = 0;
        this.existe = false;
    }

    public boolean isExiste() {
        return existe;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
    }

    public String getCodigoDestino() {
        return codigoDestino;
    }

    public void setCodigoDestino(String codigoDestino) {
        this.codigoDestino = codigoDestino;
    }

    public int getLatencia() {
        return latencia;
    }

    public void setLatencia(int latencia) {
        this.latencia = latencia;
    }

    @Override
    public int compareTo(Conexion otraConexion) {
        return this.codigoDestino.compareTo(otraConexion.codigoDestino);
    }

}
