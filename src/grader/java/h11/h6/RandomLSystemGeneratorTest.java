package h11.h6;

import h11.parse.Projection;
import h11.providers.RandomLSystemGenerator;
import h11.tutor.TutorRandom;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junitpioneer.jupiter.json.JsonClasspathSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RandomLSystemGeneratorTest {

    @ParameterizedTest
    @JsonClasspathSource("h11/h6/make-projection-test.json")
    @Tag("H6")
    void testMakeProjection(MakeProjectionTestCase testCase) {
        var random = new TutorRandom(testCase.seed());
        var generator = new RandomLSystemGenerator(random);
        var projection = generator.makeProjection(testCase.source());
        var expected = new Projection(testCase.source(), testCase.destination());
        assertEquals(expected, projection);
    }

    @ParameterizedTest
    @ValueSource(ints = { 12, 3, 5, 67, 8, 2, 1 })
    @Tag("H6")
    void testThat_sourcesAreUnique(int seed) {
        var random = new TutorRandom(seed);
        var generator = new RandomLSystemGenerator(random);
        var projections = generator.generate();

        var uniqueSources = projections
            .stream()
            .map(Projection::source)
            .distinct()
            .toList();

        var noDuplicateSources = uniqueSources.size() == projections.size();
        assertTrue(noDuplicateSources);
    }

    @ParameterizedTest
    @JsonClasspathSource("h11/h6/generate-test.json")
    @Tag("H6")
    void testGenerate(GenerateTestCase testCase) {
        var random = new TutorRandom(testCase.seed());
        var generator = new RandomLSystemGenerator(random);
        var actual = generator.generate();
        assertEquals(testCase.projections(), actual);
    }
}
