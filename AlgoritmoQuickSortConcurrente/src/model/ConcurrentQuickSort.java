package model;  // Define el paquete donde se encuentra la clase.

import java.util.concurrent.RecursiveAction;  // Importa la clase que permite tareas recursivas en Fork/Join.
import java.util.concurrent.ForkJoinPool;  // Importa el pool de subprocesos Fork/Join para paralelización.

/**
 * Implementación de QuickSort concurrente utilizando ForkJoinPool.
 * Divide el trabajo entre múltiples núcleos usando el framework Fork/Join.
 */
public class ConcurrentQuickSort {

    private static final int UMBRAL_MINIMO = 1000;  // Define el umbral mínimo para dividir tareas en concurrencia.

    /**
     * Método público para ordenar un array usando ForkJoinPool.
     * Crea un pool común de hilos y ejecuta la tarea inicial de ordenamiento.
     */
    public static void sort(int[] array) {
        ForkJoinPool pool = ForkJoinPool.commonPool(); // Usa el pool común del sistema para ejecutar tareas concurrentes.
        pool.invoke(new QuickSortTask(array, 0, array.length - 1)); // Ejecuta la tarea principal de ordenamiento.
    }

    /**
     * Clase interna que representa una tarea recursiva de QuickSort.
     * Extiende RecursiveAction para ejecutarse en el framework Fork/Join.
     */
    private static class QuickSortTask extends RecursiveAction {

        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private final int[] array; // Array que se ordenará.
        private final int izquierda; // Índice izquierdo del subarray a ordenar.
        private final int derecha; // Índice derecho del subarray a ordenar.

        /**
         * Constructor de la tarea QuickSort concurrente.
         * Se inicializan los límites del subarray.
         */
        QuickSortTask(int[] array, int izquierda, int derecha) {
            this.array = array;
            this.izquierda = izquierda;
            this.derecha = derecha;
        }

        /**
         * Método principal que ejecuta el algoritmo QuickSort de forma concurrente.
         * Se divide en subproblemas según el umbral mínimo definido.
         */
        @Override
        protected void compute() {
            if (izquierda < derecha) { // Condición base para la recursión.
                int pivote = particionar(array, izquierda, derecha); // Obtiene la posición del pivote.

                // Criterio para evitar demasiadas tareas pequeñas: si el tamaño supera el umbral.
                if ((derecha - izquierda) > UMBRAL_MINIMO) {
                    // Divide el trabajo en dos tareas paralelas.
                    invokeAll(
                        new QuickSortTask(array, izquierda, pivote - 1), // Ordena la parte izquierda del pivote.
                        new QuickSortTask(array, pivote + 1, derecha) // Ordena la parte derecha del pivote.
                    );
                } else {
                    // Si el array es pequeño, se ordena de forma secuencial.
                    quickSortSecuencial(array, izquierda, pivote - 1);
                    quickSortSecuencial(array, pivote + 1, derecha);
                }
            }
        }

        /**
         * Partición estándar de QuickSort: coloca elementos menores a la izquierda
         * y mayores a la derecha del pivote.
         */
        private int particionar(int[] array, int izquierda, int derecha) {
            int pivote = array[derecha]; // Se elige el último elemento como pivote.
            int i = izquierda - 1; // Índice del menor elemento.

            for (int j = izquierda; j < derecha; j++) { // Recorre el array hasta el pivote.
                if (array[j] <= pivote) { // Si el elemento es menor o igual al pivote, se intercambia.
                    i++; 
                    intercambiar(array, i, j); // Intercambia los elementos.
                }
            }

            intercambiar(array, i + 1, derecha); // Ubica el pivote en su posición final.
            return i + 1; // Devuelve la posición final del pivote.
        }

        /**
         * Método auxiliar para intercambiar dos elementos en el array.
         */
        private void intercambiar(int[] array, int i, int j) {
            int temp = array[i]; // Guarda temporalmente el valor del primer elemento.
            array[i] = array[j]; // Asigna el valor del segundo elemento al primero.
            array[j] = temp; // Coloca el valor original del primero en el segundo.
        }

        /**
         * Implementación recursiva de QuickSort en versión secuencial.
         * Se usa cuando el tamaño del subarray es menor al umbral definido.
         */
        private void quickSortSecuencial(int[] array, int izquierda, int derecha) {
            if (izquierda < derecha) { // Condición de parada de la recursión.
                int pivote = particionar(array, izquierda, derecha); // Obtiene la posición del pivote.
                quickSortSecuencial(array, izquierda, pivote - 1); // Ordena la parte izquierda del pivote.
                quickSortSecuencial(array, pivote + 1, derecha); // Ordena la parte derecha del pivote.
            }
        }
    }
}

