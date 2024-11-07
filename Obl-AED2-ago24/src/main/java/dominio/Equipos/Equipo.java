package dominio.Equipos;

import dominio.ABB;
import dominio.Jugadores.ABBJugadores;
import dominio.Jugadores.Jugador;

import java.util.Objects;

public class Equipo implements Comparable<Equipo> {
    private String nombre;
    private String manager;
    private int ctdIntegrantes;
    public ABB<Jugador> jugadores;

    private Equipo izq;
    private Equipo der;

    public Equipo(String nombre, String manager) {
        this.nombre = nombre;
        this.manager = manager;
        this.ctdIntegrantes = 0;
        this.jugadores = new ABB<>();
        this.izq = null;
        this.der = null;
    }

    public String getNombre() {return nombre;}
    public String getManager() {return manager;}
    public Equipo getIzq() {return izq;}
    public Equipo getDer() {return der;}
    public int getCtdIntegrantes(){return ctdIntegrantes;}

    public void setNombre(String name) {this.nombre = name;}
    public void setManager(String name) {this.manager = name;}
    public void setIzq(Equipo equipo) {this.izq = equipo;}
    public void setDer(Equipo equipo) {this.der = equipo;}

    public void sumarIntegrante(){this.ctdIntegrantes++;}
    public void restarIntegrante(){this.ctdIntegrantes--;}

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, manager, izq, der);
    }

    @Override
    public int compareTo(Equipo eq) {return this.nombre.compareTo(eq.getNombre());}

    @Override
    public String toString() {
        return this.getNombre() + ";" + this.getManager() + ";" + this.getCtdIntegrantes();
    }
}
