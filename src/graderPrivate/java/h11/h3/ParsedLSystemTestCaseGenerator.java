package h11.h3;

import h11.TestCaseUtils;
import h11.providers.RandomLSystemGenerator;
import h11.tutor.TutorRandom;

import java.io.IOException;
import java.nio.file.Paths;

public class ParsedLSystemTestCaseGenerator {

    private static final RandomLSystemGenerator generator = new RandomLSystemGenerator(new TutorRandom());

    public static void main(String[] args) throws IOException {
        TestCaseUtils.generateTestCases(Paths.get("parsed-lsystem-test.json"), () ->
            new ParsedLSystemTestCase(generator.generate()));
    }
}
