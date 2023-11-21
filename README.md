# Dilema de la Multiplicación de Matrices

La multiplicación de matrices es un problema clásico y fundamental en el mundo de la programación y la computación científica. Aunque puede parecer una operación simple a primera vista, la eficiencia y la velocidad de la multiplicación de matrices pueden variar significativamente según el enfoque utilizado. Este repositorio aborda el dilema de la multiplicación de matrices, explorando diferentes enfoques y técnicas para mejorar el rendimiento.

## El Dilema

La multiplicación de matrices es una operación esencial en diversas disciplinas, desde la inteligencia artificial hasta la simulación numérica. Sin embargo, a medida que las matrices aumentan de tamaño, la complejidad computacional se vuelve un desafío. Dos enfoques comunes, la multiplicación tradicional y la multiplicación por bloques, destacan entre las estrategias utilizadas para abordar este dilema.

### Multiplicación Tradicional
El método clásico de multiplicación de matrices implica el cálculo de cada elemento de la matriz resultante de manera individual. Aunque es sencillo de implementar, su complejidad computacional es de orden cúbico (O(n^3)), lo que puede volverse prohibitivo para matrices grandes.

### Multiplicación por Bloques
La multiplicación por bloques divide las matrices en bloques más pequeños, reduciendo así la complejidad computacional al realizar operaciones en bloques más manejables. Este enfoque es especialmente útil para aprovechar la memoria caché y mejorar la eficiencia, pero requiere una implementación más cuidadosa.

## Proyectos en este Repositorio

### 1. Benchmarking de Multiplicación de Matrices
Este proyecto se centra en evaluar y comparar el rendimiento de diferentes algoritmos de multiplicación de matrices en Java. Se proporcionan implementaciones de varios enfoques, junto con herramientas de benchmarking para medir su eficiencia en términos de tiempo de ejecución y consumo de recursos.

#### Uso
```bash
cd benchmarking
java -jar MatrixMultiplicationBenchmark.jar
```

## 2. Multiplicación de Matrices con Hadoop

El segundo proyecto explora la multiplicación de matrices en un entorno distribuido utilizando Hadoop. Se implementa un modelo de multiplicación en bloque que aprovecha la capacidad de procesamiento paralelo de un clúster Hadoop.

### Requisitos
- Apache Hadoop instalado y configurado.

### Uso
```bash
cd hadoop-matrix-multiplication
hadoop jar MatrixMultiplication.jar input output
```

## Contacto

Para cualquier pregunta, sugerencia o colaboración, no dudes en ponerte en contacto con nosotros. Puedes enviarnos un correo electrónico a jorge.hdez.hdez@outlook.es

## Presentación de Otros Proyectos

Este repositorio forma parte de una colección más amplia de proyectos relacionados con la multiplicación de matrices. Explora otros repositorios en nuestro perfil para encontrar diferentes enfoques, implementaciones y soluciones relacionadas con este desafío computacional.

Este repositorio pretende ser un recurso educativo y práctico para aquellos interesados en explorar y entender los desafíos y soluciones relacionados con la multiplicación de matrices en el contexto de la programación y la computación distribuida. ¡Explora, aprende y contribuye!
