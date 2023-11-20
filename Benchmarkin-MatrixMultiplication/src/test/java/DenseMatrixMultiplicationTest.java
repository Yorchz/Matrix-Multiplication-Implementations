import com.model.builders.DenseMatrixBuilder;
import com.model.matrixes.DenseMatrix;
import com.model.operations.parallelism.ExecutorServiceDenseMatrixMul;
import com.model.operations.parallelism.StreamDenseMultiplication;
import com.model.operations.standar.RowMatrixMultiplication;
import com.model.operations.standar.StandardMultiplication;
import com.model.operations.standar.TransposedMultiplication;
import com.model.operations.synchronization.DenseSemaphoreMultiplication;
import com.model.operations.synchronization.ThreadMultiplication;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@RunWith(Parameterized.class)
public class DenseMatrixMultiplicationTest {

    public int size;


    public DenseMatrixMultiplicationTest(int size) {
        this.size = size;
    }


    @Test
    public void multiply_two_random_dense_matrix() {
        DenseMatrix denseMatrixA = createRandomMatrix(this.size);
        DenseMatrix denseMatrixB = createRandomMatrix(this.size);
        StandardMultiplication standardMultiplication = new StandardMultiplication();
        DenseMatrix c = standardMultiplication.multiply(denseMatrixA,denseMatrixB);
        Vector vector = new Vector(this.size);
        Assertions.assertThat(vector.multiply(c)).isEqualTo(vector.multiply(denseMatrixA).multiply(denseMatrixA));
    }

    @Test
    public void multiply_two_random_dense_matrix_by_stream() {
        DenseMatrix denseMatrixA = createRandomMatrix(this.size);
        DenseMatrix denseMatrixB = createRandomMatrix(this.size);
        StreamDenseMultiplication streamDenseMultiplication = new StreamDenseMultiplication();
        DenseMatrix c = streamDenseMultiplication.multiply(denseMatrixA,denseMatrixB);
        Vector vector = new Vector(this.size);
        Assertions.assertThat(vector.multiply(c)).isEqualTo(vector.multiply(denseMatrixA).multiply(denseMatrixA));
    }

    @Test
    public void multiply_two_random_dense_matrix_by_threads() {
        DenseMatrix denseMatrixA = createRandomMatrix(this.size);
        DenseMatrix denseMatrixB = createRandomMatrix(this.size);
        ThreadMultiplication threadMultiplication = new ThreadMultiplication();
        DenseMatrix c = threadMultiplication.multiply(denseMatrixA,denseMatrixB);
        Vector vector = new Vector(this.size);
        Assertions.assertThat(vector.multiply(c)).isEqualTo(vector.multiply(denseMatrixA).multiply(denseMatrixA));
    }

    @Test
    public void multiply_two_random_dense_matrix_by_row() {
        DenseMatrix denseMatrixA = createRandomMatrix(this.size);
        DenseMatrix denseMatrixB = createRandomMatrix(this.size);
        RowMatrixMultiplication rowMultiplication = new RowMatrixMultiplication();
        DenseMatrix c = rowMultiplication.multiply(denseMatrixA,denseMatrixB);
        Vector vector = new Vector(this.size);
        Assertions.assertThat(vector.multiply(c)).isEqualTo(vector.multiply(denseMatrixA).multiply(denseMatrixA));
    }

    @Test
    public void multiply_two_random_dense_matrix_by_transpose() {
        DenseMatrix denseMatrixA = createRandomMatrix(this.size);
        DenseMatrix denseMatrixB = createRandomMatrix(this.size);
        TransposedMultiplication transposedMultiplication= new TransposedMultiplication();
        DenseMatrix c = transposedMultiplication.multiply(denseMatrixA,denseMatrixB);
        Vector vector = new Vector(this.size);
        Assertions.assertThat(vector.multiply(c)).isEqualTo(vector.multiply(denseMatrixA).multiply(denseMatrixA));
    }

    @Test
    public void multiply_two_random_dense_matrix_by_semaphores() {
        DenseMatrix denseMatrixA = createRandomMatrix(this.size);
        DenseMatrix denseMatrixB = createRandomMatrix(this.size);
        DenseSemaphoreMultiplication denseSemaphoreMultiplication = new DenseSemaphoreMultiplication();
        DenseMatrix c = denseSemaphoreMultiplication.multiply(denseMatrixA,denseMatrixB);
        Vector vector = new Vector(this.size);
        Assertions.assertThat(vector.multiply(c)).isEqualTo(vector.multiply(denseMatrixA).multiply(denseMatrixA));
    }


    @Test
    public void multiply_two_random_dense_matrix_by_executor_service() throws InterruptedException {
        DenseMatrix denseMatrixA = createRandomMatrix(this.size);
        DenseMatrix denseMatrixB = createRandomMatrix(this.size);
        ExecutorServiceDenseMatrixMul executorServiceMatrixMultiplication= new ExecutorServiceDenseMatrixMul();
        DenseMatrix c = executorServiceMatrixMultiplication.multiply(denseMatrixA,denseMatrixB);
        Vector vector = new Vector(this.size);
        Assertions.assertThat(vector.multiply(c)).isEqualTo(vector.multiply(denseMatrixA).multiply(denseMatrixA));
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