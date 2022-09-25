package h11.h4;

import h11.Algae;
import h11.LSystemGrower;
import h11.fibs.AlgaeFibonacciGenerator;
import h11.tutor.TutorAlgae;
import h11.tutor.TutorLSystemGrower;

public class AlgaeFibonacciGeneratorTest extends FibonacciGeneratorTest {

    private static final LSystemGrower<Algae.Variable> ALGAE_GROWER = new TutorLSystemGrower<>(new TutorAlgae());

    protected AlgaeFibonacciGeneratorTest() {
        super(new AlgaeFibonacciGenerator(ALGAE_GROWER));
    }

    @Override
    public void testThat_initialIsCorrect(int numberOfFibs) {
        super.testThat_initialIsCorrect(numberOfFibs);
    }

    @Override
    public void testThat_fibsAreCorrect(int numberOfFibs) {
        super.testThat_fibsAreCorrect(numberOfFibs);
    }
}
