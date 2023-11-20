package com.model.operations.parallelism;

import com.model.MatrixMultiplication;
import com.model.builders.SparseMatrixCOOBuilder;
import com.model.matrixes.SparseMatrixCCS;
import com.model.matrixes.SparseMatrixCOO;
import com.model.matrixes.SparseMatrixCRS;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorServiceSparseMatrixMul implements MatrixMultiplication<SparseMatrixCOO, SparseMatrixCRS, SparseMatrixCCS> {
    int numThewads = Runtime.getRuntime().availableProcessors();

    ExecutorService service = Executors.newFixedThreadPool(numThewads);

    public SparseMatrixCOO multiply(SparseMatrixCRS a, SparseMatrixCCS b) { //TODO intentar hacerlo con stream el primer i y despues llamar a la funcion submit
        SparseMatrixCOOBuilder builder = new SparseMatrixCOOBuilder(a.size());
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

    private void submitProcess(SparseMatrixCRS a, SparseMatrixCCS b, int i, SparseMatrixCOOBuilder builder) {
        service.submit(() -> {
            for (int j = 0; j < b.size(); j++) {
                int ii = a.getNotNullRowPointers().get(i);
                int iEnd = a.getNotNullRowPointers().get(i + 1);
                int jj = b.getFirstNotNullPointer().get(j);
                int jEnd = b.getFirstNotNullPointer().get(j + 1);
                double sum = 0.0;
                while (ii < iEnd && jj < jEnd) {
                    int aa = a.getColIndex().get(ii);
                    int bb = b.getRowIndexes().get(jj);
                    if (aa == bb) {
                        sum += a.getValues().get(ii) * b.getValues().get(jj);
                        ii++;
                        jj++;
                    }
                    else if (aa < bb) ii++;
                    else jj++;

                }
                if (sum != 0) builder.set(i, j, sum);
            }
        });
    }
}
