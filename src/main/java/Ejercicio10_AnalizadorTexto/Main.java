package Ejercicio10_AnalizadorTexto;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Texto texto = new Texto();
        Scanner sc = new Scanner(System.in);

        System.out.println("Ingrese un texto:");
        String parrafo = sc.nextLine();

        texto.analizar(parrafo);
    }
}
