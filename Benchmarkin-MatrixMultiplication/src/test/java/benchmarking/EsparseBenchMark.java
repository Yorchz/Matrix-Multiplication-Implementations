package benchmarking;

import com.model.MatrixMultiplication;
import com.model.deserializer.MTXtoSparseMatrixCCS;
import com.model.deserializer.MTXtoSparseMatrixCRS;
import com.model.matrixes.SparseMatrixCCS;
import com.model.matrixes.SparseMatrixCRS;
import com.model.operations.parallelism.ExecutorServiceSparseMatrixMul;
import com.model.operations.parallelism.StreamSparseMultiplication;
import com.model.operations.standar.SparseMatrixMultiplication;
import org.openjdk.jmh.annotations.*;

import java.io.IOException;

@BenchmarkMode(Mode.AverageTime)
@Fork(value = 2)
@Warmup(iterations = 3, time = 2)
@Measurement(iterations = 3, time = 2)
public class EsparseBenchMark {

    @Benchmark
    public void sparseMatrixMultiplication() {
        executeWith(new SparseMatrixMultiplication());
    }

    @Benchmark
    public void executorServiceSparseMatrixMul() {
        executeWith(new ExecutorServiceSparseMatrixMul());
    }

    @Benchmark
    public void sparseStreamMultiplication() {
        executeWith(new StreamSparseMultiplication());
    }

    private void executeWith(MatrixMultiplication matrixMultiplication) {
        try {
            matrixMultiplication.multiply(MTXtoSparseMatrixCRS(), MTXtoSparseMatrixCCS());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public SparseMatrixCRS MTXtoSparseMatrixCRS() throws IOException {
        MTXtoSparseMatrixCRS deserializer = new MTXtoSparseMatrixCRS();
        String filename = System.getProperty("user.dir") + "/src/test/mtx/paper.mtx";
        return deserializer.deserialize(filename);

    }

    public SparseMatrixCCS MTXtoSparseMatrixCCS() throws IOException {
        MTXtoSparseMatrixCCS deserializer = new MTXtoSparseMatrixCCS();
        String filename = System.getProperty("user.dir") + "/src/test/mtx/paper.mtx";
        return deserializer.deserialize(filename);

    }
}
