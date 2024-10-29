package ab;

public class AB {

    private Nodo raiz;

    public AB(Nodo raiz) {
        this.raiz = raiz;
    }

    //Pos: Retorna la cantidad de nodos del AB.
    public int cantNodos() {
        return cantNodos(this.raiz);
    }

    private int cantNodos(Nodo nodo) {
        //1. Caso base
        if (nodo == null) {
            return 0;
        }
        //2. Llamadas rec (usar el metodo cantNodos en mis hijos)
        int cantNodosSubIzq = cantNodos(nodo.getIzq());
        int cantNodosSubDer = cantNodos(nodo.getDer());

        //3. Unir resultados
        return cantNodosSubIzq + cantNodosSubDer + 1;
    }

    //Pos: Retorna la cantidad de nodos hoja del AB.
    public int cantHojas() {
        return cantHojas(this.raiz);
    }

    private int cantHojas(Nodo nodo) {
        //1. Caso base
        if (nodo == null) {
            return 0;
        }
        if (nodo.getDer() == null && nodo.getIzq() == null) {
            return 1;
        }
        //2. Llamadas rec (usar el metodo cantNodos en mis hijos)
        int cantHojasIzq = cantHojas(nodo.getIzq());
        int cantHojasDer = cantHojas(nodo.getDer());

        //3. Unir resultados
        return cantHojasIzq + cantHojasDer;
    }

    //Pos: Retorna la altura del AB.
    public int altura() {
        return altura(this.raiz);
    }

    private int altura(Nodo nodo) {
        //1. Caso base
        if (nodo == null) {
            return -1;
        }
        if (nodo.getDer() == null && nodo.getIzq() == null) {
            return 0;
        }
        //2. Llamadas rec (usar el metodo cantNodos en mis hijos)
        int alturaIzq = altura(nodo.getIzq());
        int alturaDer = altura(nodo.getDer());

        //3. Unir resultados
        return 1 + Math.max(alturaIzq, alturaDer);
    }

    //Pos: Retorna true si y solo si todos los elementos del AB son pares.
    public boolean todosPares() {
        return todosPares(this.raiz);
    }

    private boolean todosPares(Nodo nodo) {
        //1. Caso base
        if (nodo == null) {
            return true;
        }
        if (nodo.getDato() % 2 != 0) {
            return false;
        }

        //2. Llamadas rec (usar el metodo todosPares en mis hijos)
        boolean respIzq = todosPares(nodo.getIzq());
        boolean respDer = todosPares(nodo.getDer());

        //3. Unir resultados
        return respDer && respIzq;
    }

    //Pos: Retorna true si y solo si todos los elementos del AB son pares.
    public boolean todosParesV2() {
        return todosParesV2(this.raiz);
    }

    private boolean todosParesV2(Nodo nodo) {
        //1. Caso base
        if (nodo == null) {
            return true;
        }
        if (nodo.getDato() % 2 != 0) {
            return false;
        }

        //2. Llamadas rec (usar el metodo todosPares en mis hijos)

        //3. Unir resultados
        return todosPares(nodo.getDer()) && todosPares(nodo.getIzq());
    }

    public boolean iguales(AB arbol) {
        return iguales(arbol.raiz, this.raiz);
    }

    private boolean iguales(Nodo nodo1, Nodo nodo2) {
        //1. Caso base
        if (nodo1 == null && nodo2 == null) {
            return true;
        }
        if (nodo1 == null || nodo2 == null) {
            return false;
        }
        if(nodo1.getDato() != nodo2.getDato()){
            return false;
        }

        //2. Llamadas rec (usar el metodo todosPares en mis hijos)
        //3. Unir resultados
        return iguales(nodo1.getDer(), nodo2.getDer()) && iguales(nodo1.getIzq(), nodo2.getIzq());
    }

    private boolean igualesCor(Nodo nodo1, Nodo nodo2) {
        //1. Caso base
        if (nodo1 == null && nodo2 == null) {
            return true;
        }
        if (nodo1 == null || nodo2 == null) {
            return false;
        }
        if(nodo1.getDato() != nodo2.getDato()){
            return false;
        }

        //2. Llamadas rec (usar el metodo todosPares en mis hijos)
        //3. Unir resultados
        return igualesCor(nodo1.getDer(), nodo2.getDer()) && igualesCor(nodo1.getIzq(), nodo2.getIzq());
    }

    //Pos: Retorna true si y solo si el dato pasado como parámetro pertenece al AB.
    boolean pertenece(int x) {
        return pertenece(this.raiz, x);
    }
    private boolean pertenece(Nodo nodo, int x){
        //1. Caso base
        if(nodo == null){
            return false;
        }
        if(nodo.getDato() == x){
            return true;
        }

        //2. Llamadas rec (usar el metodo todosPares en mis hijos)
        //3. Unir resultados
        return pertenece(nodo.getIzq(),x) || pertenece(nodo.getDer(),x);

    }

    //Pos: Dado un árbol binario retorna true si y solo si el árbol es equilibrado.
    public boolean equilibrado() {
        return equilibrado(this.raiz);
    }

    private boolean equilibrado(Nodo nodo) {
        if(nodo == null){
            return true;
        }

        int alturaIzq = altura(nodo.getIzq());
        int alturaDer = altura(nodo.getDer());
        if (Math.abs(alturaDer-alturaIzq) > 1){
            return false;
        }

        return equilibrado(nodo.getIzq()) && equilibrado(nodo.getDer());
    }

    public AB clon(){
        Nodo nuevaRaiz = clon(this.raiz);
        return new AB(nuevaRaiz);
    }

    private Nodo clon(Nodo nodo){
        if(nodo == null){
            return null;
        }
        Nodo clonado = new Nodo(nodo.getDato());
        clonado.setDer(clon(nodo.getDer()));
        clonado.setIzq(clon(nodo.getIzq()));
        return clonado;
    }

    // EJERCICIO 6
    //    A. Desarrolle un algoritmo que, recibiendo un valor entero k, retorne la cantidad de elementos que
    //    son mayores a k.
    public int RetornoCtd(int k){
        return RetornoCtd(k, this.raiz);
    }
    private int RetornoCtd(int k, Nodo nodo){
        int cuentaActual = 0;
        //1. Caso base
        if(nodo == null){
            return 0;
        }
        if(nodo.getDato() > k){
            cuentaActual = 1;
        }

        //2. Llamadas rec (usar el metodo todosPares en mis hijos)
        int cantidadMayoresDerecha = RetornoCtd(k, nodo.getDer());
        int cantidadMayoresIzquierda = RetornoCtd(k, nodo.getIzq());

        //3. Unir resultados

        return cuentaActual+cantidadMayoresIzquierda + cantidadMayoresDerecha;
    }

    // B. Desarrolle un algoritmo que retorne una lista con sus elementos ordenados de forma
    // ascendente.

}
