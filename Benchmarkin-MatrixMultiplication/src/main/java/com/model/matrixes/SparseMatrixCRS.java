package com.model.matrixes;
import com.model.Matrix;
import java.util.List;

public class SparseMatrixCRS implements Matrix {

    private List<Integer> notNullRowPointers;
    private List<Integer> colIndex;
    private List<Double> values;

    int size;
    public SparseMatrixCRS(int size, List<Integer> notNullRowPointers, List<Integer> colIndex, List<Double> values) {
        this.size = size;
        this.notNullRowPointers = notNullRowPointers;
        this.colIndex = colIndex;
        this.values = values;
    }

    public List<Integer> getNotNullRowPointers() {
        return notNullRowPointers;
    }

    public List<Integer> getColIndex() {
        return colIndex;
    }

    public List<Double> getValues() {
        return values;
    }

    @Override
    public int size() {
        return this.size;
    }
}
