package com.model.operations.parallelism;

import com.model.MatrixMultiplication;
import com.model.builders.DenseMatrixBuilder;
import com.model.matrixes.DenseMatrix;

import java.util.stream.IntStream;

public class StreamDenseMultiplication implements MatrixMultiplication<DenseMatrix, DenseMatrix, DenseMatrix> {

    public DenseMatrix multiply(DenseMatrix a, DenseMatrix b) {
        DenseMatrixBuilder builder = new DenseMatrixBuilder(a.size(), a.size());

        IntStream.range(0, b.size()).parallel()
                .forEach(i -> {
                    for (int j = 0; j < b.size(); j++) {
                        double sum = 0.0;
                        for (int k = 0; k < b.size(); k++) {
                            sum += a.get(i, k) * b.get(j, k);
                        }
                        builder.set(i, j, sum);
                    }
                });
        return builder.toMatrix();

    }
}
