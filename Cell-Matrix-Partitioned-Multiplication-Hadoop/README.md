# Multiplicación de Matrices con Hadoop

## Introducción

La multiplicación de matrices es una operación computacionalmente intensiva, especialmente cuando se trata de conjuntos de datos masivos. Este proyecto aborda el desafío de la multiplicación de matrices en un entorno distribuido utilizando Apache Hadoop, una plataforma de procesamiento de grandes volúmenes de datos. El enfoque principal consiste en aplicar estrategias eficientes para manejar grandes conjuntos de datos y optimizar la eficiencia de la operación.

## Explicación del Tema

La multiplicación de matrices con Hadoop se vuelve esencial cuando lidiamos con datos que no caben en la memoria de una sola máquina. La estrategia central aquí es dividir la tarea en bloques manejables y distribuirlos en un clúster de nodos Hadoop para su procesamiento paralelo. En este proyecto, se implementa una estrategia específica: multiplicación de matrices de celda y particionamiento de la salida en submatrices de tamaño fijo. Esto permite gestionar eficientemente los datos en un entorno distribuido, aprovechando la capacidad de procesamiento paralelo de Hadoop.

## Clases y Módulos

### `Coordinate.java`

Esta clase representa las coordenadas de una matriz, esencial para el procesamiento de datos durante la fase de mapeo.

### `Main.java`

La clase principal configura y ejecuta el trabajo Hadoop. Aquí se definen los parámetros esenciales, como el tamaño de las matrices y otros ajustes de configuración.

### `Mapper.java`

Implementa la lógica del mapeador en el contexto de Hadoop para la multiplicación de matrices. Divide la tarea en subproblemas manejables y emite pares clave-valor para la fase de reducción.

### `Matrix.java`

Esta interfaz define métodos esenciales para trabajar con matrices, proporcionando una abstracción para las diferentes implementaciones de matrices utilizadas en el proyecto.

### `ReducedCoordinate.java`

Representa las coordenadas reducidas después de la fase de reducción en Hadoop. Estas coordenadas se utilizan para calcular el resultado final de la multiplicación.

### `Reducer.java`

Implementa la lógica del reductor en el contexto de Hadoop. Agrega y procesa las coordenadas reducidas para producir la salida final de la multiplicación de matrices.

## Utilización

Para ejecutar el programa, se debe configurar el tamaño de las matrices y otros parámetros relevantes en la clase `Main.java`. Luego, el trabajo Hadoop se ejecuta para realizar la multiplicación de matrices distribuida.

## Contribuciones

¡Se alientan activamente las contribuciones y sugerencias! Si tienes ideas para mejorar la eficiencia, implementar nuevas estrategias de particionamiento, o cualquier mejora en la multiplicación de matrices con Hadoop, ¡no dudes en enviar una solicitud de extracción!

Este proyecto pretende ser un recurso práctico y educativo para aquellos interesados en abordar la multiplicación de matrices en un entorno distribuido utilizando Hadoop. ¡Explora, aprende y contribuye!
