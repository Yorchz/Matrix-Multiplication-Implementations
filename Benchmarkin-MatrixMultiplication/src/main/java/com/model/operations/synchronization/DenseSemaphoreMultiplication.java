package com.model.operations.synchronization;

import com.model.MatrixMultiplication;
import com.model.builders.DenseMatrixBuilder;
import com.model.matrixes.DenseMatrix;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class DenseSemaphoreMultiplication implements MatrixMultiplication<DenseMatrix, DenseMatrix, DenseMatrix> {

    int numThewads = Runtime.getRuntime().availableProcessors();

    ExecutorService service = Executors.newFixedThreadPool(numThewads);
    private Semaphore semaphore;

    public DenseMatrix multiply(DenseMatrix a, DenseMatrix b) {
        DenseMatrixBuilder builder = new DenseMatrixBuilder(a.size(), a.size());
        semaphore = new Semaphore(1);

        for (int i = 0; i < a.size(); i++) {
            for (int j = 0; j < a.size(); j++)
                submitProcess(a, b, i, j, builder);
        }
        try {
            service.shutdown();
            service.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }
        return builder.toMatrix();
    }

    private void submitProcess(DenseMatrix a, DenseMatrix b, int i, int j, DenseMatrixBuilder builder) {
        service.submit(() -> {
            double suma = 0;
            for (int k = 0; k < a.size(); k++) {
                suma += a.get(i, k) * b.get(k, j);
            }
            try {
                semaphore.acquire();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            builder.set(i, j, suma);
            semaphore.release();
        });

    }
}