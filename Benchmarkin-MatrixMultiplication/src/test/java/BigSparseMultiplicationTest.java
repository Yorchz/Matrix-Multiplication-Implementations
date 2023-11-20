import com.model.deserializer.MTXtoSparseMatrixCCS;
import com.model.deserializer.MTXtoSparseMatrixCRS;
import com.model.matrixes.SparseMatrixCCS;
import com.model.matrixes.SparseMatrixCOO;
import com.model.matrixes.SparseMatrixCRS;
import com.model.operations.standar.SparseMatrixMultiplication;
import org.junit.Test;

import java.io.IOException;


public class BigSparseMultiplicationTest {
    public int size;

    @Test
    public void multiply_two_MTX_Compressed_Matrix() throws IOException {

        MTXtoSparseMatrixCRS deserializer2 = new MTXtoSparseMatrixCRS();
        SparseMatrixCRS m2 = deserializer2.deserialize(System.getProperty("user.dir") + "/src/test/mtx/pdb1HYS.mtx");

        MTXtoSparseMatrixCCS deserializer3 = new MTXtoSparseMatrixCCS();
        SparseMatrixCCS m3 = deserializer3.deserialize(System.getProperty("user.dir") + "/src/test/mtx/pdb1HYS.mtx");

        SparseMatrixMultiplication sparseMatrixMultiplication = new SparseMatrixMultiplication();
        SparseMatrixCOO matrix5 = sparseMatrixMultiplication.multiply(m2, m3);



    }
}