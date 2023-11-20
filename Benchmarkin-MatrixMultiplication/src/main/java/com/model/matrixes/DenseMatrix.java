package com.model.matrixes;

import com.google.common.util.concurrent.AtomicDouble;
import com.model.Matrix;

public class DenseMatrix implements Matrix {

    private double[][] matrix;
    public DenseMatrix(double[][] matrix) {
        this.matrix = matrix;
    }

    public double[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(double[][] matrix) {
        this.matrix = matrix;
    }

    @Override
    public int size() {
        return this.matrix.length;
    }

    public double get(int row, int column) {
        return this.matrix[row][column];
    }

}
