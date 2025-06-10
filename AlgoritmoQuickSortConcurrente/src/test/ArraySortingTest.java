package test;  // Define el paquete donde se encuentra la clase de prueba.

import model.ConcurrentQuickSort;  // Importa la versión concurrente de QuickSort.
import model.SequentialQuickSort;  // Importa la versión secuencial de QuickSort.
import java.util.Random;  // Importa la clase Random para generar números aleatorios.

/**
 * Clase de prueba para evaluar el rendimiento de QuickSort en sus versiones secuencial y concurrente.
 * Se ejecutan pruebas con distintos tamaños de arreglos y se mide el tiempo de ejecución de cada versión.
 */
public class ArraySortingTest {

    /**
     * Método principal que ejecuta las pruebas de rendimiento.
     * Itera sobre diferentes tamaños de arreglos, aplicando QuickSort secuencial y concurrente.
     */
    public static void main(String[] args) {
        int[] tamaños = {10_000, 1_000_000, 10_000_000}; // Define los tamaños de prueba para los arreglos.

        for (int tamaño : tamaños) { // Itera sobre cada tamaño de arreglo.
            System.out.println("\nOrdenando un array de tamaño: " + tamaño);

            int[] original = generarArrayAleatorio(tamaño); // Genera un array aleatorio de tamaño específico.

            // Se crean copias del array original para cada método de ordenamiento.
            int[] copiaSecuencial = original.clone(); 
            int[] copiaConcurrente = original.clone(); 

            // Prueba del método QuickSort secuencial
            long inicioSecuencial = System.nanoTime(); // Registra el tiempo de inicio.
            SequentialQuickSort.sort(copiaSecuencial); // Ordena el array de forma secuencial.
            long finSecuencial = System.nanoTime(); // Registra el tiempo de finalización.
            long duracionSecuencial = (finSecuencial - inicioSecuencial) / 1_000_000; // Convierte el tiempo a milisegundos.
            System.out.println("Tiempo secuencial: " + duracionSecuencial + " ms");

            // Validación del resultado: verifica si el array quedó ordenado correctamente.
            if (!estaOrdenado(copiaSecuencial)) {
                System.err.println("Error: El array secuencial no está ordenado correctamente.");
            }

            // Prueba del método QuickSort concurrente
            long inicioConcurrente = System.nanoTime(); // Registra el tiempo de inicio.
            ConcurrentQuickSort.sort(copiaConcurrente); // Ordena el array utilizando concurrencia.
            long finConcurrente = System.nanoTime(); // Registra el tiempo de finalización.
            long duracionConcurrente = (finConcurrente - inicioConcurrente) / 1_000_000; // Convierte el tiempo a milisegundos.
            System.out.println("Tiempo concurrente: " + duracionConcurrente + " ms");

            // Validación del resultado: verifica si el array quedó ordenado correctamente.
            if (!estaOrdenado(copiaConcurrente)) {
                System.err.println("Error: El array concurrente no está ordenado correctamente.");
            }

            // Cálculo del speedup: compara el tiempo secuencial contra el concurrente.
            double speedup = (double) duracionSecuencial / duracionConcurrente;
            System.out.printf("Speedup: %.2f%n", speedup); // Muestra el resultado del speedup.
        }
    }

    /**
     * Genera un array de enteros aleatorios entre 0 y 9999.
     * @param tamaño Tamaño del array a generar.
     * @return Array con números aleatorios.
     */
    private static int[] generarArrayAleatorio(int tamaño) {
        Random random = new Random(); // Crea una instancia de Random para generar números aleatorios.
        int[] array = new int[tamaño]; // Declara el array con el tamaño especificado.
        for (int i = 0; i < tamaño; i++) { // Llena el array con números aleatorios en el rango [0, 9999].
            array[i] = random.nextInt(10_000);
        }
        return array; // Devuelve el array generado.
    }

    /**
     * Verifica si un array está ordenado de menor a mayor.
     * @param array Array a verificar.
     * @return true si está ordenado, false en caso contrario.
     */
    private static boolean estaOrdenado(int[] array) {
        for (int i = 0; i < array.length - 1; i++) { // Recorre el array.
            if (array[i] > array[i + 1]) { // Si un elemento es mayor que el siguiente, no está ordenado.
                return false;
            }
        }
        return true; // Si se recorren todos los elementos sin encontrar desorden, retorna true.
    }
}
