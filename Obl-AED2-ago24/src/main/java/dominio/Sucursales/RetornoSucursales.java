package dominio.Sucursales;

// Clase creada para el retorno de sucursalesParaTorneo
public class RetornoSucursales {
    private int latenciaMaxima;
    private String sucursales;

    public RetornoSucursales() {
        this.latenciaMaxima = 0;
        this.sucursales = null;
    }

    public int getLatenciaMaxima() {
        return latenciaMaxima;
    }

    public void setLatenciaMaxima(int latenciaMaxima) {
        this.latenciaMaxima = latenciaMaxima;
    }

    public String getSucursales() {
        return sucursales;
    }

    public void setSucursales(String sucursales) {
        this.sucursales = sucursales;
    }
}
