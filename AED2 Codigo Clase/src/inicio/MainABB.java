package inicio;

import abb.ABB;
import abb.Persona;


public class MainABB {
    public static void main(String[] args) {
        ABB<Persona> abb = new ABB<>();
        abb.agregar(new Persona("Pedro", "222222", 25, 2));
        abb.agregar(new Persona("Ana", "111111", 22, 3));
        abb.agregar(new Persona("Maria", "333333", 33, 4));


        abb.inOrden();

        System.out.println();
    }
}
