package com.model.deserializer;
import com.model.COOfromMTXReader;
import com.model.Deserializer;
import com.model.builders.SparseMatrixCRSBuilder;
import com.model.matrixes.SparseMatrixCRS;
import java.io.BufferedReader;
import java.io.IOException;

public class MTXtoSparseMatrixCRS implements COOfromMTXReader, Deserializer {
    private int nRows;
    private int nColumns;
    private int nNonZeros;

    @Override
    public SparseMatrixCRS deserialize(String filename) throws IOException {
        BufferedReader br = MTXReader(filename);
        String[] matrixFormat = getMatrixFormat(br);
        String line;

        this.nRows = Integer.parseInt(matrixFormat[0]);
        this.nColumns = Integer.parseInt(matrixFormat[1]);
        this.nNonZeros = Integer.parseInt(matrixFormat[2]);

        SparseMatrixCRSBuilder sparseMatrixCRSBuilder = new SparseMatrixCRSBuilder(this.nRows);

        int row_ind_from_last_first_non_null_element_of_row_pointer = -1;
        int value_of_the_current_non_null_element = 0;

        while (true) {
            line = br.readLine();
            if (line == null)  break;
            matrixFormat = line.split("( )+");

            if (row_ind_from_last_first_non_null_element_of_row_pointer != Integer.parseInt(matrixFormat[0]) - 1) {
                sparseMatrixCRSBuilder.addRowPrt(value_of_the_current_non_null_element);
                row_ind_from_last_first_non_null_element_of_row_pointer = Integer.parseInt(matrixFormat[0]) - 1;
            }
            sparseMatrixCRSBuilder.addValue((Double.valueOf(matrixFormat[2].trim())).doubleValue());
            sparseMatrixCRSBuilder.addColumnIndex(Integer.parseInt(matrixFormat[1]) - 1);
            value_of_the_current_non_null_element++;

        }
        sparseMatrixCRSBuilder.addRowPrt(value_of_the_current_non_null_element);
        return sparseMatrixCRSBuilder.toMatrix();
    }
}
