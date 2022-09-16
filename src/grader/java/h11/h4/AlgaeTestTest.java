package h11.h4;

import h11.fibs.AlgaeTest;
import h11.fibs.FibonacciGenerator;
import h11.tutor.fibs.TutorFibonacciGenerator;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.opentest4j.AssertionFailedError;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AlgaeTestTest {

    private static final FibonacciGenerator TUTOR_FIBONACCI_GENERATOR = new TutorFibonacciGenerator();

    @ParameterizedTest
    @ValueSource(ints = {8, 16, 20, 24, 30})
    @Tag("H4")
    void testThat_algaeTestAcceptsPositive(int numberOfFibs) {
        var algaeTest = new MockAlgaeTest(TUTOR_FIBONACCI_GENERATOR);
        assertDoesNotThrow(() ->
            algaeTest.testAlgaeGeneratesFibs(numberOfFibs));
    }

    @ParameterizedTest
    @ValueSource(ints = {8, 16, 20, 24, 30})
    @Tag("H4")
    void testThat_algaeTestRejectsWrongSize(int numberOfFibs) {
        var algaeTest = new MockAlgaeTest(n ->
            TUTOR_FIBONACCI_GENERATOR.generate(numberOfFibs-1));

        assertThrows(AssertionFailedError.class, () ->
            algaeTest.testAlgaeGeneratesFibs(numberOfFibs));
    }

    @ParameterizedTest
    @ValueSource(ints = {8, 16, 20, 24, 30})
    @Tag("H4")
    void testThat_algaeTestRejectsWrongValues(int numberOfFibs) {
        var algaeTest = new MockAlgaeTest(n -> {
            var fibs = new ArrayList<>(TUTOR_FIBONACCI_GENERATOR.generate(numberOfFibs-1));
            fibs.set(fibs.size()-2, 0);
            return fibs;
        });

        assertThrows(AssertionFailedError.class, () ->
            algaeTest.testAlgaeGeneratesFibs(numberOfFibs));
    }

    private static class MockAlgaeTest extends AlgaeTest {

        protected MockAlgaeTest(FibonacciGenerator algaeFibonacciGenerator) {
            super(TUTOR_FIBONACCI_GENERATOR, algaeFibonacciGenerator);
        }
    }
}
