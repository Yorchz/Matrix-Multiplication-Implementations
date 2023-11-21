# Multiplicación de Matrices y Tipos de Matrices

## Introducción

La multiplicación de matrices es una operación fundamental en ámbitos como la inteligencia artificial, la simulación numérica y la optimización. Diferentes tipos de matrices y métodos de multiplicación ofrecen diversas perspectivas para abordar este problema. Este README explora los distintos enfoques implementados en este repositorio.

## Explicación del Tema

La multiplicación de matrices es una operación matricial que combina las filas de la primera matriz con las columnas de la segunda matriz para producir una nueva matriz. Diversos métodos y tipos de matrices afectan la eficiencia y la complejidad de esta operación.

## Clases y Módulos

### Builders
- `DenseAtomicMatrixBuilder.java`: Construye matrices densas atómicas.
- `DenseMatrixBuilder.java`: Construye matrices densas.
- `SparseMatrixCCSBuilder.java`: Construye matrices dispersas en formato CCS (Compressed Column Storage).
- `SparseMatrixCOOBuilder.java`: Construye matrices dispersas en formato COO (Coordinate).
- `SparseMatrixCRSBuilder.java`: Construye matrices dispersas en formato CRS (Compressed Row Storage).

### Deserializer
- `MTXtoDenseMatrix.java`: Deserializa matrices densas desde el formato MTX.
- `MTXtoSparseMatrixCCS.java`: Deserializa matrices dispersas en formato CCS desde el formato MTX.
- `MTXtoSparseMatrixCOO.java`: Deserializa matrices dispersas en formato COO desde el formato MTX.
- `MTXtoSparseMatrixCRS.java`: Deserializa matrices dispersas en formato CRS desde el formato MTX.

### Matrixes
- `DenseAtomicMatrix.java`: Matriz densa atómica.
- `DenseMatrix.java`: Matriz densa.
- `SparseMatrixCCS.java`: Matriz dispersa en formato CCS.
- `SparseMatrixCOO.java`: Matriz dispersa en formato COO.
- `SparseMatrixCRS.java`: Matriz dispersa en formato CRS.

### Operations
#### Parallelism
- `ExecutorServiceDenseMatrixMul.java`: Multiplicación paralela de matrices densas usando ExecutorService.
- `ExecutorServiceSparseMatrixMul.java`: Multiplicación paralela de matrices dispersas usando ExecutorService.
- `StreamDenseMultiplication.java`: Multiplicación de matrices densas utilizando streams.
- `StreamSparseMultiplication.java`: Multiplicación de matrices dispersas utilizando streams.

#### Standard
- `RowMatrixMultiplication.java`: Multiplicación estándar de matrices.
- `SparseMatrixMultiplication.java`: Multiplicación estándar de matrices dispersas.
- `StandardMultiplication.java`: Implementación estándar de multiplicación de matrices.
- `TransposedMultiplication.java`: Multiplicación de matrices transpuestas.

#### Synchronization
- `AtomicDenseMatrixMul.java`: Multiplicación atómica de matrices densas.
- `DenseSemaphoreMultiplication.java`: Multiplicación de matrices densas con semáforos.
- `ThreadMultiplication.java`: Multiplicación de matrices utilizando threads.
- `ThreadTask.java`: Tarea de thread para la multiplicación de matrices.

### Utilización
El uso de estas clases y módulos depende de los requerimientos específicos de la aplicación. Consulta la documentación de cada clase para obtener información detallada sobre cómo integrarlas en tu proyecto.

## Contribuciones
¡Se alientan las contribuciones y sugerencias! Si tienes ideas para mejorar las implementaciones existentes, implementar nuevos tipos de matrices o métodos de multiplicación, ¡no dudes en enviar una solicitud de extracción!

Este repositorio pretende ser un recurso educativo y práctico para aquellos interesados en explorar y entender los diferentes enfoques y tipos de matrices en el contexto de la multiplicación matricial. ¡Explora, aprende y contribuye!
