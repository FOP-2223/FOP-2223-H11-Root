package h11.h3;

import h11.JsonUtils;
import h11.Random;
import h11.providers.RandomLSystemGenerator;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class ParsedLSystemTestCaseGenerator {

    private static final int NUMBER_OF_TEST_CASES = 5;

    private static final RandomLSystemGenerator generator = new RandomLSystemGenerator(new Random());

    public static void main(String[] args) throws IOException {
        var testCases = Stream
            .generate(generator::generate)
            .map(ParsedLSystemTestCase::new)
            .limit(NUMBER_OF_TEST_CASES)
            .toList();

        JsonUtils.writePojo(Paths.get("parsed-lsystem-test.json"), testCases);
    }
}
