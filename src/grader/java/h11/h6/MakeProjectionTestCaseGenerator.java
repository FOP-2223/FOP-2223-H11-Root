package h11.h6;

import h11.Random;
import h11.TestCaseUtils;
import h11.providers.RandomLSystemGenerator;

import java.io.IOException;
import java.nio.file.Paths;

public class MakeProjectionTestCaseGenerator {

    private static final Random random = new Random();

    private static final RandomLSystemGenerator lSystemGenerator = new RandomLSystemGenerator(random);

    public static void main(String[] args) throws IOException {
        TestCaseUtils.generateTestCases(Paths.get("make-projection-test.json"), () -> {
            var seed = random.nextLong();
            var source = random.latin(1);

            random.setSeed(seed);
            var projection = lSystemGenerator.makeProjection(source);

            return new MakeProjectionTestCase(seed, source, projection.destination());
        });
    }
}
