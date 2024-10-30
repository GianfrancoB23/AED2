package dominio;

import interfaz.Categoria;

import java.util.Objects;

public class Jugador implements Comparable<Jugador>{
    private String alias;
    private String nombre;
    private String apellido;
    private Categoria categoria;
    private Jugador izq;
    private Jugador der;

    public Jugador(String alias, String nombre, String apellido, Categoria categoria) {
        this.alias = alias;
        this.nombre = nombre;
        this.apellido = apellido;
        this.categoria = categoria;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Jugador getIzq() {
        return izq;
    }

    public void setIzq(Jugador izq) {
        this.izq = izq;
    }

    public Jugador getDer() {
        return der;
    }

    public void setDer(Jugador der) {
        this.der = der;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Jugador jugador = (Jugador) o;
        return Objects.equals(alias, jugador.alias) && Objects.equals(nombre, jugador.nombre) && Objects.equals(apellido, jugador.apellido) && categoria == jugador.categoria;
    }

    @Override
    public int hashCode() {
        return Objects.hash(alias, nombre, apellido, categoria);
    }

    @Override
    public int compareTo(Jugador o) {
        return this.alias.compareTo(o.getAlias());
    }

    //TODO Terminar el toString
    @Override
    public String toString() {
        return super.toString();
    }

}
