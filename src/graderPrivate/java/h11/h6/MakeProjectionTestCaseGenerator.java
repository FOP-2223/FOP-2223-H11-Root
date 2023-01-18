package h11.h6;

import h11.AbstractRandom;
import h11.TestCaseUtils;
import h11.providers.RandomLSystemGenerator;
import h11.tutor.TutorRandom;

import java.io.IOException;
import java.nio.file.Paths;

public class MakeProjectionTestCaseGenerator {

    private static final AbstractRandom random = new TutorRandom();

    private static final RandomLSystemGenerator lSystemGenerator = new RandomLSystemGenerator(random);

    public static void main(String[] args) throws IOException {
        TestCaseUtils.generateTestCases(Paths.get("make-projection-test-inclusive.json"), () -> {
            var seed = random.nextLong();
            var source = random.latin(1);

            random.setSeed(seed);
            var projection = lSystemGenerator.makeProjection(source);

            return new MakeProjectionTestCase(seed, source, projection.destination());
        });
    }
}
