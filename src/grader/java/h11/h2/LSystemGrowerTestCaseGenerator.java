package h11.h2;

import h11.*;
import h11.parse.ParsedLSystem;
import h11.parse.Projection;
import h11.providers.RandomLSystemGenerator;
import h11.tutor.TutorRandom;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

public class LSystemGrowerTestCaseGenerator {

    private static final long MAX_GROWTH = 10;

    private static final RandomLSystemGenerator generator = new RandomLSystemGenerator(new TutorRandom());

    public static void main(String[] args) throws IOException {
        TestCaseUtils.generateTestCases(Paths.get("lsystem-grower-test.json"), () -> {
            List<Projection> projections = generator.generate();
            List<String> growth = grow(projections);
            return new LSystemGrowerTestCase(projections, growth);
        });
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
