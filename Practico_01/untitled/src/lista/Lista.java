package lista;

public interface Lista<T> {
    void insertar(T dato);
    void insertar(T dato, int indice);
    public int largo();
    public boolean existe(T dato);
    public T recuperar(T dato);
    public T recuperar (int indice);
    public T eliminar(int indice);
}
