import java.util.Scanner;
import java.util.Random;

/**
 * Sopa De Letras
 * 
 * @author Jordan Obando, Kendaly Grijalba Jiménez.
 * @version 13/05/2023
 */
public class Metodos {

    public static void main(String[] args) {//Método principal, donde se llama los métodos y se crea el arreglo y matriz.

        Scanner entrada = new Scanner(System.in);
        int maximoPalabras = 10;
        String[] palabras = OpcionDePalabras( entrada,maximoPalabras);

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

    public static String[] OpcionDePalabras(Scanner entrada, int maximoPalabras) {//Método que recibe como parámetro objeto de Scanner y el tope de palabras. Se encarga de preguntarle al usuario la opcion de palabras.
        String[] palabras;//Inicialización de variable.
        //Pregunta al usuario
        System.out.println("¿Desea ingresar las palabras o escoger palabras ya guardadas?");
        System.out.println("Ingrese 1 para ingresar las palabras");
        System.out.println("Ingrese 2 para escoger del banco de palabras");
        int opcion = entrada.nextInt();//Guarda opción
        if (opcion == 1) {//Si es opción "1", el usuario agrega las palabras.Llamando al método PedirPalbras
            palabras = PedirPalabras(entrada, maximoPalabras);
        } else if (opcion == 2) {//Si es la opción "2", se escoge 10 palabras aleatoriamente del banco de palabras.
            palabras = BancoPalabras(maximoPalabras);
        } else {//Si agrega un caracter invalido , se despliega esta instrucción.
            System.out.println("Por favor, ingrese una opción válida");
            return null;
        }
        System.out.print("\f");//Borra las instrucciones.
        ImprimirPalabras(palabras);//Llama al método ImprimirPalabras.
        return palabras;//Regresa el arreglo palabras.
    }

    public static String[] BancoPalabras(int maximoPalabras){//Método que recibe como parámetro el tope de palabras.Se encarga de guardar palabras ya seleccionadas para el usuario.
        //Banco de palabras, guardadas en un arreglo de String.
        String[] banco = new String[]{"Película", "Actor", "Actriz", "Director", "Guion", "Producción", "Montaje", "Maquillaje", "Vestuario", "Iluminación", "Escenario", "Banda sonora", "Estreno", "Taquilla", "Premio", "Reparto", "Protagonista", "Personaje", "Escena", "Secuencia", "Cámara", "Lente", "Enfoque", "Plano", "Cortometraje", "Trama", "Acción", "Comedia", "Drama", "Terror"};
        String[] palabras = new String[maximoPalabras];// Arreglo de String, que crea el tamaño en base al tope (maximoPalabras).
        int palabrasSeleccionadas = 0;//Variable que se utiliza como contador con las palabras ya escogidas para usar en la sopa de letras.
        Random rand = new Random();// Objeto Random
        while(palabrasSeleccionadas < maximoPalabras){//Se utiliza para seleccionar palabras aleatorias hasta que llegue al tope.
            int indice = rand.nextInt(banco.length);//En cada iteración del bucle, se seleccionada una palabra aleatoria.
            boolean palabraRepetida = false;// Variable que servirá para ver si es repetida.
            for(int i = 0; i < palabrasSeleccionadas; i++){//Se utiliza un for para ver si la palabra ya se ha escogido anteriormente.
                if(palabras[i].equals(banco[indice])){//Si la palabra ya esta en el arreglo "palabras" se establece "true" y sale.
                    palabraRepetida = true;
                    break;//Sale del ciclo.
                }
            }
            if(!palabraRepetida){//Sino la palabra se agrega al arreglo "palabras"y se incrementa la variable "palabrasSeleccionadas".
                palabras[palabrasSeleccionadas] = banco[indice];//Se guarda.
                palabrasSeleccionadas++;//Incrementa variable.
            }
        }

        return palabras;//Regresa el arreglo palabras.
    }

    public static String[] PedirPalabras(Scanner entrada, int maxPalabras){//Método que recibe como parámetro, objeto Scanner y tope de palabras. S e encarga de pedirle al usuario las palbras que desea ingresar.
        //Variables con valor vacío.
        String palab="";
        String palabTemp="";
        System.out.println("Ingrese las palabras que desea ingresar a la Sopa De Letras : ");
        palab+=entrada.nextLine()+"-"; //Se agrega el valor ingresado por el usuario a la variable.
        for(int i=0;i<maxPalabras;i++){//Bucle donde se va ingresando palabras hasta que lleguen al tope.
            System.out.print("\f");
            System.out.println("Ingrese otra palabra o ingrese 1 para salir");
            palabTemp=entrada.nextLine().trim();// Donde se van guardando las palabras,ese trim(), es para quitar espacios.
            if(palabTemp.equals("1")){//Si el usuario agrega un 1, es para salir del ciclo antes de llegar al tope de palabras.
                break;
            }else{
                palab+=palabTemp+"-";// Sino, sigue agregando.
            }
        }
        System.out.print("\f");
        String[]palabras=palab.substring(0,palab.length()-1).split("-");// S eutiliza para obtener la cadena de texto sin el ultimo caracter "-", y el split() es para dividir la cadena de texto en un arreglo de palabras.
        return palabras;//Regresa un arreglo.
    }

    public static void ImprimirPalabras(String[] palabras) {// Método que recibe como párametro el arreglo palabras,luego imprime este mismo,para que el usuario las busque en la sopa de letras.
        System.out.println("Las palabras ingresadas para la Sopa De Letras son:");
        for (int i = 0; i < palabras.length; i++) {// Recorre el arreglo hasta que se impriman todas las palabras ingresadas.
            System.out.println( (i+1)+"-"+palabras[i]);
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

        Random rand=new Random();//Instancia de la clase Random
        int randFilas;
        boolean seguir=true;
        int randCol;
        int posicion;

        for(int i=0;i<palabras.length;i++){//Bucle de todas las palabras que se deben ingresar a la sopa de letras.
            String palabraActual= palabras[i].toUpperCase();// En cada repetición las palabras se convierten en Mayúculas y se almacena en la variable
            int contador;
            seguir=true;//Significa que todavía no se ha encontrado una posición adecuada.

            posicion=rand.nextInt(2);//decidir si va a ir horizontal, vertical o diagonal
            switch(posicion){//Ver casos
                case 0:
                    //Variables que sirven para limitar los intentos para colocar la palabra en una posición adecuada.
                    contador = 0;
                    int contarPalabras=0; 
                    while(seguir){

                        randFilas=rand.nextInt(matriz.length);//En la fila aleatoria en que se coloca la palabra
                        randCol=rand.nextInt(matriz[0].length - palabraActual.length() +1);//En la columna aleatoria en que se coloca.
                        for (int j = randCol; j < randCol + palabraActual.length(); j++) {//Bucle que se efectua atravez de la longitud de la palabra.
                            if (matriz[randFilas][j] == '*' || matriz[randFilas][j] == palabraActual.charAt(contarPalabras)) {//Se comprueba si la actual de la palabra, coincide con la letra correspondiente en la matriz de caracteres.
                                contador++;// Si pasa, se incrementa el contador y continua la siguiente letra.
                            } else {//Sino, se establece el contador y se sale del bucle.
                                contador = 0;
                                break;
                            }
                            contarPalabras++;
                        }
                        if (contador == palabraActual.length()){//Si es igual, es porque se encontró un lugar adecuado para colocar la palabra.
                            contador = 0; //Se establece el contador.
                            for (int c = randCol; c < randCol + palabraActual.length(); c++) {// Se ejecuta atravez de la longitud de la palabra actual.
                                matriz[randFilas][c] = palabraActual.charAt(contador);// Se coloca la letra correspondiente de la palabra en la matriz de caracteres en la posición correspondiente.
                                contador++;
                            }
                            seguir = false;
                        } else {//Sino es igual, se establece, las variables contador, contarPalabras.
                            contarPalabras = 0;
                            contador = 0;
                        }

                        // Si se hacen demasiados intentos sin éxito, se devuelve una matriz vacía
                        if (contarPalabras > 200) {
                            return new char[0][0];
                        }
                    }
                    break;
                case 1: // Vertical, en este caso es similar, solamente, se recorre cada letra de la palabra en la columna.
                    contador = 0;
                    while (seguir) {
                        randFilas = rand.nextInt(matriz.length - palabraActual.length() + 1);
                        randCol = rand.nextInt(matriz[0].length);
                        for (int j = randFilas; j < randFilas + palabraActual.length(); j++) {
                            if (matriz[j][randCol] == '*') {
                                contador++;
                            } else {
                                contador = 0;
                                break;
                            }
                        }
                        if (contador == palabraActual.length()) {
                            contador = 0;
                            for (int c = randFilas; c < randFilas + palabraActual.length(); c++) {
                                matriz[c][randCol] = palabraActual.charAt(contador);
                                contador++;
                            }
                            seguir = false;
                        } else {
                            contador = 0;
                        }
                    }
                    break;
                case 2: // Diagonal
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

