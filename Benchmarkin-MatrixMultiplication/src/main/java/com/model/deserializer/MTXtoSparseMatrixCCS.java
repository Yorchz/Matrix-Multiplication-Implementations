package com.model.deserializer;
import com.model.COOfromMTXReader;
import com.model.Deserializer;
import com.model.builders.SparseMatrixCCSBuilder;
import com.model.matrixes.SparseMatrixCCS;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MTXtoSparseMatrixCCS implements COOfromMTXReader, Deserializer {
    private int nRows;
    private int nColumns;
    private int nNonZeros;

    private final List<Integer> row_indexes = new ArrayList<>();
    private final List<Integer> column_indexes = new ArrayList<>();
    private final List<Double> values = new ArrayList<>();

    @Override
    public SparseMatrixCCS deserialize(String filename) throws IOException {
        BufferedReader br = MTXReader(filename);
        String[] matrixFormat = getMatrixFormat(br);
        String line;

        this.nRows = Integer.parseInt(matrixFormat[0]);
        this.nColumns = Integer.parseInt(matrixFormat[1]);
        this.nNonZeros = Integer.parseInt(matrixFormat[2]);



        while (true) {
            line = br.readLine();
            if (line == null)  break;
            matrixFormat = line.split("( )+");

            row_indexes.add(Integer.parseInt(matrixFormat[0]) - 1);
            column_indexes.add(Integer.parseInt(matrixFormat[1]) - 1);
            values.add(Double.parseDouble(matrixFormat[2]));
        }

        int column_ind_from_last_first_non_null_element_of_column_pointer = -1;
        int value_of_the_current_non_null_element = 0;

        SparseMatrixCCSBuilder sparseMatrixCCSBuilder = new SparseMatrixCCSBuilder(nRows);

        for (int i = 0; i<this.nColumns;i++) {
            for (int j = 0; j<this.nNonZeros;j++) {
                if (i == column_indexes.get(j)) {
                    if (column_ind_from_last_first_non_null_element_of_column_pointer != column_indexes.get(j)) {
                        sparseMatrixCCSBuilder.addColumnPrt(value_of_the_current_non_null_element);
                        column_ind_from_last_first_non_null_element_of_column_pointer = column_indexes.get(j);
                    }
                    sparseMatrixCCSBuilder.addValue(values.get(j));
                    sparseMatrixCCSBuilder.addRowIndex(row_indexes.get(j));
                    value_of_the_current_non_null_element ++;
                }
            }
        }

        sparseMatrixCCSBuilder.addColumnPrt(value_of_the_current_non_null_element);

        return sparseMatrixCCSBuilder.toMatrix();

    }
}
