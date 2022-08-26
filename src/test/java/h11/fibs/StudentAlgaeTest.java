package h11.fibs;

import h11.Algae;
import h11.LSystemGrower;

public class StudentAlgaeTest extends AlgaeTest {

    protected StudentAlgaeTest() {
        super(new FibonacciGeneratorImpl(), new AlgaeFibonacciGenerator(LSystemGrower.of(new Algae())));
    }
}
