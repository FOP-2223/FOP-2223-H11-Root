package h11.h7;

import h11.Random;
import h11.TestCaseUtils;
import h11.providers.LSystemToRandomLinesConverter;

import java.io.IOException;
import java.nio.file.Paths;

public class RandomSpacingTestCaseGenerator {

    private static final Random random = new Random();

    public static void main(String[] args) throws IOException {
        TestCaseUtils.generateTestCases(Paths.get("random-spacing-test.json"), () -> {
            var seed = random.nextLong();

            random.setSeed(seed);
            var converter = new LSystemToRandomLinesConverter(random);
            var spaces = converter.generateSpaces();

            return new RandomSpacesTestCase(seed, spaces);
        });
    }

}
