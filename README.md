COMPARACIÓN DE QUICKSORT SECUENCIAL Y CONCURRENTE EN JAVA

Descripción:
Este proyecto implementa QuickSort en Java en sus versiones secuencial y concurrente, aprovechando la concurrencia con el framework Fork/Join para mejorar el rendimiento en grandes volúmenes de datos.
QuickSort Secuencial: Sigue el enfoque clásico de divide y vencerás, ordenando los elementos de forma recursiva.
QuickSort Concurrente: Divide el trabajo en múltiples hilos utilizando ForkJoinPool, optimizando el uso de procesadores multinúcleo.
Pruebas de rendimiento: Se comparan tiempos de ejecución y se calcula el speedup, demostrando las ventajas de la concurrencia en arreglos grandes.

Requisitos:
Java 8 o superior.
Sistema operativo Windows, Linux o macOS.
Procesador multinúcleo recomendado para maximizar el rendimiento concurrente.
