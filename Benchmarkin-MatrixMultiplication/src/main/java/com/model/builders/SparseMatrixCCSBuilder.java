package com.model.builders;
import com.model.Builder;
import com.model.matrixes.SparseMatrixCCS;
import java.util.ArrayList;
import java.util.List;

public class SparseMatrixCCSBuilder implements Builder {

    private final List<Integer> rowIndexes = new ArrayList<>();
    private final List<Integer> notNullColPointers = new ArrayList<>();
    private final List<Double> values = new ArrayList<>();

    int size;
    public SparseMatrixCCSBuilder(int size) {
        this.size = size;
    }

    public void addRowIndex(int rowId) {
        rowIndexes.add(rowId);
    }

    public void addColumnPrt(int colPointer) {
        notNullColPointers.add(colPointer);
    }

    public void addValue(double value) {
        values.add(value);
    }

    @Override
    public SparseMatrixCCS toMatrix() {
        return new SparseMatrixCCS(this.size,
                rowIndexes,
                notNullColPointers,
                values);
    }
}


