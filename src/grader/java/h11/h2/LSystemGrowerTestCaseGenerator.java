package h11.h2;

import h11.JsonUtils;
import h11.LSystemGrowerImpl;
import h11.Random;
import h11.StringUtils;
import h11.parse.ParsedLSystem;
import h11.parse.Projection;
import h11.providers.RandomLSystemGenerator;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class LSystemGrowerTestCaseGenerator {

    private static final long MAX_GROWTH = 10;

    private static final long NUMBER_OF_TEST_CASES = 100;

    private static final RandomLSystemGenerator generator = new RandomLSystemGenerator(new Random());

    public static void main(String[] args) throws IOException {
        var testCases = Stream
            .generate(LSystemGrowerTestCaseGenerator::generateTestCase)
            .limit(NUMBER_OF_TEST_CASES)
            .toList();

        JsonUtils.writePojo(Paths.get("lsystem-grower-test.json"), testCases);
    }

    private static LSystemGrowerTestCase generateTestCase() {
        List<Projection> projections = generator.generate();
        List<String> growth = grow(projections);
        return new LSystemGrowerTestCase(projections, growth);
    }

    private static List<String> grow(List<Projection> projections) {
        var lSystem = new ParsedLSystem(projections);
        var grower = new LSystemGrowerImpl<>(lSystem);

        return grower.grow()
            .limit(MAX_GROWTH)
            .map(l -> StringUtils.join("", l))
            .toList();
    }

}
