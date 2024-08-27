package ab;

public class AB {
    private Nodo raiz;

    public AB(Nodo raiz){
        this.raiz = raiz;
    }

    //Retorna la cantidad de nodos del AB.
    public int cantNodos(){
        return cantNodos(this.raiz);
    };

    private int cantNodos(Nodo nodo){
        // 1. Caso base
        if(nodo == null){
            return 0;
        }

        // 2. Llamada rec (usar el metodo cantNodos en mis hijos)
        int cantNodosSubIzq = cantNodos(nodo.getIzq());
        int cantNodosSubDer = cantNodos(nodo.getDer());

        // 3. Unir resultados
        return cantNodosSubIzq + cantNodosSubDer + 1;
    }

    //Retorna la cantidad de nodos hoja del AB.
    public int cantHojas(){
        return cantHojas(this.raiz);
    };

    private int cantHojas(Nodo nodo){
        // 1. Caso base
        if(nodo == null){
            return 0;
        }

        if(nodo.getDer() == null && nodo.getIzq() == null){
            return 1;
        }

        // 2. Llamada rec (usar el metodo cantNodos en mis hijos)
        int cantHojasSubIzq = cantHojas(nodo.getIzq());
        int cantHojasSubDer = cantHojas(nodo.getDer());


        // 3. Unir resultados
        return cantHojasSubIzq + cantHojasSubDer;
    }

    //Retorna la altura del AB.
    public int altura(){
        return altura(this.raiz);
    };

    private int altura(Nodo nodo){
        // 1. Caso base
        if(nodo == null){
            return -1;
        }

        if(nodo.getDer() == null && nodo.getIzq() == null){
            return 0;
        }

        // 2. Llamada rec (usar el metodo cantNodos en mis hijos)
        int alturaIzq = altura(nodo.getIzq());
        int alturaDer = altura(nodo.getDer());

        // 3. Unir resultados
        return 1 + Math.max(alturaIzq,alturaDer);
    }

    //Retorna true si y solo si todos los elementos del AB son pares.
    public boolean todosPares(){
        return todosPares(this.raiz);
    };

    private boolean todosPares(Nodo nodo){
        // 1. Caso base
        if(nodo == null){
            return false;
        }

        // 2. Llamada rec (usar el metodo cantNodos en mis hijos)


        // 3. Unir resultados
        return false;
    }

//   boolean pertenece(int x);
//    //Pos: Retorna true si y solo si el dato pasado como parámetro pertenece al AB

    //public boolean equilibrado();
}
