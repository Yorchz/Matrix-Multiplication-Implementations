package com.model.builders;
import com.model.Builder;
import com.model.matrixes.SparseMatrixCRS;
import java.util.ArrayList;
import java.util.List;

public class SparseMatrixCRSBuilder implements Builder {

    private final List<Integer> notNullRowPointers = new ArrayList<>();
    private final List<Integer> colIndex = new ArrayList<>();
    private final List<Double> values = new ArrayList<>();

    private int size;
    public SparseMatrixCRSBuilder(int size) {
        this.size = size;
    }

    public void addRowPrt(int rowPointer) {
        notNullRowPointers.add(rowPointer);
    }

    public void addColumnIndex(int colId) {
        colIndex.add(colId);
    }

    public void addValue(double value) {
        values.add(value);
    }

    @Override
    public SparseMatrixCRS toMatrix() {
        return new SparseMatrixCRS(this.size, notNullRowPointers, colIndex, values);
    }
}


