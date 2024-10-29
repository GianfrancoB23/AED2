package abb;

import java.util.Objects;

public class Persona implements Comparable<Persona> {
    private String nombre;
    private String cedula;
    private int edad;
    private int numero;

    public Persona(String nombre, String cedula, int edad, int numero) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.edad = edad;
        this.numero = numero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Persona persona = (Persona) o;
        return edad == persona.edad && numero == persona.numero && Objects.equals(nombre, persona.nombre) && Objects.equals(cedula, persona.cedula);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, cedula, edad, numero);
    }

    @Override
    public int compareTo(Persona o) {
        return this.numero - o.numero;
    }

    @Override
    public String toString() {
        return "{" +
                nombre + " " + cedula + " " + edad + " " + numero +
                '}';
    }
}
