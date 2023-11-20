package benchmarking;

import com.google.common.util.concurrent.AtomicDouble;
import com.model.MatrixMultiplication;
import com.model.builders.DenseAtomicMatrixBuilder;
import com.model.matrixes.DenseAtomicMatrix;
import com.model.operations.synchronization.AtomicDenseMatrixMul;
import org.openjdk.jmh.annotations.*;

import java.util.Random;

@BenchmarkMode(Mode.AverageTime)
@Fork(value = 2)
@Warmup(iterations = 3, time = 2)
@Measurement(iterations = 3, time = 2)
public class AtomicMatrixBenchMark {

    private static final int SIZE = 100;
    private static final Random random = new Random();

    @Benchmark
    public void atomicDenseMatrixMul() {
        executeWith(new AtomicDenseMatrixMul());
    }

    private void executeWith(MatrixMultiplication matrixMultiplication) {
        matrixMultiplication.multiply(createRandomMatrix(SIZE), createRandomMatrix(SIZE));
    }

    private DenseAtomicMatrix createRandomMatrix(int size) {
        DenseAtomicMatrixBuilder builder = new DenseAtomicMatrixBuilder(size, size);
        AtomicDouble atomicDouble = new AtomicDouble();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                atomicDouble.set(random.nextDouble());
                builder.set(i, j, atomicDouble);
            }
        }
        return builder.toMatrix();
    }
}
