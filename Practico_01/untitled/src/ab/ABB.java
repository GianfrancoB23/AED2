package ab;

public class ABB {
    private Nodo raiz;

    public ABB() {
        this.raiz = null;
    }

    public void agregar(int dato) {
        if (this.raiz == null) {
            this.raiz = new Nodo(dato);
        } else {
            agregar(this.raiz, dato);
        }
    }

    private void agregar(Nodo nodo, int dato) {
        if(dato > nodo.getDato()){
            // Derecha
            if (nodo.getDer() == null) {
                nodo.setDer(new Nodo(dato));
            } else {
                agregar(nodo.getDer(), dato);
            }
        }else{
            // Izquierda
            if (nodo.getIzq() == null) {
                nodo.setIzq(new Nodo(dato));
            } else {
                agregar(nodo.getIzq(), dato);
            }
        }
    }

    public boolean pertenece(int dato) {
        return true;
    }

    public void inOrden() {
        inOrden(this.raiz);
        System.out.println();
    }

    private void inOrden(Nodo nodo) { // izq - nodo - der
        if(nodo != null){
            inOrden(nodo.getIzq());
            System.out.println(nodo.getDato() + " ");
            inOrden(nodo.getDer());
        }
    }
}
