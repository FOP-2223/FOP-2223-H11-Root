package h11.h5;

import h11.Random;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junitpioneer.jupiter.json.JsonClasspathSource;

import static org.junit.jupiter.api.Assertions.*;

public class RandomTest {

    @ParameterizedTest
    @JsonClasspathSource("h11/h5/random-choices-test.json")
    @Tag("H5")
    void testChoices(RandomChoicesTestCase testCase) {
        var random = new Random(testCase.seed());
        var actual = random
            .choices(testCase.input())
            .limit(testCase.output().size())
            .toList();
        assertIterableEquals(testCase.output(), actual);
    }

    @ParameterizedTest
    @JsonClasspathSource("h11/h5/random-latin-test.json")
    @Tag("H5")
    void testLatin(RandomLatinTestCase testCase) {
        var random = new Random(testCase.seed());
        var actual = random.latin(testCase.length());
        assertEquals(testCase.output(), actual);
    }
}
