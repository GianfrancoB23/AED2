package abb;

import java.util.Comparator;

public class ComparadorPorCedula implements Comparator<Persona> {
    @Override
    public int compare(Persona o1, Persona o2) {
        return o1.getCedula().compareTo(o2.getCedula());
    }
}
