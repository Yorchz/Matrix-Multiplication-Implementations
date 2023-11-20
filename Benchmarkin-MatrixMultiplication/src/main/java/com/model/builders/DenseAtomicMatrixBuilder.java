package com.model.builders;

import com.google.common.util.concurrent.AtomicDouble;
import com.model.Builder;
import com.model.matrixes.DenseAtomicMatrix;
import com.model.matrixes.DenseMatrix;

public class DenseAtomicMatrixBuilder implements Builder {

    private final AtomicDouble[][] matrix;

    public DenseAtomicMatrixBuilder(int nRows, int nColumns) {
        this.matrix = emptyMatrixGenerator(nRows, nColumns);
    }

    private AtomicDouble[][] emptyMatrixGenerator(int nRows, int nColumns) {
        AtomicDouble[][] matrix = new AtomicDouble[nRows][nColumns];
        for (int row = 0; row < nRows; row++)
            for (int col = 0; col < nColumns; col++)
                matrix[row][col] = new AtomicDouble();
        return matrix;
    }

    public void set(int row, int col, AtomicDouble value) {
        this.matrix[row][col] = value;
    }

    @Override
    public DenseAtomicMatrix toMatrix() {
        return new DenseAtomicMatrix(this.matrix);
    }
}
