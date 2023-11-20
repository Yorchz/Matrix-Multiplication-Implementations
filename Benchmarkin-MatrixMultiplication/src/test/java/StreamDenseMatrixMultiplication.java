import com.model.builders.DenseMatrixBuilder;
import com.model.matrixes.DenseMatrix;
import com.model.operations.parallelism.StreamDenseMultiplication;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.*;

@RunWith(Parameterized.class)
public class StreamDenseMatrixMultiplication {
    public int size;


    public StreamDenseMatrixMultiplication(int size) {
        this.size = size;
    }

    @org.junit.Test
    public void multiply_two_random_dense_matrix() {
        DenseMatrix denseMatrixA = createRandomMatrix(this.size);
        DenseMatrix denseMatrixB = createRandomMatrix(this.size);
        StreamDenseMultiplication streamMultiplication = new StreamDenseMultiplication();
        DenseMatrix c = streamMultiplication.multiply(denseMatrixA,denseMatrixB);
        Vector vector = new Vector(this.size);
        assertThat(vector.multiply(c)).isEqualTo(vector.multiply(denseMatrixA).multiply(denseMatrixB));
    }

    private DenseMatrix createRandomMatrix(int size) {
        DenseMatrixBuilder builder = new DenseMatrixBuilder(this.size, this.size);
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                builder.set(i, j, random.nextDouble());
            }
        }
        return builder.toMatrix();
    }

    @Parameterized.Parameters
    public static List<Integer> parameters(){
        return Arrays.asList(10,100,1000);
    }

}
