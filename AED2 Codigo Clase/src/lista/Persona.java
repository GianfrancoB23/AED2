package lista;

import java.util.Objects;

public class Persona {
    private String nombre;
    private String cedula;


    public Persona(String cedula) {
        this.cedula = cedula;
    }

    public Persona(String nombre, String cedula) {
        this.nombre = nombre;
        this.cedula = cedula;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Persona persona = (Persona) o;
        return Objects.equals(cedula, persona.cedula);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cedula);
    }
}
