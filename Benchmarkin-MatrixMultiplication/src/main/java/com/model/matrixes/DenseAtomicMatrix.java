package com.model.matrixes;

import com.google.common.util.concurrent.AtomicDouble;
import com.model.Matrix;

public class DenseAtomicMatrix implements Matrix {

    private AtomicDouble[][] matrix;
    public DenseAtomicMatrix(AtomicDouble[][] matrix) {
        this.matrix = matrix;
    }

    public AtomicDouble[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(AtomicDouble[][] matrix) {
        this.matrix = matrix;
    }

    @Override
    public int size() {
        return this.matrix.length;
    }

    public AtomicDouble get(int row, int column) {
        return this.matrix[row][column];
    }
}
