package h11.h4;

import h11.fibs.FibonacciGenerator;
import h11.tutor.fibs.TutorFibonacciGenerator;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public abstract class FibonacciGeneratorTest {

    private final FibonacciGenerator tutorFibonaccigenerator = new TutorFibonacciGenerator();

    private final FibonacciGenerator fibonacciGenerator;

    protected FibonacciGeneratorTest(FibonacciGenerator fibonacciGenerator) {
        this.fibonacciGenerator = fibonacciGenerator;
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 4, 8, 16})
    @Tag("H4")
    void testThat_initialIsCorrect(int numberOfFibs) {
        var fibs = fibonacciGenerator.generate(numberOfFibs);
        assertTrue(fibs.size() >= 2);
        assertEquals(List.of(1, 2), fibs.subList(0, 2));
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 4, 8, 16})
    @Tag("H4")
    void testThat_fibsAreCorrect(int numberOfFibs) {
        var fibs = fibonacciGenerator.generate(numberOfFibs);
        assertEquals(numberOfFibs, fibs.size());
        assertEquals(tutorFibonaccigenerator.generate(numberOfFibs), fibs);
    }
}
