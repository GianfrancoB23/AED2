package lista;

public interface Lista<T> {

    //Agrega un elemento al final
    public void insertar(T dato);

    //Agrega un elemento al en el indice pasado por parametro //pre 0 <= indice <= largo
    public void insertar(T dato, int indice);

    //retorna la cantidad de elementos
    public int largo();

    //retorna si existe un elemento utilizando internamente el equals de la clase
    public boolean existe(T dato);

    //retorna el elemento utilizando internamente el equals de la clase o null si no lo encuentra
    public T recuperar(T dato);

    //retorna el elemento ubicado en el indice pasado por parametro
    public T recuperar(int indice);

    public T eliminar(int indice);

    public T eliminar(T dato);

    public boolean esVacia();


}
