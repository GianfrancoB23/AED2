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
        if (dato > nodo.getDato()) {
            //derecha
            if (nodo.getDer() == null) {
                nodo.setDer(new Nodo(dato));
            } else {
                agregar(nodo.getDer(), dato);
            }
        } else {
            //izquierda
            if (nodo.getIzq() == null) {
                nodo.setIzq(new Nodo(dato));
            } else {
                agregar(nodo.getIzq(), dato);
            }
        }
    }

    public boolean pertenece(int dato) {
        return pertenece(this.raiz, dato);
    }

    private boolean pertenece(Nodo nodo, int dato) {
        if (nodo == null) {
            return false;
        }
        if (nodo.getDato() == dato) {
            return true;
        }
        if(dato > nodo.getDato()){
            return pertenece(nodo.getDer(), dato);
        }else{
            return pertenece(nodo.getIzq(), dato);
        }
    }

    public void inOrden() {
        inOrden(this.raiz);
        System.out.println();
    }

    private void inOrden(Nodo nodo) { //izq - nodo - der
        if (nodo != null) {
            inOrden(nodo.getIzq());
            System.out.print(nodo.getDato() + " ");
            inOrden(nodo.getDer());
        }
    }

    public void preOrden() {
        preOrden(this.raiz);
        System.out.println();
    }

    private void preOrden(Nodo nodo) { //izq - nodo - der
        if (nodo != null) {
            System.out.print(nodo.getDato() + " ");
            preOrden(nodo.getIzq());
            preOrden(nodo.getDer());
        }
    }

    public void postOrden() {
        postOrden(this.raiz);
        System.out.println();
    }

    private void postOrden(Nodo nodo) { //izq - nodo - der
        if (nodo != null) {
            postOrden(nodo.getIzq());
            postOrden(nodo.getDer());
            System.out.print(nodo.getDato() + " ");
        }
    }


}
