package h11.h6;

import h11.Random;
import h11.TestCaseUtils;
import h11.providers.RandomLSystemGenerator;

import java.io.IOException;
import java.nio.file.Paths;

public class GenerateTestCaseGenerator {

    private static final Random random = new Random();

    private static final RandomLSystemGenerator lSystemGenerator = new RandomLSystemGenerator(random);

    public static void main(String[] args) throws IOException {
        TestCaseUtils.generateTestCases(Paths.get("generate-test.json"), () -> {
            var seed = random.nextLong();
            random.setSeed(seed);
            var projections = lSystemGenerator.generate();
            return new GenerateTestCase(seed, projections);
        });
    }
}
