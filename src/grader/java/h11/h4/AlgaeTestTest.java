package h11.h4;

import h11.fibs.AlgaeTest;
import h11.fibs.FibonacciGenerator;
import h11.tutor.fibs.TutorFibonacciGenerator;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.opentest4j.AssertionFailedError;
import org.tudalgo.algoutils.tutor.general.test.Assertions2;
import org.tudalgo.algoutils.tutor.general.test.Context;

import java.util.ArrayList;

public class AlgaeTestTest {

    private static final FibonacciGenerator TUTOR_FIBONACCI_GENERATOR = new TutorFibonacciGenerator();

    @ParameterizedTest
    @ValueSource(ints = {8, 16, 20, 24, 30})
    @Tag("H4")
    void testThat_algaeTestAcceptsPositive(int numberOfFibs) throws NoSuchMethodException {
        var algaeTest = new MockAlgaeTest(TUTOR_FIBONACCI_GENERATOR);
        Assertions2.assertTrue(testAccepts(() -> algaeTest.testAlgaeGeneratesFibs(numberOfFibs)), getContext(numberOfFibs), result ->
            "The test case did not accept correct input");
    }

    private Context getContext(int numberOfFibs) throws NoSuchMethodException {
        return Assertions2.contextBuilder()
            .subject(AlgaeTest.class.getMethod("testAlgaeGeneratesFibs", int.class))
            .property("numberOfFibs", numberOfFibs)
            .build();
    }

    @ParameterizedTest
    @ValueSource(ints = {8, 16, 20, 24, 30})
    @Tag("H4")
    void testThat_algaeTestRejectsWrongSize(int numberOfFibs) throws NoSuchMethodException {
        var algaeTest = new MockAlgaeTest(n ->
            TUTOR_FIBONACCI_GENERATOR.generate(numberOfFibs-1));
        Assertions2.assertFalse(testAccepts(() -> algaeTest.testAlgaeGeneratesFibs(numberOfFibs)), getContext(numberOfFibs), result ->
            "The test case did accept input with an incorrect length");
    }

    @ParameterizedTest
    @ValueSource(ints = {8, 16, 20, 24, 30})
    @Tag("H4")
    void testThat_algaeTestRejectsWrongValues(int numberOfFibs) throws NoSuchMethodException {
        var algaeTest = new MockAlgaeTest(n -> {
            var fibs = new ArrayList<>(TUTOR_FIBONACCI_GENERATOR.generate(numberOfFibs-1));
            fibs.set(fibs.size()-2, 0);
            return fibs;
        });

        Assertions2.assertFalse(testAccepts(() -> algaeTest.testAlgaeGeneratesFibs(numberOfFibs)), getContext(numberOfFibs), result ->
            "The test case did accept input with an incorrect value");
    }

    private static class MockAlgaeTest extends AlgaeTest {

        protected MockAlgaeTest(FibonacciGenerator algaeFibonacciGenerator) {
            super(TUTOR_FIBONACCI_GENERATOR, algaeFibonacciGenerator);
        }
    }

    private boolean testAccepts(Executable test) {
        try {
            test.execute();
            return true;
        } catch (AssertionFailedError e) {
            return false;
        } catch (Throwable e) {
            throw new AssertionFailedError("An unexpected exception was thrown while executing the test", e);
        }
    }

}
