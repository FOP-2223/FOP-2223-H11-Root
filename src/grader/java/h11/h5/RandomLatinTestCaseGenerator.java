package h11.h5;

import h11.JsonUtils;
import h11.Random;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class RandomLatinTestCaseGenerator {

    private static final Random random = new Random();

    private static final long NUMBER_OF_TEST_CASES = 20;

    private static final int MAX_LENGTH = 50;

    public static void main(String[] args) throws IOException {
        var testCases = Stream
            .generate(RandomLatinTestCaseGenerator::generateTestCase)
            .limit(NUMBER_OF_TEST_CASES)
            .toList();

        JsonUtils.writePojo(Paths.get("random-latin-test.json"), testCases);
    }

    private static RandomLatinTestCase generateTestCase() {
        var length = random.nextInt(1, MAX_LENGTH);
        var seed = random.nextLong();

        random.setSeed(seed);
        var output = random.latin(length);

        return new RandomLatinTestCase(seed, length, output);
    }
}
