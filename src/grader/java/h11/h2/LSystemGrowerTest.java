package h11.h2;

import h11.LSystem;
import h11.LSystemGrowerImpl;
import h11.StringUtils;
import h11.tutor.parse.TutorParsedLSystem;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junitpioneer.jupiter.json.JsonClasspathSource;
import org.tudalgo.algoutils.student.io.PropertyUtils;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class LSystemGrowerTest {

    private static final long STREAM_SIZE_CONSIDERED_INFINITE =
        PropertyUtils.getLongProperty("h11/h11-grader.properties", "STREAM_SIZE_CONSIDERED_INFINITE");

    @Test
    @Tag("H2")
    void testThat_streamIsInfinite() {
        var grower = new LSystemGrowerImpl<>(new LSystem<Character>() {
            @Override
            public Character getAxiom() {
                return 'A';
            }

            @Override
            public Stream<Character> project(Character e) {
                return Stream.of(e);
            }
        });

        var actual = grower
            .grow()
            .limit(STREAM_SIZE_CONSIDERED_INFINITE)
            .count();

        assertEquals(STREAM_SIZE_CONSIDERED_INFINITE, actual);
    }

    @ParameterizedTest
    @JsonClasspathSource("h11/h2/lsystem-grower-test.json")
    @Tag("H2")
    void testGrow(LSystemGrowerTestCase testCase) {
        LSystemGrowerImpl<Character> grower = getGrower(testCase);

        var actual = grower
            .grow()
            .limit(testCase.growth().size())
            .map(l -> StringUtils.join("", l))
            .toList();

        assertIterableEquals(testCase.growth(), actual);
    }

    private static LSystemGrowerImpl<Character> getGrower(LSystemGrowerTestCase testCase) {
        return new LSystemGrowerImpl<>(new TutorParsedLSystem(testCase.projections()));
    }

}
