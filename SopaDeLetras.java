import java.util.Scanner;
import java.util.Random;

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
        String[] palabras = OpcionDePalabras(entrada,maximoPalabras);
        opcionesJuego(entrada,palabras);
        
    }
    
    public static String[] OpcionDePalabras(Scanner entrada, int maximoPalabras) {//Método que recibe como parámetro objeto de Scanner y el tope de palabras. Se encarga de preguntarle al usuario la opcion de palabras.
        String[] palabras;//Inicialización de variable.
        //Pregunta al usuario
        System.out.println("¿Desea ingresar las palabras o escoger palabras ya guardadas?\nIngrese 1 para ingresar las palabras\nIngrese 2 para escoger del banco de palabras");
        int opcion=comprobarNum(entrada);
        System.out.print("\f");
        if (opcion == 1) {//Si es opción "1", el usuario agrega las palabras.Llamando al método PedirPalbras
            palabras = PedirPalabras(entrada, maximoPalabras);
        } else{//Si es la opción "2", se escoge 10 palabras aleatoriamente del banco de palabras.
            palabras = BancoPalabras(entrada,maximoPalabras);
        }
        ImprimirPalabras(palabras);//Llama al método ImprimirPalabras.
        return palabras;//Regresa el arreglo palabras.
    }
    
    public static int comprobarNum(Scanner entrada){
        boolean seguir=true;
        int opcion=0;
        while(seguir){
            opcion = entrada.nextInt();
            if(opcion==1 || opcion==2){
                seguir=false;
            }else{
                System.out.println("Por favor ingrese una opcion valida");
            }
        }
        return opcion;
    }
    
    public static String[] PedirPalabras(Scanner entrada, int maxPalabras){//Método que recibe como parámetro, objeto Scanner y tope de palabras. S e encarga de pedirle al usuario las palbras que desea ingresar.
        //Variables con valor vacío.
        String palabTemp="";
        String palab="";
        entrada.nextLine();
        System.out.println("Ingrese las palabras que desea ingresar a la Sopa De Letras : ");
        palab=entrada.nextLine()+"-";
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
        String[]palabras = palab.substring(0,palab.length()-1).split("-");// Se  utiliza para obtener la cadena de texto sin el ultimo caracter "-", y el split() es para dividir la cadena de texto en un arreglo de palabras.
        return palabras;//Regresa un arreglo.
    }
    
    public static String[] BancoPalabras(Scanner entrada,int maximoPalabras){//Método que recibe como parámetro el tope de palabras.Se encarga de guardar palabras ya seleccionadas para el usuario.
        //Banco de palabras, guardadas en un arreglo de String.
        String[] banco = new String[]{"Película", "Actor", "Actriz", "Director", "Guion", "Producción", "Montaje", "Maquillaje", "Vestuario", "Iluminación", "Escenario", "Filmación", "Estreno", "Taquilla", "Premio", "Reparto", "Protagonista", "Personaje", "Escena", "Secuencia", "Cámara", "Lente", "Enfoque", "Plano", "Cortometraje", "Trama", "Acción", "Comedia", "Drama", "Terror"};
        System.out.println("Ingrese el número de palabras que desea en la sopa de letras (máximo " + maximoPalabras + "): ");
        int cantidadPalabras = entrada.nextInt();
       
        while (cantidadPalabras > maximoPalabras) {//Bucle que permite ingresar las palabras hasta el tope
            System.out.print("\f");
            System.out.println("La cantidad de palabras ingresada supera el límite de " + maximoPalabras + " palabras. Por favor, ingrese un número válido: ");
            cantidadPalabras = entrada.nextInt();
        }
        System.out.print("\f");

        String[] palabras = new String[cantidadPalabras];// Arreglo de String, que crea el tamaño en base al tope (maximoPalabras).
        int palabrasSeleccionadas = 0;//Variable que se utiliza como contador con las palabras ya escogidas para usar en la sopa de letras.
        Random rand = new Random();// Objeto Random.
        while(palabrasSeleccionadas < cantidadPalabras){//Se utiliza para seleccionar palabras aleatorias hasta que llegue al tope.
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
    
    public static void ImprimirPalabras(String[] palabras) {// Método que recibe como párametro el arreglo palabras,luego imprime este mismo,para que el usuario las busque en la sopa de letras.
        System.out.println("Las palabras ingresadas para la Sopa De Letras son:");
        int numero=1;
        for (int i = 0; i < palabras.length; i++) {// Recorre el arreglo hasta que se impriman todas las palabras ingresadas.
            System.out.println(numero+"-"+palabras[i]);
            numero++;
        }
    }
    
    public static void opcionesJuego(Scanner entrada,String[]palabras){
        int respuesta=0;
        while(respuesta!=2){
            char[][]matriz = CrearSopaDeLetras(palabras);

            ImprimirSopaDeLetras(LlenarSopaDeLetras(matriz));
            System.out.println();
            System.out.println("Ingrese 1 para revolver las palabras\nIngrese 2 para terminar el juego");
            
            respuesta=comprobarNum(entrada);
            if(respuesta==1){
                System.out.print("\f");
                ImprimirPalabras(palabras);
                continue;
            }else if(respuesta==2){
                System.out.print("\f");
                System.out.print("Fin del juego");
            }
        }
    }
    
    public static char[][] CrearSopaDeLetras(String[] palabras){//Método que recibe como párametro el arreglo de palabras, el cual  crea una matriz con las palabras adentro.
        char[][] matriz = new char[30][30];

        for(int i=0;i<matriz.length;i++){
            for(int j=0;j<matriz[0].length;j++){
                matriz[i][j]='*';
            }
        }

        Random rand=new Random();//Instancia de la clase Random
        int randFilas;
        boolean seguir;
        int randCol;
        int posicion;

        for(int i=0;i<palabras.length;i++){//Bucle de todas las palabras que se deben ingresar a la sopa de letras.
            String palabraActual= palabras[i].toUpperCase();// En cada repetición las palabras se convierten en Mayúculas y se almacena en la variable
            int contador=0;
            seguir=true;//Significa que todavía no se ha encontrado una posición adecuada.

            posicion=rand.nextInt(3);//decidir si va a ir horizontal, vertical o diagonal
            switch(posicion){//Ver casos
                case 0:
                    //Variables que sirven para limitar los intentos para colocar la palabra en una posición adecuada.
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
                    contarPalabras = 0;
                    while (seguir) {
                        randFilas = rand.nextInt(matriz.length - palabraActual.length() + 1);// Aleatoriamente en una fila menos la palabra actual.
                        randCol = rand.nextInt(matriz[0].length);//Aleatoriamente en una columna.
                        for (int j = randFilas; j < randFilas + palabraActual.length(); j++) {
                            if (matriz[j][randCol] == '*' || matriz[j][randCol] == palabraActual.charAt(contarPalabras)) {
                                contador++;
                            } else {
                                contador = 0;
                                break;
                            }
                            contarPalabras++;
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
                    contarPalabras = 0;
                    while(seguir){//Bucle while, se ejecuta hasta que la variable "seguir" sea verdadera.
                        randFilas = rand.nextInt(matriz.length - palabraActual.length() + 1);//Aleatoriamente en una fila menos la palabra actual.
                        randCol = rand.nextInt(matriz[0].length - palabraActual.length() - 1);//Aleatoriamente en una columna menos la longitud de la palabra.
                        for(int j = 0; j < palabraActual.length();j++){//Verifica si la palabra se puede insertar en la posicion seleccionada.
                            if(matriz[randFilas + j][randCol + j]== '*'  || matriz[randFilas + j][randCol + j] == palabraActual.charAt(j)){//Verifica si la letra en la posición es un * o si es igual a la letra correspondiente de la palabra actual.
                                contador++; //Si se cumple alguna,  se incrementa a 1. 
                            }else{//Sino se sale del bucle y el contador se reinicia a 0.
                                contador = 0;
                                break;
                            }
                        }
                        if(contador == palabraActual.length()){//Si el contador alcanza la longitud de la palabra, es porque se ha encontrado una posición valida.
                            contador = 0;//Se reinicia el contador a 0.
                            for(int c = 0; c < palabraActual.length();c++){//INsertar la palabra a la matriz.
                                matriz[randFilas + c][randCol + c] = palabraActual.charAt(contador);
                                contador++;
                            }
                            seguir = false;//Sale del bucle while.
                        }else{
                            contador = 0;//Sino se continua con while, reiniciando el contador a 0.
                        }
                    }
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
                    if(i%2==0){
                        if(j%2==0){// Si es divisible a dos se le coloca consonantes.
                            numRand=num.nextInt(consonantes.length());
                            matriz[i][j]=consonantes.charAt(numRand);
                        }else if(j%2!=0){//Sino vocales
                            numRand=num.nextInt(vocales.length());
                            matriz[i][j]=vocales.charAt(numRand);
                        }
                    }else{
                        if(j%2!=0){// Si es divisible a dos se le coloca consonantes.
                            numRand=num.nextInt(consonantes.length());
                            matriz[i][j]=consonantes.charAt(numRand);
                        }else if(j%2==0){//Sino vocales
                            numRand=num.nextInt(vocales.length());
                            matriz[i][j]=vocales.charAt(numRand);
                        }
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
