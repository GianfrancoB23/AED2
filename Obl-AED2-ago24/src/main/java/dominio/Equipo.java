package dominio;

public class Equipo implements Comparable<Equipo> {
    private String nombre;
    private String manager;
    //private List<Jugador> jugadores;

    private Equipo izq;
    private Equipo der;

    public String getNombre() {return nombre;}
    public String getManager() {return manager;}
    public Equipo getIzq() {return izq;}
    public Equipo getDer() {return der;}

    public void setNombre(String name) {this.nombre = name;}
    public void setManager(String name) {this.manager = name;}
    public void setIzq(Equipo equipo) {this.izq = equipo;}
    public void setDer(Equipo equipo) {this.der = equipo;}

    @Override
    public boolean equals(Object eq) {
        if (this == eq) return true;
        if (eq == null || getClass() != o.getClass()) return false;
        Equipo aux = (Equipo) eq;
        return Objects.equals(nombre, aux.nombre) && Objects.equals(manager, aux.manager);
    }

    @Override
    public int compareTo(Equipo eq) {return this.nombre.compareTo(eq.getNombre());}
}
