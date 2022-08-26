package h11.fibs;

import h11.Algae;
import h11.LSystemGrower;

import java.util.List;

public class AlgaeFibonacciGenerator implements FibonacciGenerator {

    private final LSystemGrower<Algae.Variable> algaeGrower;

    public AlgaeFibonacciGenerator(LSystemGrower<Algae.Variable> algaeGrower) {
        this.algaeGrower = algaeGrower;
    }

    @Override
    public List<Integer> generate(int numberOfFibs) {
        return algaeGrower.grow()
            .limit(numberOfFibs)
            .map(List::size)
            .toList();
    }
}
