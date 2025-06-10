package model;  // Define el paquete donde se encuentra la clase.

/**
 * Implementación secuencial de QuickSort.
 * Se utiliza como base para comparar el rendimiento con la versión concurrente.
 */
public class SequentialQuickSort {

    /**
     * Método público para ordenar un array completo.
     * Llama al método quickSort con los límites iniciales del array.
     */
    public static void sort(int[] array) {
        quickSort(array, 0, array.length - 1);  // Inicia el ordenamiento desde el primer hasta el último elemento.
    }

    /**
     * Implementación recursiva del algoritmo QuickSort.
     * Ordena los elementos dividiendo el array en particiones menores.
     */
    private static void quickSort(int[] array, int izquierda, int derecha) {
        if (izquierda < derecha) {  // Condición de parada de la recursión: si el índice izquierdo es menor que el derecho.
            int pivote = particionar(array, izquierda, derecha);  // Obtiene la posición del pivote tras la partición.
            quickSort(array, izquierda, pivote - 1);  // Ordena recursivamente la parte izquierda del pivote.
            quickSort(array, pivote + 1, derecha);  // Ordena recursivamente la parte derecha del pivote.
        }
    }

    /**
     * Método de partición: organiza los elementos alrededor del pivote.
     * Los menores van a la izquierda y los mayores a la derecha.
     */
    private static int particionar(int[] array, int izquierda, int derecha) {
        int pivote = array[derecha];  // Se elige el último elemento como pivote.
        int i = izquierda - 1;  // Inicializa el índice del menor elemento.

        // Recorre el array y coloca los valores menores al pivote a la izquierda.
        for (int j = izquierda; j < derecha; j++) {
            if (array[j] <= pivote) {  // Si el elemento es menor o igual al pivote, se intercambia.
                i++;  // Incrementa el índice del menor elemento.
                intercambiar(array, i, j);  // Intercambia los elementos para reorganizarlos.
            }
        }

        intercambiar(array, i + 1, derecha);  // Coloca el pivote en su posición final.
        return i + 1;  // Devuelve la posición del pivote.
    }

    /**
     * Método auxiliar para intercambiar dos elementos dentro del array.
     */
    private static void intercambiar(int[] array, int i, int j) {
        int temp = array[i];  // Guarda el valor del primer elemento en una variable temporal.
        array[i] = array[j];  // Asigna el valor del segundo elemento al primero.
        array[j] = temp;  // Asigna el valor almacenado en temp al segundo elemento.
    }
}
