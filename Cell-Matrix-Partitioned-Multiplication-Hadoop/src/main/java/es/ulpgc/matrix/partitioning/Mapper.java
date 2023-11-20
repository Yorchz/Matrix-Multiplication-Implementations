package es.ulpgc.matrix.partitioning;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;

import java.io.IOException;

public class Mapper extends org.apache.hadoop.mapreduce.Mapper<LongWritable, Text, Text, Text>{


    private org.apache.hadoop.mapreduce.Mapper.Context context;

    @Override
    protected void map(LongWritable key, Text item, org.apache.hadoop.mapreduce.Mapper<LongWritable, Text, Text, Text>.Context context) throws IOException, InterruptedException {
        Configuration conf = context.getConfiguration();
        int size = Integer.parseInt(conf.get("size"));
        Coordinate coordinate = parseCoordinate(item.toString());

        Text outputKey = new Text();
        Text outputValue = new Text();
        if (coordinate.matrix.equals("A")) {
            processMatrixA(coordinate, size, outputKey, outputValue, context);
        } else {
            processMatrixB(coordinate, size, outputKey, outputValue, context);
        }
    }

    private void processMatrixA(Coordinate coordinate, int size, Text outputKey, Text outputValue, org.apache.hadoop.mapreduce.Mapper<LongWritable, Text, Text, Text>.Context context) throws IOException, InterruptedException {
        for (int i = 0; i < size; i++) {
            outputKey.set(coordinate.x + "," + i);
            outputValue.set(coordinate.matrix + "," + coordinate.y + "," + coordinate.value);
            writer(outputKey, outputValue, context);
        }
    }

    private void processMatrixB(Coordinate coordinate, int size, Text outputKey, Text outputValue, org.apache.hadoop.mapreduce.Mapper<LongWritable, Text, Text, Text>.Context context) throws IOException, InterruptedException {
        for (int i = 0; i < size; i++) {
            outputKey.set(i + "," + coordinate.y);
            outputValue.set("B," + coordinate.x + "," + coordinate.value);
            writer(outputKey, outputValue, context);
        }
    }

    private void writer (Text outputKey, Text outputValue, org.apache.hadoop.mapreduce.Mapper<LongWritable, Text, Text, Text>.Context context) throws IOException, InterruptedException {
        context.write(outputKey, outputValue);
    }

    private Coordinate parseCoordinate(String input) {
        return new Coordinate(input.toString().split(","));
    }

}
