package h11.h7;

import h11.AbstractRandom;
import h11.TestCaseUtils;
import h11.providers.LSystemToRandomLinesConverter;
import h11.providers.RandomLSystemGenerator;
import h11.tutor.TutorRandom;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Paths;

public class LSystemAsLinesTestCaseGenerator {

    private static final AbstractRandom random = new TutorRandom();

    private static final RandomLSystemGenerator lSystemGenerator = new RandomLSystemGenerator(random);

    @Test
    void generate() throws IOException {
        TestCaseUtils.generateTestCases(Paths.get("lsystem-as-lines-test-exclusive.json"), () -> {
            var seed = random.nextLong();
            var projections = lSystemGenerator.generate();

            random.setSeed(seed);
            var converter = new LSystemToRandomLinesConverter(random);
            var lines = converter
                .lSystemAsLines(projections)
                .toList();

            return new LSystemAsLinesTestCase(seed, projections, lines);
        });
    }

}
