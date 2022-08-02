package h11;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AlgaeTest {

    @Test
    void testAlgaeGeneratesFibs() {
        var fibs = List.of(
                1, 2, 3, 5, 8, 13, 21, 34, 55,
                89, 144, 233, 377, 610, 987, 1597, 2584,
                4181, 6765, 10946, 17711, 28657, 46368, 75025, 121393
        );

        var algae = new Algae();
        var generator = new LSystemGeneratorImpl<>(algae);

        var actual = generator.generate()
                .limit(fibs.size())
                .map(List::size)
                .toList();

        assertIterableEquals(fibs, actual);
    }
}
