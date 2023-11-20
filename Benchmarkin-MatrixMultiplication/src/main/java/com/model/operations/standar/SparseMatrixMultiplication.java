package com.model.operations.standar;
import com.model.MatrixMultiplication;
import com.model.matrixes.SparseMatrixCOO;
import com.model.builders.SparseMatrixCOOBuilder;
import com.model.matrixes.SparseMatrixCCS;
import com.model.matrixes.SparseMatrixCRS;

public class SparseMatrixMultiplication implements MatrixMultiplication<SparseMatrixCOO, SparseMatrixCRS, SparseMatrixCCS> {

    @Override
    public SparseMatrixCOO multiply(SparseMatrixCRS a, SparseMatrixCCS b) {
            SparseMatrixCOOBuilder builder = new SparseMatrixCOOBuilder(a.size());
            for (int i = 0; i < a.size(); i++) {
                for (int j = 0; j < b.size(); j++) {
                    int ii = a.getNotNullRowPointers().get(i);
                    int iEnd = a.getNotNullRowPointers().get(i + 1);
                    int jj = b.getFirstNotNullPointer().get(j);
                    int jEnd = b.getFirstNotNullPointer().get(j + 1);
                    double value = 0;
                    while (ii < iEnd && jj < jEnd) {
                        int aa = a.getColIndex().get(ii);
                        int bb = b.getRowIndexes().get(jj);
                        if (aa == bb) {
                            value += a.getValues().get(ii) * b.getValues().get(jj);
                            ii++;
                            jj++;
                        }
                        else if (aa < bb) ii++;
                        else jj++;

                    }
                    if (value != 0) builder.set(i, j, value);
                }
            }
            return builder.toMatrix();
        }

}
