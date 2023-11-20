package com.model.operations.parallelism;

import com.model.MatrixMultiplication;
import com.model.builders.SparseMatrixCOOBuilder;
import com.model.matrixes.SparseMatrixCCS;
import com.model.matrixes.SparseMatrixCOO;
import com.model.matrixes.SparseMatrixCRS;

import java.util.stream.IntStream;

public class StreamSparseMultiplication implements MatrixMultiplication<SparseMatrixCOO, SparseMatrixCRS, SparseMatrixCCS> {
    public SparseMatrixCOO multiply(SparseMatrixCRS matrix1, SparseMatrixCCS matrix2) {
        SparseMatrixCOOBuilder builder = new SparseMatrixCOOBuilder(matrix1.size());
        IntStream.range(0, matrix1.size() * matrix2.size())
                .parallel().forEach(id -> {
                    int rowA = id / matrix2.size();
                    int colB = id % matrix2.size();

                    double sum = 0.0;
                    // each nonzero in A row
                    for (int i = matrix1.getNotNullRowPointers().get(rowA); i < matrix1.getNotNullRowPointers().get(rowA+1); i++)
                        // each nonzero in B column
                        for (int j = matrix2.getFirstNotNullPointer().get(colB); j < matrix2.getFirstNotNullPointer().get(colB+1); j++) {
                            if (matrix1.getColIndex().get(i) == matrix2.getRowIndexes().get(j)) {
                                sum += matrix1.getValues().get(i) * matrix2.getValues().get(j);
                                break;
                            }
                    }
                    // add nonzero calculated values
                    if (sum != 0) {
                        builder.set(rowA,colB,sum);
                    }
                });
        return builder.toMatrix();
    }
}
