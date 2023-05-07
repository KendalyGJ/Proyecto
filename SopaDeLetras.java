
import java.util.*;

/**
 * Sopa De Letras
 * 
 * @author Jordan Obando, Kendaly Grijalba Jiménez.
 * @version 13/05/2023
 */
public class SopaDeLetrasGit {
    
    public static void main(String[] args) {//Método principal, donde se llama los métodos y se crea el arreglo y matriz.
        Scanner entrada = new Scanner(System.in);
        int maximoPalabras = 10;
        String[] palabras = pedirPalabras(entrada, maximoPalabras);
        ImprimirPalabras(palabras);
        boolean seguir=true;
        int respuesta;
        while(seguir){
            char[][]matriz = CrearSopaDeLetras(palabras);
        
            ImprimirSopaDeLetras(LlenarSopaDeLetras(matriz));
            System.out.println();
            System.out.println("Ingrese 1 para revolver las palabras\nIngrese 2 para terminar el juego");
            respuesta=entrada.nextInt();
            if(respuesta==1){
                System.out.println("\f");
                ImprimirPalabras(palabras);
                continue;
            }else if(respuesta==2){
                seguir=false;
            }
        }
        
    }
    
    public static String[] pedirPalabras(Scanner entrada, int maxPalabras){
        String palab="";
        String palabTemp="";
        System.out.println("Ingrese las palabras que desea ingresar a la Sopa De Letras : ");
        palab+=entrada.nextLine()+"-";
        for(int i=0;i<maxPalabras;i++){
            System.out.print("\f");
            System.out.println("Ingrese otra palabra o ingrese 1 para salir");
            palabTemp=entrada.nextLine();
            if(palabTemp.equals("1")){
                break;
            }else{
                palab+=palabTemp+"-";
            }
        }
        System.out.print("\f");
        String[]palabras=palab.substring(0,palab.length()-1).split("-");
        return palabras;
    }

    public static void ImprimirPalabras(String[] palabras) {// Método que recibe como párametro el arreglo palabras,luego imprime este mismo,para que el usuario las busque en la sopa de letras.
        System.out.println("Las palabras ingresadas para la Sopa De Letras son:");
        for (int i = 0; i < palabras.length; i++) {// Recorre el arreglo hasta que se impriman todas las palabras ingresadas.
            System.out.println(palabras[i]);
        }
        System.out.println();
    }

    public static char[][] CrearSopaDeLetras(String[] palabras){//Método que recibe como párametro el arreglo de palabras, el cual  crea una matriz con las palabras adentro.
        int tamaño = 0; //Donde se va a guardar la logitud de la palabra más grande.
        for(int i = 0; i < palabras.length; i++){//Recorre el arreglo
            if(palabras[i].length()>tamaño){//Pregunta el tamaño de la palabra ingresada.
                tamaño=palabras[i].length(); // El tamaño se va actualizando con la palabra más grande hasta que se recorra todo el arreglo de palabras.
            }
        }
        char[][] matriz = new char[20][20];
        for(int i=0;i<matriz.length;i++){
            for(int j=0;j<matriz[0].length;j++){
                matriz[i][j]='*';
            }
        }
        
        Random rand=new Random();
        int randFilas;
        boolean seguir=true;
        int randCol;
        int posicion;
        int contarPalabras=0;
        
        for(int i=0;i<palabras.length;i++){
            String palabraActual=palabras[i].toUpperCase();
            int contador=0;
            seguir=true;
            
            posicion=rand.nextInt(2);//decidir si va a ir horizontal, vertical o diagonal
            switch(posicion){
                case 0:
                    while(seguir){
                        contador=0;
                        randFilas=rand.nextInt(matriz.length);
                        randCol=rand.nextInt(matriz[0].length);
                        if(matriz[0].length-randCol>=palabraActual.length()){//preguntar si la palabra cabe
                            for(int j=randCol;j<randCol+palabraActual.length();j++){//ver si esos campos estan vacios
                                
                                if(matriz[randFilas][j]=='*'|| matriz[randFilas][j]==palabraActual.charAt(contarPalabras)){
                                    contador++;
                                }
                                System.out.println(contarPalabras);
                                contarPalabras++;
                            }
                            if(contador==palabraActual.length()){//si estan vacios entonces se ingresa la palabra
                                contador=0;
                                for(int c=randCol;c<randCol+palabraActual.length();c++){
                                    matriz[randFilas][c]=palabraActual.charAt(contador);
                                    contador++;
                                }
                                seguir=false;
                            }
                        }
                    }
                    break;
                case 1:
                    while(seguir){
                        randFilas=rand.nextInt(matriz.length);
                        randCol=rand.nextInt(matriz[0].length);
                        if(matriz.length-randFilas>=palabraActual.length()){
                            for(int j=randFilas;j<randFilas+palabraActual.length();j++){
                                if(matriz[j][randCol]=='*'){
                                    contador++;
                                }
                            }
                            if(contador==palabraActual.length()){
                                contador=0;
                                for(int c=randFilas;c<randFilas+palabraActual.length();c++){
                                    matriz[c][randCol]=palabraActual.charAt(contador);
                                    contador++;
                                }
                                seguir=false;
                            }
                        }
                    }
                    break;
                case 2:
                    System.out.println("aun no :(((");
                    break;
            }
        }
        
        return matriz;// Se devuelve la matriz creada.
    }

    public static char[][] LlenarSopaDeLetras(char [][] matriz){//Método que recibe como párametro la matriz. Y se encarga de llenar aleatoriamente la matriz.
        String vocales="aeiou"; //Variables donde se guardan los caracteres para luego colocarlos en la matriz.
        String consonantes="bcdfghjkmnpqrstv";
        int numRand;
        Random num=new Random();
        for(int i=0;i<matriz.length;i++){//Recorre las filas de la matriz
            for(int j=0;j<matriz[0].length;j++){//Recorre las columnas de la matriz
                if(matriz[i][j]=='*'){//Verifica que si la posicion es igual a * se le asigne un caracter.
                    if(j%2==0){// Si es divisible a dos se le coloca consonantes.
                        numRand=num.nextInt(consonantes.length());
                        matriz[i][j]=consonantes.charAt(numRand);
                    }else if(j%2!=0){//Sino vocales
                        numRand=num.nextInt(vocales.length());
                        matriz[i][j]=vocales.charAt(numRand);
                    }
                }
            }
        }
        return matriz;//Regresa la matriz rellenada aleatoriamente.
    }

    public static void ImprimirSopaDeLetras(char[][] matriz){//Método que impre la matriz
        for(int i = 0; i< matriz.length; i++){//Recorre las filas de la matriz
            System.out.print("|");
            for(int j = 0; j < matriz[0].length; j++){//Recorre las columnas de la matriz
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println("|");
        }
    }
}
