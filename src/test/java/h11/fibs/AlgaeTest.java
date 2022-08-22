package h11.fibs;

import h11.Algae;
import h11.LSystemGrower;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

abstract class AlgaeTest {

    private final FibonacciGenerator fibonacciGenerator;

    private final LSystemGrower<Algae.Variable> algaeGrower;

    protected AlgaeTest(FibonacciGenerator fibonacciGenerator, LSystemGrower<Algae.Variable> algaeGrower) {
        this.fibonacciGenerator = fibonacciGenerator;
        this.algaeGrower = algaeGrower;
    }

    @Test
    void testAlgaeGeneratesFibs() {
        var fibs = fibonacciGenerator.generate(20);

        var actual = algaeGrower.grow()
                .limit(fibs.size())
                .map(List::size)
                .toList();

        assertIterableEquals(fibs, actual);
    }
}
