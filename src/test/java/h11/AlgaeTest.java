package h11;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class AlgaeTest {

    @Test
    void testAlgaeGeneratesFibs() {
        var fibs = getFibs(20);

        var algae = new Algae();
        var grower = LSystemGrower.of(algae);

        var actual = grower.grow()
                .limit(fibs.size())
                .map(List::size)
                .toList();

        assertIterableEquals(fibs, actual);
    }

    @NotNull
    private static List<Integer> getFibs(int numberOfFibs) {
        return Stream
            .iterate(new FibPair(), FibPair::next)
            .map(FibPair::a)
            .limit(numberOfFibs)
            .toList();
    }

    private record FibPair(int a, int b) {

        FibPair() {
            this(1, 2);
        }

        public FibPair next() {
            return new FibPair(b, a+b);
        }
    }
}
