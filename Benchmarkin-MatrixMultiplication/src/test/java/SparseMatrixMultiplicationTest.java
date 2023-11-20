import com.model.deserializer.MTXtoDenseMatrix;
import com.model.deserializer.MTXtoSparseMatrixCRS;
import com.model.matrixes.DenseMatrix;
import com.model.matrixes.SparseMatrixCOO;
import com.model.matrixes.SparseMatrixCRS;
import com.model.deserializer.MTXtoSparseMatrixCCS;
import com.model.matrixes.SparseMatrixCCS;
import com.model.operations.parallelism.ExecutorServiceSparseMatrixMul;
import com.model.operations.standar.SparseMatrixMultiplication;
import com.model.operations.parallelism.StreamSparseMultiplication;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import com.model.builders.DenseMatrixBuilder;

import java.io.IOException;

public class SparseMatrixMultiplicationTest {


    @Test
    public void multiply_two_sparse_matrix() throws IOException {
        MTXtoSparseMatrixCRS deserializer = new MTXtoSparseMatrixCRS();
        MTXtoSparseMatrixCCS deserializer2 = new MTXtoSparseMatrixCCS();
        MTXtoDenseMatrix deserializer3 = new MTXtoDenseMatrix();


        String filename = System.getProperty("user.dir") + "/src/test/mtx/paper.mtx";

        SparseMatrixCRS a = deserializer.deserialize(filename);
        SparseMatrixCCS b = deserializer2.deserialize(filename);


        SparseMatrixMultiplication sparseMatrixMultiplication = new SparseMatrixMultiplication();
        SparseMatrixCOO c = sparseMatrixMultiplication.multiply(a,b);

        DenseMatrix a1 = deserializer3.deserialize(filename);
        DenseMatrix c1 = sparseMatrixCOOtoDenseMatrix(c);

        Vector vector = new Vector(c1.size());
        Assertions.assertThat(vector.multiply(c1)).isEqualTo(vector.multiply(a1).multiply(a1));

    }

    @Test
    public void multiply_two_sparse_matrix_by_stream() throws IOException {
        MTXtoSparseMatrixCRS deserializer = new MTXtoSparseMatrixCRS();
        MTXtoSparseMatrixCCS deserializer2 = new MTXtoSparseMatrixCCS();
        MTXtoDenseMatrix deserializer3 = new MTXtoDenseMatrix();


        String filename = System.getProperty("user.dir") + "/src/test/mtx/paper.mtx";

        SparseMatrixCRS a = deserializer.deserialize(filename);
        SparseMatrixCCS b = deserializer2.deserialize(filename);


        StreamSparseMultiplication streamSparseMultiplication = new StreamSparseMultiplication();
        SparseMatrixCOO c = streamSparseMultiplication.multiply(a,b);

        DenseMatrix a1 = deserializer3.deserialize(filename);
        DenseMatrix c1 = sparseMatrixCOOtoDenseMatrix(c);

        Vector vector = new Vector(c1.size());
        Assertions.assertThat(vector.multiply(c1)).isEqualTo(vector.multiply(a1).multiply(a1));

    }

    @Test
    public void multiply_two_sparse_matrix_by_executor_service() throws IOException, InterruptedException {
        MTXtoSparseMatrixCRS deserializer = new MTXtoSparseMatrixCRS();
        MTXtoSparseMatrixCCS deserializer2 = new MTXtoSparseMatrixCCS();
        MTXtoDenseMatrix deserializer3 = new MTXtoDenseMatrix();


        String filename = System.getProperty("user.dir") + "/src/test/mtx/paper.mtx";

        SparseMatrixCRS a = deserializer.deserialize(filename);
        SparseMatrixCCS b = deserializer2.deserialize(filename);


        ExecutorServiceSparseMatrixMul executorServiceSparseMatrixMul = new ExecutorServiceSparseMatrixMul();
        SparseMatrixCOO c = executorServiceSparseMatrixMul.multiply(a,b);

        DenseMatrix a1 = deserializer3.deserialize(filename);
        DenseMatrix c1 = sparseMatrixCOOtoDenseMatrix(c);

        Vector vector = new Vector(c1.size());
        Assertions.assertThat(vector.multiply(c1)).isEqualTo(vector.multiply(a1).multiply(a1));

    }

    @Test
    public void multiply_two_big_sparse_matrix() throws IOException {
        MTXtoSparseMatrixCRS deserializer = new MTXtoSparseMatrixCRS();
        MTXtoSparseMatrixCCS deserializer2 = new MTXtoSparseMatrixCCS();
        MTXtoDenseMatrix deserializer3 = new MTXtoDenseMatrix();


        String filename = System.getProperty("user.dir") + "/src/test/mtx/paper.mtx";

        SparseMatrixCRS a = deserializer.deserialize(filename);
        SparseMatrixCCS b = deserializer2.deserialize(filename);


        SparseMatrixMultiplication sparseMatrixMultiplication = new SparseMatrixMultiplication();
        SparseMatrixCOO c = sparseMatrixMultiplication.multiply(a,b);

        DenseMatrix a1 = deserializer3.deserialize(filename);
        DenseMatrix c1 = sparseMatrixCOOtoDenseMatrix(c);

        Vector vector = new Vector(c1.size());
        Assertions.assertThat(vector.multiply(c1)).isEqualTo(vector.multiply(a1).multiply(a1));



    }

    public DenseMatrix sparseMatrixCOOtoDenseMatrix(SparseMatrixCOO matrix) {

        DenseMatrixBuilder new_matrix = new DenseMatrixBuilder(matrix.size(), matrix.size());
        for (int i = 0; i < matrix.getValues().size(); i++) {
            new_matrix.set(matrix.getRowIndexes().get(i), matrix.getColumnIndexes().get(i), matrix.getValues().get(i));
        }

        return new_matrix.toMatrix();
    }

}