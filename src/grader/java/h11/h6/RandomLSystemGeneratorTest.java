package h11.h6;

import h11.Random;
import h11.parse.Projection;
import h11.providers.RandomLSystemGenerator;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junitpioneer.jupiter.json.JsonClasspathSource;

import static org.junit.jupiter.api.Assertions.*;

public class RandomLSystemGeneratorTest {

    @ParameterizedTest
    @JsonClasspathSource("h11/h6/make-projection-test.json")
    @Tag("H6")
    void testMakeProjection(MakeProjectionTestCase testCase) {
        var random = new Random(testCase.seed());
        var generator = new RandomLSystemGenerator(random);
        var projection = generator.makeProjection(testCase.source());
        var expected = new Projection(testCase.source(), testCase.destination());
        assertEquals(expected, projection);
    }

    @ParameterizedTest
    @JsonClasspathSource("h11/h6/generate-test.json")
    @Tag("H6")
    void testMakeProjection(GenerateTestCase testCase) {
        var random = new Random(testCase.seed());
        var generator = new RandomLSystemGenerator(random);
        var actual = generator.generate();
        assertEquals(testCase.projections(), actual);
    }
}
