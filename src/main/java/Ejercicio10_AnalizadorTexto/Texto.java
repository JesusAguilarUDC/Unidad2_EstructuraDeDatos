package Ejercicio10_AnalizadorTexto;

import java.util.*;
import java.util.stream.*;

public class Texto {

    public void analizar(String parrafo) {
        // ---------- Normalizar ----------
        parrafo = parrafo.toLowerCase().replaceAll("[^a-záéíóúñü\\s]", "");

        // ---------- Convertir en lista de palabras ----------
        List<String> palabras = Arrays.stream(parrafo.split("\\s+"))
                .filter(p -> !p.isEmpty()) // eliminar vacíos
                .collect(Collectors.toList());

        // ---------- Transformar ----------
        // Ejemplo: convertir todas las palabras a mayúsculas
        List<String> palabrasMayus = palabras.stream()
                .map(String::toUpperCase)
                .toList();
        System.out.println("\nTransformación (a mayúsculas):");
        System.out.println(palabrasMayus);

        // ---------- Filtrar ----------
        // Ejemplo: solo palabras con más de 4 letras
        List<String> filtradas = palabras.stream()
                .filter(p -> p.length() > 4)
                .toList();
        System.out.println("\nFiltradas (más de 4 letras):");
        System.out.println(filtradas);

        // ---------- Reducir ----------
        // Ejemplo: contar total de letras usando reduce
        int totalLetras = palabras.stream()
                .map(String::length)
                .reduce(0, Integer::sum);
        System.out.println("\nTotal de letras en el texto: " + totalLetras);

        // ---------- Mapear y contar frecuencias ----------
        Map<String, Long> conteo = palabras.stream()
                .collect(Collectors.groupingBy(p -> p, Collectors.counting()));

        // ---------- Ordenar declarativamente ----------
        List<Map.Entry<String, Long>> top5 = conteo.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(5)
                .toList();

        // ---------- Mostrar resultado ----------
        System.out.println("\nTop 5 palabras más frecuentes:");
        IntStream.range(0, top5.size())
                .forEach(i -> {
                    Map.Entry<String, Long> e = top5.get(i);
                    System.out.println((i+1) + ". " + e.getKey() + " → " + e.getValue());
                });
    }
}
