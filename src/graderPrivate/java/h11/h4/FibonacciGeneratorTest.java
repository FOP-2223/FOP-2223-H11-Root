package h11.h4;

import h11.fibs.FibonacciGenerator;
import h11.tutor.fibs.TutorFibonacciGenerator;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.tudalgo.algoutils.tutor.general.assertions.Assertions2;
import org.tudalgo.algoutils.tutor.general.assertions.Context;

import java.util.List;

import static h11.NoLoopAssertions.assertNoLoopsUsed;

public abstract class FibonacciGeneratorTest {

    private final Class<? extends FibonacciGenerator> classToTest;

    private final FibonacciGenerator tutorFibonaccigenerator = new TutorFibonacciGenerator();

    private final FibonacciGenerator fibonacciGenerator;

    protected FibonacciGeneratorTest(FibonacciGenerator fibonacciGenerator) {
        this.classToTest = fibonacciGenerator.getClass();
        this.fibonacciGenerator = fibonacciGenerator;
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 4, 8, 16})
    @Tag("H4")
    public void testThat_initialIsCorrect(int numberOfFibs) throws NoSuchMethodException {
        assertNoLoopsUsed(classToTest, "generate");
        var fibs = fibonacciGenerator.generate(numberOfFibs);
        Assertions2.assertTrue(fibs.size() >= 2, getContext(numberOfFibs), result ->
            "Too few fibonacci numbers were generated, was: "+ fibs.size());
        Assertions2.assertEquals(List.of(1, 2), fibs.subList(0, 2), getContext(numberOfFibs), result ->
            "The first two fibonacci numbers were returned incorrectly");
    }

    @Test
    @Tag("H4")
    public void testThat_fibsMatchHardcodedValues() throws NoSuchMethodException {
        assertNoLoopsUsed(classToTest, "generate");
        var expected = List.of(1, 2, 3, 5, 8, 13, 21);
        var fibs = fibonacciGenerator.generate(expected.size());
        Assertions2.assertEquals(expected.size(), fibs.size(), getContext(expected.size()), result ->
            "The wrong amount of fibonacci numbers was returned");
        Assertions2.assertEquals(expected, fibs, getContext(expected.size()), result ->
            "The fibonacci numbers were returned incorrectly");
    }


    @ParameterizedTest
    @ValueSource(ints = {2, 4, 8, 16})
    @Tag("H4")
    public void testThat_fibsAreCorrect(int numberOfFibs) throws NoSuchMethodException {
        assertNoLoopsUsed(classToTest, "generate");
        var fibs = fibonacciGenerator.generate(numberOfFibs);
        Assertions2.assertEquals(numberOfFibs, fibs.size(), getContext(numberOfFibs), result ->
            "The wrong amount of fibonacci numbers was returned");
        Assertions2.assertEquals(tutorFibonaccigenerator.generate(numberOfFibs), fibs, getContext(numberOfFibs), result ->
            "The fibonacci numbers were returned incorrectly");
    }

    private Context getContext(int numberOfFibs) throws NoSuchMethodException {
        return Assertions2.contextBuilder()
            .subject(classToTest.getMethod("generate", int.class))
            .add("numberOfFibs", numberOfFibs)
            .build();
    }
}
