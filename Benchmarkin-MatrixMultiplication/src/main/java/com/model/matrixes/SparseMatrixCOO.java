package com.model.matrixes;
import com.model.Matrix;

import java.util.List;

public class SparseMatrixCOO implements Matrix {

    private List<Integer> rowIndexes;
    private List<Integer> columnIndexes;
    private List<Double> values;

    int size;
    public SparseMatrixCOO(int size, List<Integer> rowIndexes, List<Integer> columnIndexes, List<Double> values) {
        this.size = size;
        this.rowIndexes = rowIndexes;
        this.columnIndexes = columnIndexes;
        this.values = values;
    }

    public List<Integer> getRowIndexes() {
        return rowIndexes;
    }

    public List<Integer> getColumnIndexes() {
        return columnIndexes;
    }

    public List<Double> getValues() {
        return values;
    }

    @Override
    public int size() {
        return this.size;
    }
}
