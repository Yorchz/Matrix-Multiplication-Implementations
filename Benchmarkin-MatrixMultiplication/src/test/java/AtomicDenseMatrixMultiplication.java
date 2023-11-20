import com.google.common.util.concurrent.AtomicDouble;
import com.model.builders.DenseAtomicMatrixBuilder;
import com.model.builders.DenseMatrixBuilder;
import com.model.matrixes.DenseAtomicMatrix;
import com.model.matrixes.DenseMatrix;
import com.model.operations.synchronization.AtomicDenseMatrixMul;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@RunWith(Parameterized.class)
public class AtomicDenseMatrixMultiplication {

    public int size;

    public AtomicDenseMatrixMultiplication(int size) {
        this.size = size;
    }

    @Test
    public void multiply_two_random_atomic_dense_matrix() {
        DenseAtomicMatrix AtomicDenseMatrixA = createRandomMatrix(this.size);
        DenseAtomicMatrix denseMatrixB = createRandomMatrix(this.size);
        AtomicDenseMatrixMul atomicDenseMatrixMul = new AtomicDenseMatrixMul();
        DenseAtomicMatrix c = atomicDenseMatrixMul.multiply(AtomicDenseMatrixA,denseMatrixB);
        Vector vector = new Vector(this.size);
        Assertions.assertThat(vector.multiply(toDenseMatrix(c))).isEqualTo(vector.multiply(toDenseMatrix(AtomicDenseMatrixA)).multiply(toDenseMatrix(AtomicDenseMatrixA)));
    }

    private DenseAtomicMatrix createRandomMatrix(int size) {
        DenseAtomicMatrixBuilder builder = new DenseAtomicMatrixBuilder(size, size);
        Random random = new Random();
        AtomicDouble atomicDouble = new AtomicDouble();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                atomicDouble.set(random.nextDouble());
                builder.set(i, j, atomicDouble);
            }
        }
        return builder.toMatrix();
    }


    private DenseMatrix toDenseMatrix(DenseAtomicMatrix atomicDenseMatrix){
        DenseMatrixBuilder builder = new DenseMatrixBuilder(atomicDenseMatrix.size(), atomicDenseMatrix.size());
        for(int i = 0; i <  atomicDenseMatrix.size(); i++)
            for(int j = 0; j < atomicDenseMatrix.size(); j++){
                builder.set(i, j, atomicDenseMatrix.get(i,j).doubleValue());
            }
        return builder.toMatrix();
    }

    @Parameterized.Parameters
    public static List<Integer> parameters(){
        return Arrays.asList(10,100,1000);
    }

}
