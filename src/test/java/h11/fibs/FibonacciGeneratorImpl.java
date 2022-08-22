package h11.fibs;

import java.util.List;
import java.util.stream.Stream;

public class FibonacciGeneratorImpl implements FibonacciGenerator {

    @Override
    public List<Integer> generate(int numberOfFibs) {
        return Stream
            .iterate(new FibonacciPair(), FibonacciPair::next)
            .map(FibonacciPair::a)
            .limit(numberOfFibs)
            .toList();
    }
}
