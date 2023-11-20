package com.model.builders;
import com.model.Builder;
import com.model.matrixes.SparseMatrixCOO;
import java.util.ArrayList;
import java.util.List;

public class SparseMatrixCOOBuilder implements Builder {

    private final List<Integer> rowIndexes = new ArrayList<>();
    private final List<Integer> colIndexes = new ArrayList<>();
    private final List<Double> values = new ArrayList<>();

    int size;
    public SparseMatrixCOOBuilder(int size) {
        this.size = size;
    }

    public void set(int rowPointer, int colPointer, double value) {
        rowIndexes.add(rowPointer);
        colIndexes.add(colPointer);
        values.add(value);
    }

    @Override
    public SparseMatrixCOO toMatrix() {
        return new SparseMatrixCOO(size,
                rowIndexes,
                colIndexes,
                values);
    }
}
