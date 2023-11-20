package com.model.operations.synchronization;

import com.model.builders.DenseMatrixBuilder;
import com.model.matrixes.DenseMatrix;

public class ThreadTask implements Runnable {

    private DenseMatrix a;
    private DenseMatrix b;

    private DenseMatrixBuilder builder;
    private int start, n;

    public ThreadTask(DenseMatrix a, DenseMatrix b, int start, int n, DenseMatrixBuilder builder) {
        this.a = a;
        this.b = b;
        this.start = start;
        this.n = n;
        this.builder = builder;
    }

    public void run() {
        for(int i = start; i<n+start && i< a.size(); i++)
            for(int j = 0; j< a.size(); j++) {
                double sum = 0.0;
                for (int k = 0; k < a.size(); k++) {
                    sum += a.get(i, k) * b.get(k, j);
                }
                builder.set(i, j, sum);
            }
    }
}
