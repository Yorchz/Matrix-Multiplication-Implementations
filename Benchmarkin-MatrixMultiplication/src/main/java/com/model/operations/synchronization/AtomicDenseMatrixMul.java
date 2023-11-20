package com.model.operations.synchronization;

import com.google.common.util.concurrent.AtomicDouble;
import com.model.MatrixMultiplication;
import com.model.builders.DenseAtomicMatrixBuilder;
import com.model.builders.DenseMatrixBuilder;
import com.model.matrixes.DenseAtomicMatrix;
import com.model.matrixes.DenseMatrix;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class AtomicDenseMatrixMul implements MatrixMultiplication<DenseAtomicMatrix, DenseAtomicMatrix, DenseAtomicMatrix> {

    int numThewads = Runtime.getRuntime().availableProcessors();

    ExecutorService service = Executors.newFixedThreadPool(numThewads);

    @Override
    public DenseAtomicMatrix multiply(DenseAtomicMatrix a, DenseAtomicMatrix b) {
        DenseAtomicMatrixBuilder builder = new DenseAtomicMatrixBuilder(a.size(), a.size());
        for (int i = 0; i < a.size(); i++)
            submitProcess(a, b, i, builder);
        service.shutdown();
        try {
            service.awaitTermination(20, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return builder.toMatrix();
    }

    private void submitProcess(DenseAtomicMatrix a, DenseAtomicMatrix b, int i, DenseAtomicMatrixBuilder builder) {
        service.submit(() -> {
            for (int j = 0; j < a.size(); j++) {
                AtomicDouble suma = new AtomicDouble();
                for (int k = 0; k < a.size(); k++) {
                    suma.set((a.get(i, k).doubleValue() * b.get(k, j).doubleValue()) + suma.doubleValue());
                }
                builder.set(i, j, suma);
            }
        });
    }
}
