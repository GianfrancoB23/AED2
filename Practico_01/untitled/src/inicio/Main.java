package inicio;

public class Main {
    public static void main(String[] args) {

        int[] datos = {2,4,6,7,8,1};
        int resultado = sumaRec(datos);

        System.out.println("resultado = " + resultado);
    }

    public static int sumaInter(int[] datos){
        int suma = 0;
        for(int i=0; i<datos.length;i++) {
            suma += datos[i];
        }
        return suma;
    }

    public static int sumaRec(int[] datos){
        //Recordar cambiar el metodo por el cual queremos resolver
        return sumaRecV3(datos, 0, datos.length-1);
    }

    public static int sumaRec(int[] datos, int desde, int hasta) {
        //1.- Definir los casos base
        if(desde == hasta){
            return datos[desde];
        }

        //2.- Realizar la llamada rec
        int medio = (desde+hasta)/2;
        int sumaIzq = sumaRec(datos, desde, medio);
        int sumaDer = sumaRec(datos, medio+1, hasta);

        //3.- Unir resultados
        return sumaIzq+sumaDer;
    }

    public static int sumaRecV2(int[] datos, int desde, int hasta) {
        //1.- Definir los casos base
        if(desde == hasta){
            return datos[desde];
        }

        //2.- Realizar la llamada rec
        int sumaIzq = sumaRecV2(datos, desde, desde);
        int sumaDer = sumaRecV2(datos, desde+1, hasta);

        //3.- Unir resultados
        return sumaIzq+sumaDer;
    }

    public static int sumaRecV3(int[] datos, int desde, int hasta) {
        //1.- Definir los casos base
        if(desde == hasta){
            return datos[desde];
        }

        //3.- Unir resultados
        return datos[desde] + sumaRecV3(datos, desde+1, hasta);
    }
}