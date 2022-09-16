package h11.h5;

import h11.Random;
import h11.TestCaseUtils;

import java.io.IOException;
import java.nio.file.Paths;

public class RandomChoicesTestCaseGenerator {

    private static final Random random = new Random();

    private static final int MAX_INPUT_LENGTH = 50;

    public static void main(String[] args) throws IOException {
        TestCaseUtils.generateTestCases(Paths.get("random-choices-test.json"), () -> {
            var inputLength = random.nextInt(MAX_INPUT_LENGTH);
            var input = random
                .ints(inputLength, 1, 10)
                .mapToObj(random::latin)
                .distinct()
                .toArray(String[]::new);

            var outputLength = 10 * inputLength;
            var seed = random.nextLong();
            random.setSeed(seed);
            var output = random
                .choices(input)
                .limit(outputLength)
                .toList();

            return new RandomChoicesTestCase(seed, input, output);
        });
    }

}
