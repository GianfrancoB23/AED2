package ab;

public class AB {

    private Nodo raiz;

    public AB(Nodo raiz) {
        this.raiz = raiz;
    }

    //Pos: Retorna la cantidad de nodos del AB.
    public int cantNodos(){
        return cantNodos(this.raiz);
    }

    private int cantNodos(Nodo nodo) {
        //1. Caso base
        if(nodo == null){
            return 0;
        }
        //2. Llamadas rec (usar el metodo cantNodos en mis hijos)
        int cantNodosSubIzq = cantNodos(nodo.getIzq());
        int cantNodosSubDer = cantNodos(nodo.getDer());

        //3. Unir resultados
        return cantNodosSubIzq + cantNodosSubDer + 1;
    }

    //Pos: Retorna la cantidad de nodos hoja del AB.
    public int cantHojas(){
        return cantHojas(this.raiz);
    }

    private int cantHojas(Nodo nodo) {
        //1. Caso base
        if(nodo == null){
            return 0;
        }
        if(nodo.getDer() == null && nodo.getIzq() == null){
            return 1;
        }
        //2. Llamadas rec (usar el metodo cantNodos en mis hijos)
        int cantHojasIzq = cantHojas(nodo.getIzq());
        int cantHojasDer = cantHojas(nodo.getDer());

        //3. Unir resultados
        return cantHojasIzq + cantHojasDer;
    }

    //Pos: Retorna la altura del AB.
    public int altura(){
        return altura(this.raiz);
    }

    private int altura(Nodo nodo) {
        //1. Caso base
        if(nodo == null){
            return -1;
        }
        if(nodo.getDer() == null && nodo.getIzq() == null){
            return 0;
        }
        //2. Llamadas rec (usar el metodo cantNodos en mis hijos)
        int alturaIzq = altura(nodo.getIzq());
        int alturaDer = altura(nodo.getDer());

        //3. Unir resultados
        return 1 + Math.max(alturaIzq, alturaDer);
    }

    public boolean todosPares(){
        return todosPares(this.raiz);
    }

    private boolean todosPares(Nodo nodo) {
        //1. Casos base
        if(nodo == null){
            return true;
        }
        if(nodo.getDato() %2 != 0){
            return false;
        }
        //3. unir resultados
        return todosPares(nodo.getDer()) && todosPares(nodo.getIzq());
    }

    private boolean todosParesV2(Nodo nodo) {
        //1. Casos base
        if(nodo == null){
            return true;
        }
        if(nodo.getDato() %2 != 0){
            return false;
        }
        //2. llamadas rec
        boolean respDer = todosParesV2(nodo.getDer());
        boolean respIzq = todosParesV2(nodo.getIzq());
        //3. unir resultados
        return respDer && respIzq;
    }

    public boolean iguales(AB arbol){
        return igualesRec(this.raiz, arbol.raiz);
    }

    private boolean igualesRec(Nodo nodo1, Nodo nodo2) {
        if(nodo1 == null && nodo2 == null){
            return true;
        }
        if(nodo1 == null || nodo2 == null){
            return false;
        }
        if(nodo1.getDato() != nodo2.getDato()){
            return false;
        }
        return igualesRec(nodo1.getDer(), nodo2.getDer()) && igualesRec(nodo1.getIzq(), nodo2.getIzq());
    }

    public AB clon(){
        Nodo nuevaRaiz = clon(this.raiz);
        return new AB(nuevaRaiz);
    }

    private Nodo clon(Nodo nodo) {
        if(nodo == null){
            return null;
        }
        Nodo clonado = new Nodo(nodo.getDato());
        clonado.setDer(clon(nodo.getDer()));
        clonado.setIzq(clon(nodo.getIzq()));
        return clonado;
    }

    public boolean pertenece(int x){
        return perteneceRec(this.raiz, x);
    }

    private boolean perteneceRec(Nodo nodo, int x) {
        //1. Casos base
        if( nodo == null){
            return false;
        }
        if( nodo.getDato() == x){
            return true;
        }
        return perteneceRec(nodo.getIzq(), x) || perteneceRec(nodo.getDer(), x)  ;
    }

    //Pos: Retorna true si y solo si el dato pasado como parámetro pertenece al AB.

    public boolean equilibrado(){
        return equilibrado(this.raiz);
    }

    private boolean equilibrado(Nodo nodo) {
        if(nodo == null){
            return true;
        }
        int alturaIzq = altura(nodo.getIzq());
        int alturaDer = altura(nodo.getDer());
        if( Math.abs(alturaDer-alturaIzq) > 1 ){
            return false;
        }
        return equilibrado(nodo.getIzq()) && equilibrado(nodo.getDer());
    }
    //Pos: Dado un árbol binario retorna true si y solo si el árbol es equilibrado.



}
