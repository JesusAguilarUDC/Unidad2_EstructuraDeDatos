package Ejercicio10_AnalizadorTexto;

import java.util.*;

public class Texto {

    // - Algoritmo de Busqueda. -
    public void analizar(String parrafo) {
        // Normalizar texto
        parrafo = parrafo.toLowerCase().replaceAll("[^a-záéíóúñü\\s]", "");
        String[] palabras = parrafo.split("\\s+");

        // Contar palabras
        Map<String, Integer> conteo = new HashMap<>();
        for (String palabra : palabras) {
            if (!palabra.isEmpty()) {
                conteo.put(palabra, conteo.getOrDefault(palabra, 0) + 1);
            }
        }

        // Pasar a lista para ordenar
        List<Map.Entry<String, Integer>> lista = new ArrayList<>(conteo.entrySet());

        // Ordenamiento manual (burbuja) por frecuencia descendente
        burbuja(lista);

        // Mostrar las 5 más frecuentes
        System.out.println("\nPalabras más frecuentes (Burbuja):");
        for (int i = 0; i < Math.min(5, lista.size()); i++) {
            Map.Entry<String, Integer> entrada = lista.get(i);
            System.out.println((i+1) + ". " + entrada.getKey() + " -> " + entrada.getValue());
        }

        // Ordenamiento nativo con sort
        lista.sort((a, b) -> b.getValue().compareTo(a.getValue()));
        System.out.println("\nPalabras más frecuentes (sort nativo):");
        for (int i = 0; i < Math.min(5, lista.size()); i++) {
            Map.Entry<String, Integer> entrada = lista.get(i);
            System.out.println((i+1) + ". " + entrada.getKey() + " -> " + entrada.getValue());
        }

        // Ejemplo de búsqueda lineal y binaria
        System.out.println("\nBúsqueda manual de la palabra 'java':");
        System.out.println("Lineal -> " + busquedaLineal(palabras, "java"));
        Arrays.sort(palabras); // para binaria se requiere ordenado
        System.out.println("Binaria -> " + busquedaBinaria(palabras, "java"));
    }

    // ---------- ORDENAMIENTO MANUAL ----------
    private void burbuja(List<Map.Entry<String, Integer>> lista) {
        int n = lista.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (lista.get(j).getValue() < lista.get(j+1).getValue()) {
                    Collections.swap(lista, j, j+1);
                }
            }
        }
    }

    // - Busqueda Lineal. -
    public boolean busquedaLineal(String[] arr, String palabra) {
        for (String elem : arr) {
            if (elem.equals(palabra)) {
                return true;
            }
        }
        return false;
    }

    // - Busqueda Binaria. -
    public boolean busquedaBinaria(String[] arr, String palabra) {
        int inicio = 0, fin = arr.length - 1;
        while (inicio <= fin) {
            int mid = (inicio + fin) / 2;
            int cmp = arr[mid].compareTo(palabra);
            if (cmp == 0) return true;
            else if (cmp < 0) inicio = mid + 1;
            else fin = mid - 1;
        }
        return false;
    }
}
