
import java.util.Scanner;
import java.util.Random;

/**
 * Sopa De Letras
 * 
 * @author Jordan Obando, Kendaly Grijalba Jiménez.
 * @version 13/05/2023
 */
public class SopaDeLetras {
    
    public static void main(String[] args) {//Método principal, donde se llama los métodos y se crea el arreglo y matriz.
        Scanner entrada = new Scanner(System.in);
        int maximoPalabras = 10;
        String[] palabras = pedirPalabras(entrada, maximoPalabras);
        ImprimirPalabras(palabras);
        char[][]matriz = CrearSopaDeLetras(palabras);
        
        ImprimirSopaDeLetras(LlenarSopaDeLetras(matriz));
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

        char[][] matriz = new char[tamaño * 2][tamaño * 2];

        for(int i=0;i<matriz.length;i++){
            for(int j=0;j<matriz[0].length;j++){
                matriz[i][j]='*';
            }
        }
        for (int i = 0; i < palabras.length; i++) {
            String palabraActual = palabras[i].toLowerCase(); 
            for (int j = 0; j < palabraActual.length(); j++) {
                if (j < matriz[i].length) {
                    matriz[i][j] = palabraActual.charAt(j);
                }
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
