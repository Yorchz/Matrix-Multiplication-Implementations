package com.model.operations.synchronization;

import com.model.MatrixMultiplication;
import com.model.builders.DenseMatrixBuilder;
import com.model.matrixes.DenseMatrix;

import java.util.LinkedList;

public class ThreadMultiplication implements MatrixMultiplication<DenseMatrix, DenseMatrix, DenseMatrix> {

    static int cores  = Runtime.getRuntime().availableProcessors();

    public DenseMatrix multiply (DenseMatrix a, DenseMatrix b) {
        DenseMatrixBuilder builder = new DenseMatrixBuilder(a.size(), b.size());

        Thread[] threads = new Thread[cores];
        int nAssingRows = a.size() / cores;

        for(int w=0;w<cores;w++) {
            int assignedRows = nAssingRows;
            if (w == cores-1)
                assignedRows += a.size() % cores;
            ThreadTask wc = new ThreadTask(a,b,nAssingRows*w, assignedRows , builder);
            threads[w] = new Thread(wc);
            threads[w].start();
        }

        for (int w=0;w<cores;w++) //TODO external function
            try {
                threads[w].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        return builder.toMatrix();
    }
}
