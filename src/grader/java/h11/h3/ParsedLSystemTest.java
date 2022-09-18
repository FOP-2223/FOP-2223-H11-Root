package h11.h3;

import h11.parse.ParsedLSystem;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junitpioneer.jupiter.json.JsonClasspathSource;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParsedLSystemTest {

    @ParameterizedTest
    @JsonClasspathSource("h11/h3/parsed-lsystem-test.json")
    @Tag("H3")
    void testThat_firstProjectionGivesAxiom(ParsedLSystemTestCase testCase) {
        var lSystem = new ParsedLSystem(testCase.projections());
        var expected = testCase.projections().get(0).source();
        assertEquals(expected, lSystem.getAxiom());
    }

    @ParameterizedTest
    @JsonClasspathSource("h11/h3/parsed-lsystem-test.json")
    @Tag("H3")
    void testThat_projectionsOfKnownProject(ParsedLSystemTestCase testCase) {
        var lSystem = new ParsedLSystem(testCase.projections());

        for (var p : testCase.projections()) {
            var actual = lSystem
                .project(p.source())
                .map(String::valueOf)
                .collect(Collectors.joining());

            assertEquals(p.destination(), actual);
        }
    }

    @ParameterizedTest
    @JsonClasspathSource("h11/h3/parsed-lsystem-test.json")
    @Tag("H3")
    void testThat_projectionsOfUnknownDoesNotProject(ParsedLSystemTestCase testCase) {
        var lSystem = new ParsedLSystem(testCase.projections());

        for (char c = '0'; c <= '1'; c++) {
            assertEquals(List.of(c), lSystem.project(c).toList());
        }
    }
}
