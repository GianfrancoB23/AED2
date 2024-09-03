package inicio;

import pila.*;

public class MainTADs {
    public static void main(String[] args) {
        Pila pila = new PilaImpArray(5);
        pila.push(3);
        pila.push(2);
        pila.push(1);
        while (!pila.estaVacia()){
            System.out.println(pila.pop());
        }
    }
}
