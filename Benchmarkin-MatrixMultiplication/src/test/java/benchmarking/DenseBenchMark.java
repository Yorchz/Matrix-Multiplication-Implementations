package benchmarking;


import com.model.MatrixMultiplication;
import com.model.builders.DenseMatrixBuilder;
import com.model.matrixes.DenseMatrix;
import com.model.operations.parallelism.ExecutorServiceDenseMatrixMul;
import com.model.operations.parallelism.StreamDenseMultiplication;
import com.model.operations.standar.RowMatrixMultiplication;
import com.model.operations.standar.StandardMultiplication;
import com.model.operations.standar.TransposedMultiplication;
import com.model.operations.synchronization.DenseSemaphoreMultiplication;
import com.model.operations.synchronization.ThreadMultiplication;
import org.openjdk.jmh.annotations.*;

import java.util.Random;

@BenchmarkMode(Mode.AverageTime)
@Fork(value = 2)
@Warmup(iterations = 3, time = 2)
@Measurement(iterations = 3, time = 2)
public class DenseBenchMark {
    private static final int SIZE = 100;
    private static final Random random = new Random();

    @Benchmark
    public void standardMatrixMultiplication() {
        executeWith(new StandardMultiplication());
    }

    @Benchmark
    public void rowMultiplication() {
        executeWith(new RowMatrixMultiplication());
    }

    @Benchmark
    public void transposedMultiplication() {
        executeWith(new TransposedMultiplication());
    }

    @Benchmark
    public void streamMultiplication() {
        executeWith(new StreamDenseMultiplication());
    }

    @Benchmark
    public void threadMultiplication() {
        executeWith(new ThreadMultiplication());
    }

    @Benchmark
    public void executorServiceMultiplication() {
        executeWith(new ExecutorServiceDenseMatrixMul());
    }

    @Benchmark
    public void semaphoreMultiplication() {
        executeWith(new DenseSemaphoreMultiplication());
    }

    private void executeWith(MatrixMultiplication matrixMultiplication) {
        matrixMultiplication.multiply(createRandomMatrix(SIZE), createRandomMatrix(SIZE));
    }

    private DenseMatrix createRandomMatrix(int size) {
        DenseMatrixBuilder builder = new DenseMatrixBuilder(size, size);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                builder.set(i, j, random.nextDouble());
            }
        }
        return builder.toMatrix();
    }
}



