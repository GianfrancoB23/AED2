package inicio;

public class Main {
    public static void main(String[] args) {

        int[][] datos = {{1,2,3},{4,5,6},{7,8,9}};
        //int[] datos = {2,4,6,7,8,1};
        int resultado = sumaDiagonales(datos);

        System.out.println("resultado = " + resultado);
    }

    public static int sumaDiagonales(int[][] mat){
        int suma = 0;
        for(int i = 0; i < mat.length; i++)
            for (int j = 0; j<mat[0].length; j++){
                if(i == i || j == mat.length - 1 - i){
                    suma += mat[i][j];
                }
            }
        return suma;
    }

    public static int sumarDiagonales(int[][] m){
        int suma = 0;
        int longitud = m.length;

        for(int i = 0; i<longitud; i++){
            suma += m[i][i];
            suma += m[i][longitud-1-i];
        }

        if(longitud%2==1){
            int centro = longitud/2;
            suma -= m[centro][centro];
        }

        return suma;
    }

    public static int sumarDiagonalesv2(int[][] m){
        int suma = 0;
        int longitud = m.length;

        for(int i = 0; i<longitud; i++){
            suma += m[i][i];
            suma += m[i][longitud-1-i];
            suma += m[longitud-1-i][i];
            suma += m[longitud-1-i][longitud-1-i];
        }

        if(longitud%2==1){
            int centro = longitud/2;
            suma += m[centro][centro];
        }

        return suma;
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