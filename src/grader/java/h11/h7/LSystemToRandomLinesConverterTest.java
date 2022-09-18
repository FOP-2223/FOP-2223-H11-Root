package h11.h7;

import h11.providers.LSystemToRandomLinesConverter;
import h11.tutor.TutorRandom;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junitpioneer.jupiter.json.JsonClasspathSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LSystemToRandomLinesConverterTest {

    @ParameterizedTest
    @JsonClasspathSource("h11/h7/random-spacing-test.json")
    @Tag("H7")
    void testRandomSpacing(RandomSpacesTestCase testCase) {
        var random = new TutorRandom(testCase.seed());
        var converter = new LSystemToRandomLinesConverter(random);
        assertEquals(testCase.spaces(), converter.generateSpaces());
    }

    @ParameterizedTest
    @JsonClasspathSource("h11/h7/lsystem-as-lines-test.json")
    @Tag("H7")
    void testLSystemAsLines(LSystemAsLinesTestCase testCase) {
        var random = new TutorRandom(testCase.seed());
        var converter = new LSystemToRandomLinesConverter(random);
        var actual = converter.lSystemAsLines(testCase.projections()).toList();
        assertEquals(testCase.lines(), actual);
    }
}
