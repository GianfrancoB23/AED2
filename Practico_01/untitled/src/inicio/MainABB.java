package inicio;
import ab.ABB;

public class MainABB {
    public static void main(String[] args){
        ABB abb = new ABB();
        abb.agregar(4);
        abb.agregar(2);
        abb.agregar(1);
        abb.agregar(3);
        abb.agregar(6);
        abb.agregar(5);
        abb.agregar(7);
        boolean existe = abb.pertenece(5);
        System.out.println("existe = " + existe);

        abb.inOrden();
        System.out.println();
    }
}
