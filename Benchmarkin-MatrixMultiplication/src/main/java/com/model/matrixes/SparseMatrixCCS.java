package com.model.matrixes;
import com.model.Matrix;

import java.util.List;

public class SparseMatrixCCS implements Matrix {

    private List<Integer> rowIndexes;
    private List<Integer> pointers;
    private List<Double> values;

    int size;
    public SparseMatrixCCS(int size, List<Integer> rowIndexes, List<Integer> pointers, List<Double> values) {
        this.size = size;
        this.rowIndexes = rowIndexes;
        this.pointers = pointers;
        this.values = values;
    }

    public List<Integer> getRowIndexes() {
        return rowIndexes;
    }

    public List<Integer> getFirstNotNullPointer() {
        return pointers;
    }

    public List<Double> getValues() {
        return values;
    }

    @Override
    public int size() {
        return this.size;
    }
}
