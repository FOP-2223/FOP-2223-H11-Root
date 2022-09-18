package h11.h5;

import h11.AbstractRandom;
import h11.TestCaseUtils;
import h11.tutor.TutorRandom;

import java.io.IOException;
import java.nio.file.Paths;

public class RandomLatinTestCaseGenerator {

    private static final AbstractRandom random = new TutorRandom();

    private static final int MAX_LENGTH = 50;

    public static void main(String[] args) throws IOException {
        TestCaseUtils.generateTestCases(Paths.get("random-latin-test.json"), () -> {
            var length = random.nextInt(1, MAX_LENGTH);
            var seed = random.nextLong();

            random.setSeed(seed);
            var output = random.latin(length);

            return new RandomLatinTestCase(seed, length, output);
        });
    }
}
