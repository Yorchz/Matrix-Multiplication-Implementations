package com.model.operations.parallelism;

import com.model.MatrixMultiplication;
import com.model.builders.DenseMatrixBuilder;
import com.model.matrixes.DenseMatrix;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorServiceDenseMatrixMul implements MatrixMultiplication<DenseMatrix, DenseMatrix, DenseMatrix> {

    int numThewads = Runtime.getRuntime().availableProcessors();

    ExecutorService service = Executors.newFixedThreadPool(numThewads);

    public DenseMatrix multiply(DenseMatrix a, DenseMatrix b) { //TODO intentar hacerlo con stream el primer i y despues llamar a la funcion submit
        DenseMatrixBuilder builder = new DenseMatrixBuilder(a.size(), a.size());
        for (int i = 0; i < a.size(); i++) {
            submitProcess(a, b, i, builder);
        }
        service.shutdown();
        try {
            service.awaitTermination(20, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return builder.toMatrix();
    }

    private void submitProcess(DenseMatrix a, DenseMatrix b, int i, DenseMatrixBuilder builder) {
        service.submit(() -> {
            for (int j = 0; j < a.size(); j++) {
                double suma = 0;
                for (int k = 0; k < a.size(); k++) {
                    suma += a.get(i, k) * b.get(k, j);
                }
                builder.set(i, j, suma);
            }
        });
    }
}
